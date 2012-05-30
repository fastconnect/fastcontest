package fr.fastconnect.quizz.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import fr.fastconnect.quizz.model.User;

public class UserDaoImpl implements UserDao 
{
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void init() {
		final String collectionName = mongoTemplate.getCollectionName(User.class);
		if (!mongoTemplate.collectionExists(collectionName)) {
			mongoTemplate.createCollection(collectionName)
					.ensureIndex("email");
		}
	}
	
	@Override
	public int count() {
		return mongoTemplate.findAll(User.class).size();
	}
	
	@Override
	public User findFirstById(final ObjectId userId) {
		final Query query= new Query(Criteria.where("userId").is(userId));
	    return mongoTemplate.findOne(query, User.class);
	}
	
	@Override
	public User findFirstByEmail(final String email){
		final Query query = new Query(Criteria.where("email").is(email));
		return mongoTemplate.findOne(query, User.class);
	}
	
	@Override
	public User findFirstByEmailPassword(final String email, final String hashedPassword){
		final Query query = new Query(Criteria.where("email").is(email).and("password").is(hashedPassword));
		return mongoTemplate.findOne(query, User.class);
	}
	
	@Override
	public void save(final User user) {
		mongoTemplate.save(user);
	}
	
}
