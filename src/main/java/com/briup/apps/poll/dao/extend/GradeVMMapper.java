package com.briup.apps.poll.dao.extend;

import java.util.List;

import com.briup.apps.poll.bean.extend.GradeVM;

public interface GradeVMMapper {
	
	List<GradeVM> selectAll();
	
	GradeVM selectById(long id);
	
	List<GradeVM> selectByKeyWords(String keyWords);
}
