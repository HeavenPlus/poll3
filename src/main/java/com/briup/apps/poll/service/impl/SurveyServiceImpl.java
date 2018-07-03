package com.briup.apps.poll.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.SurveyExample;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.dao.SurveyMapper;
import com.briup.apps.poll.dao.extend.SurveyVMMapper;
import com.briup.apps.poll.service.ISurveyService;

@Service
public class SurveyServiceImpl implements ISurveyService {
	@Autowired
	private SurveyMapper surveyMapper;
	@Autowired
	private SurveyVMMapper surveyVMMapper;

	@Override
	public List<Survey> findAll() throws Exception {
		SurveyExample example = new SurveyExample();
		return surveyMapper.selectByExample(example);
	}

	@Override
	public SurveyVM findById(long id) throws Exception {

		return surveyVMMapper.selectById(id);
	}
	@Override
	public void saveOrUpdate(Survey survey) throws Exception {
		if (survey.getId() != null) {
			// 更新
			surveyMapper.updateByPrimaryKey(survey);
		} else {
			// 插入
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String surveydate = format.format(now);
			survey.setStatus(Survey.STATUS_INIT);
			survey.setCode("");
			survey.setSurveydate(surveydate);
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

	@Override
	public List<SurveyVM> findAllVM() throws Exception {
		return surveyVMMapper.selectAll();
	}

	@Override
	public Survey findSurveyById(long id) throws Exception {
		return surveyMapper.selectByPrimaryKey(id);
	}
}
