package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Question;
import com.briup.apps.poll.bean.extend.QuestionVM;

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
	 * @param questionVm
	 * @throws Exception
	 */
	void saveOrUpdateQuestionVM(QuestionVM questionVM) throws Exception;
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
	/**
	 * 查询所有问题信息，并带有选项信息
	 * @return
	 * @throws Exception
	 */
	List<QuestionVM> findAllOptions() throws Exception;
	/**
	 * 根据id 查询问题信息，并带有选项信息
	 * @return
	 * @throws Exception
	 */
	QuestionVM selectById(long id) throws Exception;
	
	List<QuestionVM> selectByKeyWords(String keyWords) throws Exception;
}
