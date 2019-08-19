package com.antybeety.news.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler
{

    private String id ="id";     // 로그인 id값이 들어오는 input 태그 name
    private String password="password";   // 로그인 password 값이 들어오는 input 태그 name
    private String loginredirectname="";   // 로그인 성공시 redirect 할 URL이 지정되어 있는 input 태그 name
    private String exceptionmsgname="error";    // 예외 메시지를 request의 Attribute에 저장할 때 사용될 key 값
    private String defaultFailureUrl="/admin/login";   // 화면에 보여줄 URL(로그인 화면)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String loginid = request.getParameter(id);
        String loginpasswd = request.getParameter(password);
        String loginRedirect = request.getParameter(loginredirectname);
        System.out.println("man");
        request.setAttribute(id, loginid);
        request.setAttribute(password, loginpasswd);
        request.setAttribute(loginredirectname, loginRedirect);


        // Request 객체의 Attribute에 예외 메시지 저장
        request.setAttribute(exceptionmsgname, e.getMessage());
        System.out.println(exceptionmsgname);
        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
    }
}
