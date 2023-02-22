package com.KoreaIT.java.AM.dto;

public class Member extends Dto {
	public String loginId = null;
	public String loginPw = null;
	public String name = null;

	public Member(int id, String regDate, String loginId, String loginPw, String name) {
		this.id = id;
		this.regDate = regDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}

}
