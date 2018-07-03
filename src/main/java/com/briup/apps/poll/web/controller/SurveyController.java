package com.briup.apps.poll.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.service.IAnswersService;
import com.briup.apps.poll.service.ISurveyService;
import com.briup.apps.poll.util.MsgResponse;
import com.briup.apps.poll.vm.SurveyAndAnswersVM;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="课调相关接口")
@RestController
@RequestMapping("/survey")
public class SurveyController {
	
	@Autowired
	private ISurveyService surveyService;
	@Autowired
	private IAnswersService answersServie;
	
	@ApiOperation(value="查询所有数据")
	@GetMapping("findAllSurvey")
	public MsgResponse findAllSurvey(){
		try {
			List<Survey> list =  surveyService.findAll();
			//返回成功信息
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			//返回错误信息
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="查询所有课调信息",notes="级联查询课程中的课程，班级，用户，问卷")
	@GetMapping("findAllSurveyVM")
	public MsgResponse findAllSurveyVM(){
		try {
			List<SurveyVM> list = surveyService.findAllVM();
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
			SurveyVM surveyVM = surveyService.findById(id);
			return MsgResponse.success("success", surveyVM);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="更新或插入数据",notes="不输入id时执行插入操作，输入id时执行更新操作")
	@PostMapping("saveOrUpdate")
	public MsgResponse saveOrUpdate(Survey Survey){
		try {
			surveyService.saveOrUpdate(Survey);
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
			surveyService.deleteById(id);
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
			surveyService.batchDelete(ids);
			return MsgResponse.success("success", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="开启课调",notes="只有在课调状态为未开启的时候才能开启课调")
	@GetMapping("beginSurvey")
	public MsgResponse beginSurvey(long id){
		try {
			Survey survey = surveyService.findSurveyById(id);
			if(survey.getStatus().equals(Survey.STATUS_INIT)){
				survey.setStatus(Survey.STATUS_BEGIN);
				survey.setCode(survey.getId().toString());
				surveyService.saveOrUpdate(survey);
				return MsgResponse.success("开启成功", survey);
			}else{
				return MsgResponse.error("当前课调已开启");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="审核课调",notes="返回课调的信息以及课调下所有答卷信息")
	@GetMapping("toCheckSurvey")
	public MsgResponse toCheckSurvey(long id){
		try {
			SurveyVM surveyVM = surveyService.findById(id);
			
			if(surveyVM.getStatus().equals(Survey.STATUS_CHECK_UN)){
				List<Answers> list = answersServie.findAnswersBySurveyId(id);
				double total = 0;
				for(Answers answer : list){
					String[] arr = answer.getSelections().split("[|]");
					double singleTotal = 0;
					for(String a : arr){
						singleTotal += Integer.parseInt(a);
					}
					double singleAvarage = singleTotal/arr.length;
					total += singleAvarage;
				}
				double average = total/list.size();
				surveyVM.setAverage(average);
				Survey survey = surveyService.findSurveyById(id);
				//如果数据库中的平均分没有设定，我们再进行设定，否则不做操作
				if(survey.getAverage()== null){
					survey.setAverage(average);
					surveyService.saveOrUpdate(survey);
				}
				
				//如何将surveyVM 和list 返回,封装到一个对象中
				SurveyAndAnswersVM savm = new SurveyAndAnswersVM();
				savm.setSurveyVM(surveyVM);
				savm.setAnswers(list);
				return MsgResponse.success("success", savm);
				
			}else{
				return MsgResponse.error("课调状态不合法");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="关闭课调",notes="只有在课调状态为开启的时候才能关闭课调")
	@GetMapping("stopSurvey")
	public MsgResponse stopSurvey(long id){
		try {
			//1. 通过id查询出课调
			Survey survey = surveyService.findSurveyById(id);
			if(survey!=null && survey.getStatus().equals(Survey.STATUS_BEGIN)){
				survey.setStatus(Survey.STATUS_CHECK_UN);
				surveyService.saveOrUpdate(survey);
				return MsgResponse.success("关闭课调成功",null);
			} else {
				return MsgResponse.error("当前课调状态必须为未开启状态");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
}
