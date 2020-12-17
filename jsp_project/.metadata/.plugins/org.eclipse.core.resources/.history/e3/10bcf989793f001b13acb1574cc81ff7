<%@page import="form.loginFormData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	loginFormData loginData = new loginFormData();

	String userId = request.getParameter("userid");
	String userPw = request.getParameter("pw");

	loginData.setUserId(userId);
	loginData.setUserPw(userPw);
	
	request.setAttribute("loginData", loginData);
%>
<jsp:forward page="loginView.jsp"></jsp:forward>