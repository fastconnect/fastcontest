package fr.fastconnect.quizz.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import fr.fastconnect.quizz.model.UserContestParticipation;

public interface ParticipationDao 
{
	@PostConstruct
	public void init();
	
	public int count();
	
	public UserContestParticipation findFirstByUserContest(final String email, final int contestId);
	
	public void save(final UserContestParticipation participation);
	
	public List<UserContestParticipation> getBestResult(final int maxResult, final int batchSize, final boolean orderDesc);
}
