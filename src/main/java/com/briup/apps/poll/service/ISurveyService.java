package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Survey;

public interface ISurveyService {
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	List<Survey> findAll() throws Exception;
	/**
	 * 根据id查询一条数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Survey findById(long id) throws Exception;
	/**
	 * 更新或插入数据
	 * @param survey
	 * @throws Exception
	 */
	void saveOrUpdate(Survey survey) throws Exception;
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
