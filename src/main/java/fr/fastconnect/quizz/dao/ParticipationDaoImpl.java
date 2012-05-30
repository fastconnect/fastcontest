package fr.fastconnect.quizz.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import fr.fastconnect.quizz.model.UserContestParticipation;

public class ParticipationDaoImpl implements ParticipationDao 
{
	private static final Log logger = LogFactory.getLog(ParticipationDaoImpl.class);

	@Resource(name="mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	@Override
	public void init() {
		final String collectionName = mongoTemplate.getCollectionName(UserContestParticipation.class);
		if (!mongoTemplate.collectionExists(collectionName)) {
			mongoTemplate.createCollection(collectionName)
					.ensureIndex(new BasicDBObject("score", 1).append("luckyPoint", 1));
		}
	}

	@Override
	public int count() {
		return mongoTemplate.findAll(UserContestParticipation.class).size();
	}

	@Override
	public UserContestParticipation findFirstByUserContest(final String email, final int contestId) {
		final Query query= new Query(Criteria.where("email").is(email).and("contestId").is(contestId));
		return mongoTemplate.findOne(query, UserContestParticipation.class);
	}
	
	@Override
	public void save(final UserContestParticipation participation) {
		mongoTemplate.save(participation);
	}

	/**
	 * Returns a list of best user contest Participation based on their score during contests
	 * @param maxResult
	 * @param orderDesc
	 * @return A list of UserContestParticipation object
	 * 
	 */
	public List<UserContestParticipation> getBestResult(final int maxResult, final int batchSize, final boolean orderDesc){

		final List<UserContestParticipation> contestParticipationsList = new ArrayList<UserContestParticipation>();

		// Queries the data base with the specified parameters
		final int order = orderDesc ? -1 : 1;
		final String collectionName = mongoTemplate.getCollectionName(UserContestParticipation.class);
		final DBCursor dbCursor = mongoTemplate.getCollection(collectionName)
				.find()
				.sort(new BasicDBObject("score", order).append("luckyPoint", order))
				.limit(maxResult)
				.batchSize(batchSize);

		// Iterate throw query result
		// check and retrieving user contest participations returned values
		while (dbCursor.hasNext()) {

			final DBObject dbObject = dbCursor.next();
			final UserContestParticipation userContextParticipation = new UserContestParticipation();

			// checking contest id
			try {
				final int contestId = (Integer) dbObject.get("contestId");
				userContextParticipation.setContestId(contestId);
			} catch (Exception e) {
				logger.warn("Error occured while processing the user data. Contest Id is not set, changing it to 0.");
				userContextParticipation.setContestId(0);
			}

			// checking first name
			try {
				final String firstName = (String) dbObject.get("firstName");
				userContextParticipation.setFirstName(firstName);
			} catch (Exception e) {
				logger.warn("Error occured while processing the user first Name, changing it to \"\" (empty string).");
				userContextParticipation.setFirstName("");	
			}

			// checking last name
			try {
				final String lastName = (String) dbObject.get("lastName");
				userContextParticipation.setLastName(lastName);
			} catch (Exception e) {
				logger.warn("Error occured while processing the user last Name, changing it to \"\" (empty string).");
				userContextParticipation.setLastName("");	
			}

			// checking email
			try {
				final String email = (String)  dbObject.get("email");
				userContextParticipation.setEmail(email);
			} catch (Exception e) {
				logger.warn("Error occured while processing the user last Name, changing it to \"\" (empty string).");
				userContextParticipation.setLastName("");
			}

			// checking score
			try {
				final int score = (Integer) dbObject.get("score");
				userContextParticipation.setScore(score);
			} catch (Exception e) {
				logger.warn("Error occured while processing the user score, changing it to 0.");
				userContextParticipation.setScore(0);
			}

			// checking luckyPoint
			try {
				final int luckyPoint = (Integer) dbObject.get("luckyPoint");
				userContextParticipation.setLuckyPoint(luckyPoint);
			} catch (Exception e) {
				logger.warn("Error occured while processing the user luckyPoint, changing it to 0.");
				userContextParticipation.setLuckyPoint(0);
			}

			// add the current contest participation to result list.
			contestParticipationsList.add(userContextParticipation);
		}

		return contestParticipationsList;
	}
}
