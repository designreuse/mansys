package com.wicky.biz.web.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wicky.biz.entity.ResourceVO;
import com.wicky.biz.entity.UserVO;
import com.wicky.biz.service.IResourceService;
import com.wicky.biz.service.IUserService;
import com.wicky.biz.web.bind.annotation.CurrentUser;

@Controller
public class DashboardsController {
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public String index(@CurrentUser UserVO loginUser, Model model) {
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<ResourceVO> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "dashboards/index";
    }

    public String dashboard_1() {
        return "dashboard_1";
    }
    
    public String dashboard_2() {
    	return "dashboard_2";
    }
    
    public String dashboard_3() {
    	return "dashboard_3";
    }
    
    public String dashboard_4() {
    	return "dashboard_4";
    }
    
    public String dashboard_4_1() {
    	return "dashboard_4_1";
    }
    
    public String dashboard_5() {
    	return "dashboard_5";
    }

}
