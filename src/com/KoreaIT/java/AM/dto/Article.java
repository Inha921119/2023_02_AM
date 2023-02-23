package com.KoreaIT.java.AM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int hit;
	public int memberId;

	public Article(int id, String regDate, int memberId, String title, String body) {
		this(id, regDate, memberId, title, body, 0);
	}

	public Article(int id, String regDate, int memberId, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
		this.memberId = memberId;
	}

	public void increaseHit() {
		this.hit++;
	}

}