package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.SurveyExample;
import com.briup.apps.poll.dao.SurveyMapper;
import com.briup.apps.poll.service.ISurveyService;

@Service
public class SurveyServiceImpl implements ISurveyService {
	@Autowired
	private SurveyMapper surveyMapper;

	@Override
	public List<Survey> findAll() throws Exception {
		SurveyExample example = new SurveyExample();
		return surveyMapper.selectByExample(example);
	}

	@Override
	public Survey findById(long id) throws Exception {

		return surveyMapper.selectByPrimaryKey(id);
	}
	@Override
	public void saveOrUpdate(Survey survey) throws Exception {
		if (survey.getId() != null) {
			// 更新
			surveyMapper.updateByPrimaryKey(survey);
		} else {
			// 插入
			surveyMapper.insert(survey);
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		surveyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		for (long id : ids) {
			surveyMapper.deleteByPrimaryKey(id);
		}
	}
}
