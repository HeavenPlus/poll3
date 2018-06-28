package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Options;
import com.briup.apps.poll.bean.OptionsExample;
import com.briup.apps.poll.bean.Question;
import com.briup.apps.poll.bean.QuestionExample;
import com.briup.apps.poll.bean.extend.QuestionVM;
import com.briup.apps.poll.dao.OptionsMapper;
import com.briup.apps.poll.dao.QuestionMapper;
import com.briup.apps.poll.dao.extend.QuestionVMMapper;
import com.briup.apps.poll.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService {
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionVMMapper questionvmMapper;
	@Autowired
	private OptionsMapper optionsMapper;

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
	public void deleteById(long id) throws Exception {
		questionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		for (long id : ids) {
			questionMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<QuestionVM> findAllOptions() throws Exception {
		return questionvmMapper.selectAll();
	}

	@Override
	public void saveOrUpdateQuestionVM(QuestionVM questionVM) throws Exception {
		//1. 分离questionVM,从中获取到Question Options
		List<Options> options = questionVM.getOptions();
		Question question = new Question();
		question.setId(questionVM.getId());
		question.setName(questionVM.getName());
		question.setQuestiontype(questionVM.getQuestionType());
		
		//question 问题对象，options 所有问题的选项
		//判断保存还是修改
		if(question.getId() == null){
			//保存
			if(question.getQuestiontype().equals("简答题")){
				// 保存简答题，只需要保存题目相关信息
				questionMapper.insert(question);
			} else {
				//保存单选和多选题的时候需要先保存题目信息，再保存选项信息
				questionMapper.insert(question);
				//如何获取刚刚插入到问题的ID
				long questionId = question.getId();
				for(Options option : options){
					//为每个option设置question_id
					option.setQuestionId(questionId);
					//保存选项
					optionsMapper.insert(option);
				}
			}
		} else {
			//修改问题
			questionMapper.updateByPrimaryKey(question);
			//删除原有选项
			OptionsExample example = new OptionsExample();
			example.createCriteria().andQuestionIdEqualTo(question.getId());
			optionsMapper.deleteByExample(example);
			//重新添加选项
			long questionId = question.getId();
			for(Options option : options){
				option.setQuestionId(questionId);
				optionsMapper.insert(option);
			}
		}	
	}

	@Override
	public QuestionVM selectById(long id) throws Exception {
		
		return questionvmMapper.selectById(id);
	}

	@Override
	public List<QuestionVM> selectByKeyWords(String keyWords) throws Exception {

		return questionvmMapper.selectByKeyWords(keyWords);
	}

}
