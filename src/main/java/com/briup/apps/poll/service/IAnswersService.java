package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Answers;

public interface IAnswersService {
	
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	List<Answers> findAll() throws Exception;
	/**
	 * 根据id查询一条数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Answers findById(long id) throws Exception;
	/**
	 * 根据关键字查询
	 * @param keyWords
	 * @return
	 * @throws Exception
	 */
//	List<Answers> query(String keyWords) throws Exception;
	/**
	 * 更新或插入数据
	 * @param answers
	 * @throws Exception
	 */
	void saveOrUpdate(Answers answers) throws Exception;
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
