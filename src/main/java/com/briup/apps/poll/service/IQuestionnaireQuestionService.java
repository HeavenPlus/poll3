package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.QuestionnaireQuestion;

public interface IQuestionnaireQuestionService {
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	List<QuestionnaireQuestion> findAll() throws Exception;
	/**
	 *根据id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	QuestionnaireQuestion findById(long id) throws Exception;
	/**
	 * 根据id删除
	 * @param id
	 * @throws Exception
	 */
	void deleteById(long id) throws Exception;
	/**
	 * 保存或更新
	 * @param qq
	 * @throws Exception
	 */
	void saveOrUpdate(QuestionnaireQuestion qq) throws Exception;
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	void batchDelete(long[] ids) throws Exception;
}
