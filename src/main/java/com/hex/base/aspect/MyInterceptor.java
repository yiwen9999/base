package com.hex.base.aspect;

import com.hex.base.domain.Operator;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * User: hexuan
 * Date: 2017/8/15
 * Time: 下午1:07
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    // 拦截判断session中是否存在operator，不存在跳转toLogin
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag = true;
        Operator operator = (Operator) httpServletRequest.getSession().getAttribute("operator");
        if (null == operator) {
            httpServletResponse.sendRedirect("toLogin");
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
