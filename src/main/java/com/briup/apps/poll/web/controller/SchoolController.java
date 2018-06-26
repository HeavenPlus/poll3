package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.School;
import com.briup.apps.poll.service.ISchoolService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="学校相关接口")
@RestController
@RequestMapping("/school")
public class SchoolController {
	
	@Autowired
	private ISchoolService schoolService;
	@ApiOperation(value="查询所有数据")
	@GetMapping("findAllSchool")
	public MsgResponse findAllSchool(){
		try {
			List<School> list =  schoolService.findAll();
			//返回成功信息
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			//返回错误信息
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="根据id查询一条数据",notes="查询时需要输入id")
	@GetMapping("findById")
	public MsgResponse findById(@RequestParam long id){
		try {
			School school = schoolService.findById(id);
			return MsgResponse.success("success", school);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="根据name关键字查询",notes="查询时需要输入name中的关键字")
	@GetMapping("findByKeyWords")
	public MsgResponse findByKeyWords(@RequestParam String keyWords){
		try {
			List<School> list = schoolService.query(keyWords);
			return MsgResponse.success("successs", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="更新或插入数据",notes="不输入id时执行插入操作，输入id时执行更新操作")
	@PostMapping("saveOrUpdate")
	public MsgResponse saveOrUpdate(School school){
		try {
			schoolService.saveOrUpdate(school);
			return MsgResponse.success("success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="根据id删除数据",notes="删除时需要输入id")
	@GetMapping("deleteById")
	public MsgResponse deleteById(@RequestParam long id){
		try {
			schoolService.deleteById(id);
			return MsgResponse.success("success", "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="批量删除数据",notes="需要输入id")
	@GetMapping("batchDelete")
	public MsgResponse batchDelete(long[] ids){
		try {
			schoolService.batchDelete(ids);
			return MsgResponse.success("success", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
}
