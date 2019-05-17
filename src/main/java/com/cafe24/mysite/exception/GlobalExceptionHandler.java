package com.cafe24.mysite.exception;
//부모가 없는class는 POJO

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	//시스템 외적인 것이므로 기술침투는 어느정도 허용 (Controller , model ,view)외적인것.
	@ExceptionHandler(Exception.class)
	public void handlerException(HttpServletRequest request,
			HttpServletResponse response , Exception e) throws ServletException, IOException {
		
		//1.logging
		
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			//LOGGER.error(errors.toString());
			System.out.println(errors.toString());
			
		//2.안내페이지 가기 + 정상종료(response)
			
		request.setAttribute("uri", request.getRequestURI());
		request.setAttribute("exception", errors.toString());
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		
		
	
	}
	
	
}
