package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Question;
import com.briup.apps.poll.bean.QuestionExample;
import com.briup.apps.poll.dao.QuestionMapper;
import com.briup.apps.poll.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService {
	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public List<Question> findAll() throws Exception {
		QuestionExample example = new QuestionExample();
		return questionMapper.selectByExample(example);
	}

	@Override
	public Question findById(long id) throws Exception {

		return questionMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Question> query(String keyWords) throws Exception {
		QuestionExample example = new QuestionExample();
		// 添加一个条件，name属性中包含keyWords
		example.createCriteria().andNameLike(keyWords);
		return questionMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(Question question) throws Exception {
		if (question.getId() != null) {
			// 更新
			questionMapper.updateByPrimaryKey(question);
		} else {
			// 插入
			questionMapper.insert(question);
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		questionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		for (long id : ids) {
			questionMapper.deleteByPrimaryKey(id);
		}
	}
}
