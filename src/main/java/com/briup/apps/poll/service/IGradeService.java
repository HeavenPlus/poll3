package com.briup.apps.poll.service;

import java.util.List;

import com.briup.apps.poll.bean.Grade;
import com.briup.apps.poll.bean.extend.GradeVM;

public interface IGradeService {
	
	/**
	 * 查询所有年级信息，并包含所属学校信息
	 * @return
	 * @throws Exception
	 */
	List<GradeVM> findAllGrade() throws Exception;
	/**
	 * 根据id查询年级信息，包含所属学校信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	GradeVM findById(long id) throws Exception;
	/**
	 * 根据name属性关键字查询年级信息，包含所属学校信息
	 * @param keyWords
	 * @return
	 */
	List<GradeVM> findByKeyWords(String keyWords);
	/**
	 * 根据id删除年级信息，删除时会删除包含的班级信息
	 * @param id
	 * @throws Exception
	 */
	void deleteById(long id) throws Exception;
	
	/**
	 * 根据id批量删除年级信息，删除时会删除包含的班级信息
	 * @param ids
	 * @throws Exception
	 */
	void batchDelete(long[] ids) throws Exception;
	/**
	 * 保存或更新年级信息，id为空时保存信息，id不为空时修改信息
	 * @param grade
	 * @throws Exception
	 */
	void saveOrUpdate(Grade grade) throws Exception;
}
