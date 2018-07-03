package com.briup.apps.poll.bean.extend;

import java.util.List;

import io.swagger.annotations.Api;
@Api(value="问卷模型，问卷包含多个问题，每个问题包含多个选项")
public class QuestionnaireVM {
	private Long id;
	private String name;
	private String description;
	private List<QuestionVM> questionVMs;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<QuestionVM> getQuestionVM() {
		return questionVMs;
	}
	public void setQuestionVM(List<QuestionVM> questionVMs) {
		this.questionVMs = questionVMs;
	}
}
