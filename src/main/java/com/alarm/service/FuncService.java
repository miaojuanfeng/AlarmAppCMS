package com.alarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface FuncService {
	public boolean isNumeric(String str);
	public int getItemIndexOfArray(Object[] array , Object item);
	public void modelAttribute(Model model, HttpSession httpSession, HttpServletRequest request);
}
