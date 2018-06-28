package com.briup.apps.poll.bean.extend;

import com.briup.apps.poll.bean.Question;
import com.briup.apps.poll.bean.Questionnaire;

public class QuestionnaireQuestionVM {
	private long id;
	private Questionnaire questionnaire;
	private Question question;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}
}
