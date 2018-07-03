package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Grade;
import com.briup.apps.poll.bean.GradeExample;
import com.briup.apps.poll.bean.extend.GradeVM;
import com.briup.apps.poll.dao.GradeMapper;
import com.briup.apps.poll.dao.extend.GradeVMMapper;
import com.briup.apps.poll.service.IGradeService;

@Service
public class GradeServiceImpl implements IGradeService {
	@Autowired
	private GradeMapper gradeMapper;
	@Autowired
	private GradeVMMapper gradeVMMapper;
	
	/**
	 * 查询所有年级信息，并包含所属学校信息
	 */
	@Override
	public List<GradeVM> findAllGrade() throws Exception {
		return gradeVMMapper.selectAll();
	}
	
	/**
	 * 根据id查询年级信息，包含所属学校信息
	 */
	@Override
	public GradeVM findById(long id) throws Exception {
		return gradeVMMapper.selectById(id);
	}
	
	/**
	 * 根据name属性关键字查询年级信息，包含所属学校信息
	 */
	@Override
	public List<GradeVM> findByKeyWords(String keyWords) {
		return gradeVMMapper.selectByKeyWords(keyWords);
	}
	
	/**
	 *  根据id删除年级信息，删除时会删除包含的班级信息
	 */
	@Override
	public void deleteById(long id) throws Exception {
		gradeMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 *  根据id批量删除年级信息，删除时会删除包含的班级信息
	 */
	@Override
	public void batchDelete(long[] ids) throws Exception {
		for (long id : ids) {
			gradeMapper.deleteByPrimaryKey(id);
		}
	}
	
	/**
	 * 保存或更新年级信息，id为空时保存信息，id不为空时修改信息
	 */
	@Override
	public void saveOrUpdate(Grade grade) throws Exception {
		if (grade.getId() != null) {
			gradeMapper.updateByPrimaryKey(grade);
		} else {
			gradeMapper.insert(grade);
		}
	}
}
