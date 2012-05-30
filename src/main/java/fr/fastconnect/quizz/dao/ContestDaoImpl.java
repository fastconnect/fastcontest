package fr.fastconnect.quizz.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import fr.fastconnect.quizz.model.Contest;

public class ContestDaoImpl implements ContestDao 
{
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void init() {
		final String collectionName = mongoTemplate.getCollectionName(Contest.class);
		if (!mongoTemplate.collectionExists(collectionName)) {
			mongoTemplate.createCollection(collectionName);
		}
	}
	
	@Override
	public int count() {
		return mongoTemplate.findAll(Contest.class).size();
	}
	
	@Override
	public List<Contest> findAll() {
		return mongoTemplate.findAll(Contest.class);
	}
	
	@Override
	public Contest findFirstById(final int contestId) {
		final Query query= new Query(Criteria.where("contestId").is(contestId));
	    return mongoTemplate.findOne(query, Contest.class);
	
	}
	
	@Override
	public void saveAll(final List<Contest> contests) {
		for (final Contest contest : contests){
			mongoTemplate.save(contest);
		}
	}
	
	@Override
	public void removeAll() {
		mongoTemplate.remove(new Query(), Contest.class);
	}
	
}
