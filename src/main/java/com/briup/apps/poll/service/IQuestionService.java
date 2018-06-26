package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Question;

public interface IQuestionService {
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	List<Question> findAll() throws Exception;
	/**
	 * 根据id查询一条数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Question findById(long id) throws Exception;
	/**
	 * 根据关键字查询
	 * @param keyWords
	 * @return
	 * @throws Exception
	 */
	List<Question> query(String keyWords) throws Exception;
	/**
	 * 更新或插入数据
	 * @param question
	 * @throws Exception
	 */
	void saveOrUpdate(Question question) throws Exception;
	/**
	 * 根据id删除数据
	 * @param id
	 * @throws Exception
	 */
	void deleteById(long id) throws Exception;
	/**
	 * 批量删除数据
	 * @param ids
	 * @throws Exception
	 */
	void batchDelete(long[] ids) throws Exception;
}
