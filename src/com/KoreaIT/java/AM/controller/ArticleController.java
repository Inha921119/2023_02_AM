package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Util.Util;
import com.KoreaIT.java.AM.dto.Article;

@SuppressWarnings("unused")

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
			System.out.println("존재하지 않는 명령어 입니다.");;
			break;
		}
	}
		
	private void showList() {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
		} else {
			System.out.println("|번호	|제목		|날짜		|작성자		|조회수		");
			for (int i = articles.size() - 1; i >= 0; i--) {
				Article article = articles.get(i);
				System.out.printf("|%d	|%s		|%s	|%s		|%d		\n", article.id, article.title,
						article.regDate.substring(0, 10), article.writer, article.hit);
			}
		}
	}
	private void doWrite() {
		int id = lastArticleId + 1;
		System.out.printf("제목 : ");
		String title = sc.nextLine();

		System.out.printf("내용 : ");
		String body = sc.nextLine();

		String regDate = Util.getNowDateTimeStr();

		Article article = new Article(id, regDate, title, body);

		article.writer = MemberController.foundMember.loginId;

		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);
		lastArticleId++;
	}
	
	private void showDetail () {
		if (command.split(" ").length == 2) {
			System.out.println("detail 뒤에 번호를 입력해주세요");
			return;
		} else if (command.split(" ")[2].matches("[^0-9]+")) {
			System.out.println("detail 뒤에 숫자만 입력해주세요");
			return;
		}

		String cmdBits = command.split(" ")[2];
		int id = Integer.parseInt(cmdBits);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		if (MemberController.foundMember.loginId != foundArticle.writer) {
			foundArticle.increaseHit();
		}

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("작성자 : %s\n", foundArticle.writer);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		if (foundArticle.LastModifyDate != null) {
			System.out.printf("수정된 날짜 : %s\n", foundArticle.LastModifyDate);
		}
		System.out.printf("조회수 : %d회\n", foundArticle.hit);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);

	}
	private void doDelete () {
		if (command.split(" ").length == 2) {
			System.out.println("delete 뒤에 번호를 입력해주세요");
			return;
		} else if (command.split(" ")[2].matches("[^0-9]+")) {
			System.out.println("delete 뒤에 숫자만 입력해주세요");
			return;
		}

		String cmdBits = command.split(" ")[2];
		int id = Integer.parseInt(cmdBits);

		int foundIndex = getArticleIndexById(id);

		if (articles.get(foundIndex).writer == MemberController.foundMember.loginId) {
			articles.remove(foundIndex);
			System.out.printf("%d번 게시물이 삭제되었습니다\n", id);
		} else {
			System.out.println("삭제할 권한이 없습니다");
		}
		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
	}
	
	private void doModify () {
		if (command.split(" ").length == 2) {
			System.out.println("modify 뒤에 번호를 입력해주세요");
			return;
		} else if (command.split(" ")[2].matches("[^0-9]+")) {
			System.out.println("modify 뒤에 숫자만 입력해주세요");
			return;
		}

		String cmdBits = command.split(" ")[2];
		int id = Integer.parseInt(cmdBits);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		if (foundArticle.writer == MemberController.foundMember.loginId) {
			System.out.printf("제목 : %s\n", foundArticle.title);
			System.out.printf("내용 : %s\n", foundArticle.body);
			System.out.printf("%d번 게시물의 '제목'과 '내용'중 무엇을 수정하시겠습니까?\n", id);
			String command_modify = sc.nextLine().trim();

			if (command_modify.equals("제목")) {
				System.out.printf("%d번 게시물의 제목을 수정합니다\n", id);
				System.out.printf("제목 : ");
				String title = sc.nextLine();

				foundArticle.title = title;

				String LastModifyDate = Util.getNowDateTimeStr();

				foundArticle.LastModifyDate = LastModifyDate;

				System.out.printf("%d번 게시물의 제목이 수정되었습니다\n", id);

			} else if (command_modify.equals("내용")) {
				System.out.printf("%d번 게시물의 내용을 수정합니다\n", id);
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				foundArticle.body = body;

				String LastModifyDate = Util.getNowDateTimeStr();
				foundArticle.LastModifyDate = LastModifyDate;

				System.out.printf("%d번 게시물의 내용이 수정되었습니다\n", id);
				return;
			} else {
				System.out.println("'제목' 혹은 '내용'을 입력해주세요");
			}
		}
		if (foundArticle.writer != MemberController.foundMember.loginId) {
			System.out.println("편집할 권한이 없습니다");
			return;
		}
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

	public void makeTestData() {
		System.out.println("테스트를 위한 게시물을 생성합니다");
		articles.add(new Article(1, Util.getNowDateTimeStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateTimeStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateTimeStr(), "제목3", "내용3", 33));
	}
	}
