package com.KoreaIT.java.AM.dto;

public class Member {
	public int id;
	public String signupdate;
	public String loginId;
	public String loginPW;
	public String name;
	public String callnumber;

	public Member(int id, String signupdate, String loginId, String loginPW, String name, String callnumber) {
		this.id = id;
		this.signupdate = signupdate;
		this.loginId = loginId;
		this.loginPW = loginPW;
		this.name = name;
		this.callnumber = callnumber;
	}
}