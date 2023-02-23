package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.dto.Member;

public abstract class Controller {
	public abstract void doAction(String command, String actionMethodName);
	
	public abstract void makeTestData(); 
	
	protected static Member loginedMember = null;
	
	protected boolean isLogined() {
		return loginedMember != null;
	}
}
