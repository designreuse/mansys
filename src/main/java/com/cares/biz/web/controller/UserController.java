package com.cares.biz.web.controller;

import com.cares.biz.entity.RoleVO;
import com.cares.biz.service.IOrganizationService;
import com.cares.biz.service.IRoleService;
import com.cares.biz.service.IUserService;
import com.cares.biz.entity.OrganizationVO;
import com.cares.biz.entity.UserVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IOrganizationService organizationService;
    @Autowired
    private IRoleService roleService;

    @RequiresPermissions("user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "user/list";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("user", new UserVO());
        model.addAttribute("op", "新增");
        return "user/edit";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(UserVO user, RedirectAttributes redirectAttributes) {
        userService.createUser(user);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/user";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        UserVO user = userService.findOne(id);
        if (!CollectionUtils.isEmpty(user.getRoleList())) {
            StringBuilder sb = new StringBuilder();
            for (RoleVO role : user.getRoleList()) {
                sb.append(role.getId());
                sb.append(",");
            }
            user.setRoleIds(sb.toString().substring(0, sb.toString().length() - 1));
        }

        model.addAttribute("user", user);
        model.addAttribute("op", "修改");
        return "user/edit";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(UserVO user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/user";
    }

//    @RequiresPermissions("user:delete")
//    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
//    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
//        setCommonData(model);
//        model.addAttribute("user", userService.findOne(id));
//        model.addAttribute("op", "删除");
//        return "user/edit";
//    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/user";
    }


    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "修改密码");
        return "user/changePassword";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") Long id, String newPassword, RedirectAttributes redirectAttributes) {
        userService.doChangePassword(id, newPassword);
        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return "redirect:/user";
    }

    @RequiresPermissions("user:view")
    @RequestMapping(value = "/{id}/findUserVO")
    public @ResponseBody UserVO findUserVO(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }

    private void setCommonData(Model model) {
        try {
            model.addAttribute("organizationList", organizationService.findAll());
            model.addAttribute("roleList", roleService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
