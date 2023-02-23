package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.AM.dto.Member;

public abstract class Controller {

	public static List<Member> members = new ArrayList<>();

	public static Member loginedMember = null;

	public static boolean isLogined() {
		return loginedMember != null;
	}

	public abstract void doAction(String command, String actionMethodName);

	public abstract void makeTestData();
}