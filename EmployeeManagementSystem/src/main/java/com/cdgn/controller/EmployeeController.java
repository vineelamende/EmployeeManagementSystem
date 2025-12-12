package com.cdgn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdgn.model.Employee;
import com.cdgn.service.EmployeeService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	@RequestMapping("/saveuser")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		Employee emp = service.saveEmployee(employee);
		ModelAndView mav = new ModelAndView("register.jsp");
		if(emp!=null) {
			mav.addObject("status","employee registered successfully...");
		}
		else {
			mav.addObject("status","employee registration failed...");
		}
		return mav;	
	}
	@RequestMapping("/emplogin")
	public ModelAndView login(@RequestParam String email,@RequestParam String password,HttpSession session) {
		boolean validate = service.existsByEmailAndPassword(email,password);
		ModelAndView mav;
		if(email.equals("admin@codegnan.com")&&password.equals("admin@cg")) {
			mav=new ModelAndView("admin.jsp");
			session.setAttribute("email",email);
		}
		else {
			if(validate) {
				mav=new ModelAndView("view");
				session.setAttribute("email",email);
			}
			else {
				mav=new ModelAndView("login.jsp");
				mav.addObject("employee","Invalid credentials");
			}
		}
		return mav;
	}
	@RequestMapping("/findAll")
	public ModelAndView findAllEmployees(@ModelAttribute Employee employee) {
		List<Employee> emplist = service.findAll();
		ModelAndView mav = new ModelAndView("viewemps.jsp");
		mav.addObject("emplist",emplist);
		return mav;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteById(@RequestParam int id) {
		service.deleteById(id);
		ModelAndView mav = new ModelAndView("findAll");
		return mav;
}
	@RequestMapping("/edituser")
	public ModelAndView updateEmployee(@ModelAttribute Employee Employee ) {
		Employee employee = service.saveEmployee(Employee);
		ModelAndView mav = new ModelAndView("findAll");
		mav.addObject("employee",employee);
		return mav;
	}
	@RequestMapping("/search")
	public ModelAndView findById(@RequestParam int id) {
		Employee employee= service.findById(id);
		 
		if(employee !=null) {
			ModelAndView mav = new ModelAndView("viewemp.jsp");
			mav.addObject("employee",employee);
			return mav;
		}
		else {
			ModelAndView mav = new ModelAndView("search.jsp");
			 mav.addObject("employee","Invalid ID");
			 return mav;
		}
	}
	
	@RequestMapping("/emplogout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView("login.jsp");
		return mav;
	}
	
	@RequestMapping("/view")
	public ModelAndView viewProfile(@RequestParam String email,@RequestParam String password) {
		Employee employee= service.findByEmailAndPassword(email,password);
		 
		if(employee !=null) {
			ModelAndView mav = new ModelAndView("userprofile.jsp");
			mav.addObject("employee",employee);
			return mav;
		}
		else {
			ModelAndView mav = new ModelAndView("login.jsp");
			 mav.addObject("employee","Invalid ID");
			 return mav;
		}
	}
}