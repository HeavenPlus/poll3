package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.School;
import com.briup.apps.poll.bean.SchoolExample;
import com.briup.apps.poll.dao.SchoolMapper;
import com.briup.apps.poll.service.ISchoolService;

@Service
public class SchoolServiceImpl implements ISchoolService {
	@Autowired
	private SchoolMapper schoolMapper;

	@Override
	public List<School> findAll() throws Exception {
		SchoolExample example = new SchoolExample();
		return schoolMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public School findById(long id) throws Exception {

		return schoolMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<School> query(String keyWords) throws Exception {
		SchoolExample example = new SchoolExample();
		// 添加一个条件，name属性中包含keyWords
		example.createCriteria().andNameLike(keyWords);
		return schoolMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void saveOrUpdate(School school) throws Exception {
		if (school.getId() != null) {
			// 更新
			schoolMapper.updateByPrimaryKey(school);
		} else {
			// 插入
			schoolMapper.insert(school);
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		schoolMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		for (long id : ids) {
			schoolMapper.deleteByPrimaryKey(id);
		}
	}
}
