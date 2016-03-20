package com.wicky.biz.web.controller.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wicky.biz.web.layouts.PipelineHolder;

@RequestMapping("/cache")
@Controller
public class CacheController {

	@RequiresPermissions("admin/cache:reload")
    @RequestMapping("/reload")
    public String reload() {
    	PipelineHolder.getInstance().reload();
        return "admin/cache/success";
    }
}
