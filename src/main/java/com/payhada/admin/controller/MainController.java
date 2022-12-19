package com.payhada.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class MainController {


    @GetMapping(value="/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        log.debug("APPLICATION ROOT!");
        return "login";
    }

    @RequestMapping(value={"/login"})
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
        
        return "index_backup";
    }

    @RequestMapping(value={"/404"})
    public String errorPage(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("[MainController : errorPage] START");	

		model.addAttribute("text","1212121212");
        
        return "error/maintenance";
    }
}

