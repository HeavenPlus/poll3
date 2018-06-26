package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Clazz;
import com.briup.apps.poll.bean.ClazzExample;
import com.briup.apps.poll.dao.ClazzMapper;
import com.briup.apps.poll.service.IClazzService;

@Service
public class ClazzServiceImpl implements IClazzService {
	@Autowired
	private ClazzMapper clazzMapper;

	@Override
	public List<Clazz> findAll() throws Exception {
		ClazzExample example = new ClazzExample();
		return clazzMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Clazz findById(long id) throws Exception {

		return clazzMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Clazz> query(String keyWords) throws Exception {
		ClazzExample example = new ClazzExample();
		// 添加一个条件，name属性中包含keyWords
		example.createCriteria().andNameLike(keyWords);
		return clazzMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void saveOrUpdate(Clazz clazz) throws Exception {
		if (clazz.getId() != null) {
			// 更新
			clazzMapper.updateByPrimaryKey(clazz);
		} else {
			// 插入
			clazzMapper.insert(clazz);
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		clazzMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		for (long id : ids) {
			clazzMapper.deleteByPrimaryKey(id);
		}
	}
}
