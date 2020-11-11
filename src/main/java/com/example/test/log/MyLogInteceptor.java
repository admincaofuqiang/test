package com.example.test.log;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.lang.reflect.Method;

public class MyLogInteceptor extends HandlerInterceptorAdapter {
    private static final ThreadLocal<Long> threadLocal=new ThreadLocal<Long>();
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        MyLog annotation = method.getAnnotation(MyLog.class);
        if(annotation!=null){
            long l = System.currentTimeMillis();
            threadLocal.set(l);
        }
        return true;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handler1 = (HandlerMethod) handler;
        Method method = handler1.getMethod();
        MyLog annotation = method.getAnnotation(MyLog.class);
        if(annotation!=null){
            Long aLong = threadLocal.get();
            long l = System.currentTimeMillis();
            long l1 = l - aLong;
            StringBuffer requestURL = request.getRequestURL();
            String s = method.getDeclaringClass().getName() + "     " + method.getName();
            String desc = annotation.desc();
            System.out.println("-------------------------------------------");
            System.out.println("描述：  "+desc);
            System.out.println("耗时：  "+l1);
            System.out.println("请求路径："+requestURL.toString());
            System.out.println("请求控制器所在位置及请求方法："+s);
            String s1 = JSONObject.toJSONString(request.getParameterMap());
            System.out.println("请求参数："+s1);
        }
    }
}
