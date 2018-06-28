package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Clazz;
import com.briup.apps.poll.bean.extend.ClazzVM;
import com.briup.apps.poll.service.IClazzService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="班级相关接口")
@RestController
@RequestMapping("/Clazz")
public class ClazzController {
	
	@Autowired
	private IClazzService clazzService;
	@ApiOperation(value="查询所有数据")
	@GetMapping("findAllClazz")
	public MsgResponse findAllClazz(){
		try {
			List<Clazz> list =  clazzService.findAll();
			//返回成功信息
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			//返回错误信息
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="查询所有班级数据并带有年级和班主任数据")
	@GetMapping("findAllClazzVM")
	public MsgResponse findAllClazzVM(){
		try {
			List<ClazzVM> list = clazzService.findAllClazzVM();
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
			Clazz clazz = clazzService.findById(id);
			return MsgResponse.success("success", clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="根据name关键字查询",notes="查询时需要输入name中的关键字")
	@GetMapping("findByKeyWords")
	public MsgResponse findByKeyWords(@RequestParam String keyWords){
		try {
			List<Clazz> list = clazzService.query(keyWords);
			return MsgResponse.success("successs", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="更新或插入数据",notes="不输入id时执行插入操作，输入id时执行更新操作")
	@PostMapping("saveOrUpdate")
	public MsgResponse saveOrUpdate(Clazz clazz){
		try {
			clazzService.saveOrUpdate(clazz);
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
			clazzService.deleteById(id);
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
			clazzService.batchDelete(ids);
			return MsgResponse.success("success", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
}
