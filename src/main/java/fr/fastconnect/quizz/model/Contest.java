package fr.fastconnect.quizz.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Contest {
	@Id
	private int contestId;

	private static int currentContestId = 0;

	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public int getContestId() {
		return contestId;
	}

	public void setContestId(int contestId) {
		this.contestId = contestId;
	}

	public static int getCurrentContestId() {
		return currentContestId;
	}

	public static void setCurrentContestId(int contest) {
		currentContestId = contest;
	}
}
