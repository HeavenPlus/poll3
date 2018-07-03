package com.briup.apps.poll.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.poll.bean.Answers;
import com.briup.apps.poll.bean.Survey;
import com.briup.apps.poll.bean.extend.SurveyVM;
import com.briup.apps.poll.service.IAnswersService;
import com.briup.apps.poll.service.ISurveyService;
import com.briup.apps.poll.util.MsgResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="学生相关接口")
@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private ISurveyService surveyService;
	@Autowired
	private IAnswersService answersService;
	
	@ApiOperation(value="登录课调")
	@GetMapping("loginSurvey")
	public MsgResponse loginSurvey(long id){
		try {
			SurveyVM surveyVM = surveyService.findById(id);
			if(surveyVM!=null&&surveyVM.getStatus().equals(Survey.STATUS_BEGIN)){
				return MsgResponse.success("success", surveyVM);
			}else{
				return MsgResponse.error("课调状态不合法");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="提交答卷")
	@PostMapping("submitAnswer")
	public MsgResponse submitAnswer(Answers answers){
		try {
			answersService.saveOrUpdate(answers);
			return MsgResponse.success("success", "您的意见已收到");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
}
