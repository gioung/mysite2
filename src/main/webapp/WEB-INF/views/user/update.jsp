<%@page import="com.cafe24.mysite.repository.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="POST" action="${pageContext.servletContext.contextPath }/user/update">
					
					<input type="hidden" name="no" value="${userVo.no} ">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name }">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${userVo.email }" readonly>
					
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="${userVo.password }">
					
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
	<c:if test='${result == "success" }'>
	<script>
		alert( "정상적으로 수정 하였습니다." );
	</script>
</c:if>
</body>
</html>