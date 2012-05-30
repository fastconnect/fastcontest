package fr.fastconnect.quizz.model;

import java.util.List;
import java.util.Random;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserContestParticipation {
	@Id
	private ObjectId participationId;

	private String email;

	private String firstName;
	private String lastName;
	private String birthday;

	private int contestId;

	private int score;

	private int luckyPoint;

	private List<UserAnswer> answers;

	public ObjectId getParticipationId() {
		return this.participationId;
	}

	public void setParticipationId(final ObjectId id) {
		this.participationId = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(final String birthday) {
		this.birthday = birthday;
	}

	public int getContestId() {
		return contestId;
	}

	public void setContestId(final int contestId) {
		this.contestId = contestId;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(final Integer score) {
		this.score = score;
	}

	public int getLuckyPoint() {
		return luckyPoint;
	}

	public void setLuckyPoint(final int luckyPoint) {
		this.luckyPoint = luckyPoint;
	}

	private static final Random RANDOM = new Random();
	public void generateLuckyPoint() {
		this.luckyPoint = (RANDOM.nextInt(1000));
	}

	public List<UserAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(final List<UserAnswer> answers) {
		this.answers = answers;
	}
}
