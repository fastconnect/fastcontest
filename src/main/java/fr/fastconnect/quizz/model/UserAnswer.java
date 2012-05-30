package fr.fastconnect.quizz.model;

public class UserAnswer {

	private int questionId;

	private String answer;

	private boolean isCorrect;

	public int getQuestion() {
		return questionId;
	}

	public void setQuestion(int questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

}
