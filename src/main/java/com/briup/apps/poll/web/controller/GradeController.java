package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Grade;
import com.briup.apps.poll.bean.extend.GradeVM;
import com.briup.apps.poll.service.IGradeService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "年级相关接口")
@RestController
@RequestMapping("/grade")
public class GradeController {
	@Autowired
	private IGradeService gradeService;
	
	@ApiOperation(value="查询所有年级信息",notes=("年级信息包含所属学校信息"))
	@GetMapping("findAllGrade")
	public MsgResponse findAllGrade() {
		try {
			List<GradeVM> list = gradeService.findAllGrade();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
		
	}
	
	@ApiOperation(value="根据id查询年级信息",notes=("需要输入id,查询的年级信息包含所属学校信息"))
	@GetMapping("findGradeById")
	public MsgResponse findGradeById(@RequestParam long id) {
		// @Requestparam需要初始化默认值
		try {
			GradeVM gradeVM = gradeService.findById(id);
			return MsgResponse.success("success", gradeVM);
			// return course;
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过name属性关键字查询信息",notes="需要输入年级名称关键字")
	@GetMapping("selectByKeyWords")
	public MsgResponse selectByKeyWords(@RequestParam String keyWords) {
		try {
			List<GradeVM> list = gradeService.findByKeyWords(keyWords);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
		
	}

	@ApiOperation(value="根据id删除年级信息",notes=("删除时需要输入id，删除年级信息时会删除包含的班级信息"))
	@GetMapping("deleteGradeById")
	public MsgResponse deleteGradeById(@RequestParam long id) {
		try {
			gradeService.deleteById(id);
			return MsgResponse.success("success", "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="批量删除数据",notes="需要输入一组id")
	@GetMapping("batchDeleteGrade")
	public MsgResponse batchDeleteGrade(long[] ids) {
		try {
			gradeService.batchDelete(ids);
			return MsgResponse.success("success", "批量删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="更新或保存数据",notes="id为空时保存数据，id不为空时修改数据")
	@PostMapping("saveOrUpdateGrade")
	public MsgResponse saveOrUpdateGrade(Grade grade) {
		try {
			gradeService.saveOrUpdate(grade);
			return MsgResponse.success("success", "保存或更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
}
