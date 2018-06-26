package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Grade;
import com.briup.apps.poll.bean.GradeExample;
import com.briup.apps.poll.dao.GradeMapper;
import com.briup.apps.poll.service.IGradeService;

@Service
public class GradeServiceImpl implements IGradeService {
	@Autowired
	private GradeMapper gradeMapper;

	@Override
	public List<Grade> findAll() throws Exception {
		GradeExample example = new GradeExample();
		return gradeMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Grade findById(long id) throws Exception {

		return gradeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Grade> query(String keyWords) throws Exception {
		GradeExample example = new GradeExample();
		// 添加一个条件，name属性中包含keyWords
		example.createCriteria().andNameLike(keyWords);
		return gradeMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void saveOrUpdate(Grade grade) throws Exception {
		if (grade.getId() != null) {
			// 更新
			gradeMapper.updateByPrimaryKey(grade);
		} else {
			// 插入
			gradeMapper.insert(grade);
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		gradeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		for (long id : ids) {
			gradeMapper.deleteByPrimaryKey(id);
		}
	}
}
