package com.ch018.library.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController {
	
	@RequestMapping("error")
	public String customError(HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String exceptionMessage = getExceptionMessage(throwable, statusCode);
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		String message = MessageFormat.format("{0} returned for {1} with message {3}",
				statusCode, requestUri, exceptionMessage);
		model.addAttribute("errorMessage", message);
		return "error";
	}
	
	@RequestMapping("error404")
	public String notFound(Model model) {
		String message = "404 the page you are request is not found.";
		model.addAttribute("errorMessage", message);
		return "error404";
	}
	
	@RequestMapping("error403")
	public String accessDenied(Model model) {
		String message = "Access is denied.";
		model.addAttribute("errorMessage", message);
		return "error403";
	}
	
	@RequestMapping("error400")
	public String badRequest(Model model) {
		String message = "Bad request.";
		model.addAttribute("errorMessage", message);
		return "error400";
	}
	
	private String getExceptionMessage(Throwable throwable, Integer statusCode) {
		if (throwable != null) {
			return throwable.getMessage();
		}
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		return httpStatus.getReasonPhrase();	
	}

}
