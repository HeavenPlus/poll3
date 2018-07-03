package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Questionnaire;
import com.briup.apps.poll.bean.QuestionnaireExample;
import com.briup.apps.poll.bean.QuestionnaireQuestion;
import com.briup.apps.poll.bean.QuestionnaireQuestionExample;
import com.briup.apps.poll.bean.extend.QuestionnaireVM;
import com.briup.apps.poll.dao.QuestionnaireMapper;
import com.briup.apps.poll.dao.QuestionnaireQuestionMapper;
import com.briup.apps.poll.dao.extend.QuestionnaireVMMapper;
import com.briup.apps.poll.service.IQuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements IQuestionnaireService {
	@Autowired
	private QuestionnaireMapper questionnaireMapper;
	@Autowired
	private QuestionnaireVMMapper questionnaireVMMapper;
	@Autowired
	private QuestionnaireQuestionMapper qqMapper;
	@Override
	public List<Questionnaire> findAll() throws Exception {
		QuestionnaireExample example = new QuestionnaireExample();
		return questionnaireMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Questionnaire findById(long id) throws Exception {

		return questionnaireMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Questionnaire> query(String keyWords) throws Exception {
		QuestionnaireExample example = new QuestionnaireExample();
		// 添加一个条件，name属性中包含keyWords
		example.createCriteria().andNameLike(keyWords);
		return questionnaireMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void saveOrUpdate(Questionnaire questionnaire,long[] questionIds) throws Exception {
		if (questionnaire.getId() != null) {
			// 更新问卷
			questionnaireMapper.updateByPrimaryKey(questionnaire);
			//删除poll_qq表中原有的内容
			QuestionnaireQuestionExample example = new QuestionnaireQuestionExample();
			example.createCriteria().andQuestionnaireIdEqualTo(questionnaire.getId());
			qqMapper.deleteByExample(example);
			//保存新的内容
			long questionnaireId = questionnaire.getId();
			for(long questionId : questionIds){
				QuestionnaireQuestion qq = new QuestionnaireQuestion();
				qq.setQuestionnaireId(questionnaireId);
				qq.setQuestionId(questionId);
				qqMapper.insert(qq);
			}
		} else {
			// 插入
			questionnaireMapper.insert(questionnaire);
			//保存到poll_qq表
			long questionnaireId = questionnaire.getId();
			for(long questionId : questionIds){
				QuestionnaireQuestion qq = new QuestionnaireQuestion();
				qq.setQuestionnaireId(questionnaireId);
				qq.setQuestionId(questionId);
				qqMapper.insert(qq);
			}
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		questionnaireMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		for (long id : ids) {
			questionnaireMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public QuestionnaireVM findVMById(long id) throws Exception {
		
		return questionnaireVMMapper.selectById(id);
	}
}
