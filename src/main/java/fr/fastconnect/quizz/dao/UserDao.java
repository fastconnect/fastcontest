package fr.fastconnect.quizz.dao;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;

import fr.fastconnect.quizz.model.User;

public interface UserDao 
{
	@PostConstruct
	public void init();
	
	public int count();
	
	public User findFirstById(final ObjectId userId);
	
	public User findFirstByEmail(final String email);
	
	public User findFirstByEmailPassword(final String email, final String password);
	
	public void save(final User user);	
}
