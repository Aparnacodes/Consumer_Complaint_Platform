package com.accenture.lkm.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.ComplaintBean;
import com.accenture.lkm.service.ComplaintService;

@Controller
public class ComplaintRegisterController {

	@Autowired
	private ComplaintService complaintService;

	@RequestMapping("loadComplaintPage")
	public ModelAndView loadComplaintForm() {
		return new ModelAndView("ComplaintPage", "complaintBean", new ComplaintBean());
	}

	@RequestMapping(value="/complaintForm", method=RequestMethod.POST)
	public ModelAndView processComplaintForm(@ModelAttribute("complaintBean") @Valid ComplaintBean complaintBean,BindingResult result) throws Exception {
		 // Your implementation goes here
		ModelAndView mv = new ModelAndView();
		if(result.hasErrors()) {
			mv.setViewName("ComplaintPage");
		}else {
			complaintService.registerComplaintDetails(complaintBean);
			mv.setViewName("Success");
			mv.addObject("message", "Hi "+complaintBean.getCustomerName() +"  your complaint is successfully registered.");
		}
        return mv;
	}

	@ModelAttribute("complaintTypes")
	public Map<Integer, String> getAllComplaintTypes() {
        return complaintService.getAllComplaintTypes();
	}
	@ExceptionHandler
	public ModelAndView handleAllExceptions(Exception exception) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("GeneralizedExceptionHandlerPage");
		mv.addObject("message", exception.getMessage());
        return mv;
	}
}
