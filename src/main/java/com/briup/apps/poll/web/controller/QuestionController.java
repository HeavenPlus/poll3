package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Question;
import com.briup.apps.poll.bean.extend.QuestionVM;
import com.briup.apps.poll.service.IQuestionService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="问题相关接口")
@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private IQuestionService questionService;
	@ApiOperation(value="查询所有数据")
	@GetMapping("findAllQuestion")
	public MsgResponse findAllQuestion(){
		try {
			List<Question> list =  questionService.findAll();
			//返回成功信息
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			//返回错误信息
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="查询所有数据")
	@GetMapping("findAllOptions")
	public MsgResponse findAllOptions(){
		try {
			List<QuestionVM> list = questionService.findAllOptions();
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
			Question question = questionService.findById(id);
			return MsgResponse.success("success", question);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="根据id查询一条数据并带有选项信息",notes="查询时需要输入id")
	@GetMapping("selectById")
	public MsgResponse selectById(@RequestParam long id){
		try {
			QuestionVM questionVM = questionService.selectById(id);
			return MsgResponse.success("success", questionVM);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="根据name关键字查询",notes="查询时需要输入name中的关键字")
	@GetMapping("findByKeyWords")
	public MsgResponse findByKeyWords(@RequestParam String keyWords){
		try {
			List<Question> list = questionService.query(keyWords);
			return MsgResponse.success("successs", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="根据name关键字查询,并带有选项信息",notes="查询时需要输入name中的关键字")
	@GetMapping("selectByKeyWords")
	public MsgResponse selectByKeyWords(@RequestParam String keyWords){
		try {
			List<QuestionVM> list = questionService.selectByKeyWords(keyWords);
			return MsgResponse.success("successs", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="保存或修改问题",
			notes="当id不为空表示修改，否则表示更新，保存和更新的时候需要提交选项数据")
	@PostMapping("saveOrUpdateQuestion")
	public MsgResponse saveOrUpdateQuestion(QuestionVM questionVM){
		try {
			questionService.saveOrUpdateQuestionVM(questionVM);
			return MsgResponse.success("保存成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="根据id删除数据",notes="删除时需要输入id")
	@GetMapping("deleteById")
	public MsgResponse deleteById(@RequestParam long id){
		try {
			questionService.deleteById(id);
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
			questionService.batchDelete(ids);
			return MsgResponse.success("success", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
}
