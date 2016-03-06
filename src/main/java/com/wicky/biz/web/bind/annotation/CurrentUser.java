package com.wicky.biz.web.bind.annotation;


import java.lang.annotation.*;

import com.wicky.biz.web.Constants;

/**
 * <p>绑定当前登录的用户</p>
 * <p>不同于@ModelAttribute</p>
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

    /**
     * 当前用户在request中的名字
     * @return
     */
    String value() default Constants.CURRENT_USER;

}
