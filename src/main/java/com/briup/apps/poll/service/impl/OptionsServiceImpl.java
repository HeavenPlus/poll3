package com.briup.apps.poll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.poll.bean.Options;
import com.briup.apps.poll.bean.OptionsExample;
import com.briup.apps.poll.dao.OptionsMapper;
import com.briup.apps.poll.service.IOptionsService;

@Service
public class OptionsServiceImpl implements IOptionsService {
	@Autowired
	private OptionsMapper optionsMapper;

	@Override
	public List<Options> findAll() throws Exception {
		OptionsExample example = new OptionsExample();
		return optionsMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Options findById(long id) throws Exception {

		return optionsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Options> query(String keyWords) throws Exception {
		OptionsExample example = new OptionsExample();
		// 添加一个条件，name属性中包含keyWords
		example.createCriteria().andNameLike(keyWords);
		return optionsMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void saveOrUpdate(Options options) throws Exception {
		if (options.getId() != null) {
			// 更新
			optionsMapper.updateByPrimaryKey(options);
		} else {
			// 插入
			optionsMapper.insert(options);
		}
	}

	@Override
	public void deleteById(long id) throws Exception {
		optionsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws Exception {
		for (long id : ids) {
			optionsMapper.deleteByPrimaryKey(id);
		}
	}
}
