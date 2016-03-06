package com.wicky.biz.web.controller;

import com.wicky.biz.entity.ResourceVO;
import com.wicky.biz.entity.UserVO;
import com.wicky.biz.service.IResourceService;
import com.wicky.biz.service.IUserService;
import com.wicky.biz.web.bind.annotation.CurrentUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

/**
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
public class IndexController {
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public String index(@CurrentUser UserVO loginUser, Model model) {
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<ResourceVO> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
