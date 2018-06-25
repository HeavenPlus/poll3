package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Course;

public interface ICourseService {
	/**
	 * 查询所有信息
	 * @return
	 * @throws Exception
	 */
	List<Course> findAll() throws Exception;
	/**
	 * 根据id查询一条信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Course findById(long id) throws Exception;
	/**
	 * 根据关键字查询
	 * @param keyWords
	 * @return
	 * @throws Exception
	 */
	List<Course> query(String keyWords) throws Exception;
	/**
	 * 
	 * @param course
	 * @throws Exception
	 */
	void saveOrUpdate(Course course) throws Exception;
	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	void deleteById(long id) throws Exception;
	/**
	 * 
	 * @param ids
	 * @throws Exception
	 */
	void batchDelete(List<Long> ids) throws Exception;
}
