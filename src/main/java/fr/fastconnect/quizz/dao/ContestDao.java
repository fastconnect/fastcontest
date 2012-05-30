package fr.fastconnect.quizz.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import fr.fastconnect.quizz.model.Contest;

public interface ContestDao 
{
	@PostConstruct
	public void init();
	
	public int count();
	
	public List<Contest> findAll();
	
	public Contest findFirstById(final int contestId);
	
	public void saveAll(final List<Contest> contests);
	
	public void removeAll();
}
