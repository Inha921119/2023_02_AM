package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class ArticleController extends Controller {

	private List<Article> articles;
	private Scanner sc;
	private String command;
	private String actionMethodName;

	public ArticleController(Scanner sc) {
		this.articles = new ArrayList<>();
		this.sc = sc;
	}

	int lastArticleId = 3;

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "write":
			doWrite();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;
		}
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다");
		articles.add(new Article(1, Util.getNowDateTimeStr(), 2, "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateTimeStr(), 1, "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateTimeStr(), 3, "제목3", "내용3", 33));
	}

	private void showList() {

		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
		} else {
			System.out.println("   번호     /     제목       /   조회    /    작성자");
			for (int i = articles.size() - 1; i >= 0; i--) {
				Article article = articles.get(i);

				String writerName = null;

				List<Member> members = Controller.members;

				for (Member member : members) {
					if (article.memberId == member.id) {
						writerName = member.name;
						break;
					}
				}

				System.out.printf("  %4d    /   %7s      /  %4d      /  %4s    \n", article.id, article.title,
						article.hit, writerName);
			}
		}

	}

	private void doWrite() {
		int id = lastArticleId + 1;
		String regDate = Util.getNowDateTimeStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, loginedMember.id, title, body);
		articles.add(article);

		System.out.println(id + "번 글이 생성되었습니다");
		lastArticleId++;

	}

	private void showDetail() {

		String[] cmdBits = command.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		String writerName = null;

		for (Member member : members) {
			if (foundArticle.memberId == member.id) {
				writerName = member.name;
				break;
			}
		}

		foundArticle.increaseHit();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("작성자 : %s\n", writerName);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회수 : %d\n", foundArticle.hit);

	}

	private void doModify() {
		String[] cmdBits = command.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("권한이 없습니다");
			return;
		}

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번 글을 수정했습니다.\n", id);

	}

	private void doDelete() {
		String[] cmdBits = command.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("권한이 없습니다");
			return;
		}

		articles.remove(foundArticle);
		System.out.printf("%d번 글을 삭제했습니다.\n", id);

	}

	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Article getArticleById(int id) {
		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

}