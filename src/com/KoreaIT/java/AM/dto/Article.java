package com.KoreaIT.java.AM.dto;

public class Article extends Dto {
	public String title;
	public String body;
	public int hit;
	public String writer;
	public String LastModifyDate;

	public Article(int id, String regDate, String writer, String title, String body) {
		this(id, regDate, writer, title, body, 0);
	}

	public Article(int id, String regDate, String writer, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
		this.writer = writer;
	}

	public void increaseHit() {
		this.hit++;
	}

}