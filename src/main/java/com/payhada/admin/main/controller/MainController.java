package com.payhada.admin.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController { 
    
	private static final Logger log = LoggerFactory.getLogger(MainController.class);


    @RequestMapping(value={"/", "/login"})
    public String loginPage(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("[MainController : loginPage] START");	

        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

		model.addAttribute("text","1212121212");
        
        return "login";
    }

    @RequestMapping(value={"/main"})
    public String mainPage(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("[MainController : mainPage] START");	

		model.addAttribute("text","1212121212");
        
        return "index";
    }
}

