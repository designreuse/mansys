package com.wicky.biz.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
        	error = "该账号已锁定，请一小时后重试";
        } else if(AuthenticationException.class.getName().equals(exceptionClassName)) {
        	error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "发生未知错误，请稍后重试";
            System.err.println("!!! 登陆发生未知错误：" + exceptionClassName);
        }
        model.addAttribute("error", error);
        return "admin/login";
    }

}
