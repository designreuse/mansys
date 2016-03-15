package com.wicky.biz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wicky.biz.entity.UserVO;
import com.wicky.biz.web.bind.annotation.CurrentUser;

@RequestMapping("/samples")
@Controller
public class SamplesController {

	@RequestMapping({"", "/", "index", "/other"})
    public String index(@CurrentUser UserVO loginUser, Model model) {
		System.out.println("!index!");
        return "samples/tags_sample";
    }
	
}
