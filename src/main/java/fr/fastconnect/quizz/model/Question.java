package fr.fastconnect.quizz.model;

import java.util.List;

public class Question {
	private int questionId;

	private String question;

	private QuestionType questionType;

	private String correctAnswer;

	private List<String> answers;

	public Question() {
		this.setQuestionId();
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public int getQuestionId() {
		return questionId;
	}

	private static int ID = 1;
	public synchronized void setQuestionId() {
		this.questionId = ID;
		ID++;
	}
}
