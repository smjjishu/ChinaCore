package com.example.project.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * app中api请求验签
 */
@Component
public class AllHttpInterceptor implements HandlerInterceptor {

    /**
     * 请求方式
     */
    public static final String OPTIONS = "options";

    /**
     * 头部名称
     */
    public static final String BASIC_AUTH = "BasicAuth ";

    /**
     * 密钥名称
     */
    public static final String SECRET = "secret";

    /**
     * 编码方式
     */
    public static final String CHARSET_NAME = "utf-8";

    /**
     * 头部存放值
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 是否开启验签校验
     */
    @Value("${appsigninfo.issign}")
    public Boolean isOpenSign;

    /**
     * 验签key
     */
    @Value("${appsigninfo.key}")
    private String BASIC_USER_NAME;

    /**
     * 验签secret
     */
    @Value("${appsigninfo.secret}")
    private String BASIC_SIGN_SECRET;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception { }

    /**
     * 验签校验
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断验签校验是否开启
    /*    if (!isOpenSign) {
            return true;
        }*/
        System.out.println("判断验签校验是否开启和判断验签校验是正确！");
        Object oo=request;
        return true;
    }

    /**
     * 校验accessToken值是否校验通过
     */


    /**
     * 检查token，防止redis挂了，异常情况直接返回true
     */


    /**
     * 创建验签
     */


    /**
     * 返回请求结果，true，false
     */


    /**
     * 获取json格式结果
     */


    /**
     * 判断本地组装的编码值和请求头部的编码值是否相同
     */


    /**
     * 获取 post 请求内容
     */

}

