package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static int lastArticleId = 0;
	public static List<Article> articles;

	static {
		articles = new ArrayList<>();
	}

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

//		int lastSignUpId = 0;
//		List<Member> members = new ArrayList<>();

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

//			if (command.equals("sign up")) {
//				int idNum = lastSignUpId + 1;
//				System.out.printf("아이디 : ");
//				String id = sc.nextLine();
//				System.out.printf("비밀번호 : ");
//				String PassWords = sc.nextLine();
//				System.out.printf("이름 : ");
//				String Name = sc.nextLine();
//				System.out.printf("전화번호 : ");
//				String PhoneNumber = sc.nextLine();
//
//				String SignUpDate = Util.getNowDateTimeStr();
//
//				Member member = new Member(idNum, SignUpDate, id, PassWords, Name, PhoneNumber);
//				members.add(member);
//
//				System.out.printf("%s님 가입이 완료되었습니다\n", id);
//				lastSignUpId++;
//				continue;
//			}
//
//			if (command.equals("login")) {
//				String id;
//				String PassWords;
//				Member foundMember;
//
//				while (true) {
//					System.out.printf("아이디 : ");
//					id = sc.nextLine();
//					System.out.printf("비밀번호 : ");
//					PassWords = sc.nextLine();
//
//					foundMember = null;
//
//					for (int i = 0; i < members.size(); i++) {
//						Member member = members.get(i);
//						if (id.equals(member.id) && PassWords.equals(member.passwords)) {
//							foundMember = member;
//							System.out.printf("로그인되었습니다\n");
//							foundMember.logindate = Util.getNowDateTimeStr();
//							break;
//						}
//					}
//
//					if (foundMember == null) {
//						System.out.printf("로그인에 실패하였습니다.\n아이디나 비밀번호를 확인해주세요.\n");
//						break;
//					}
//				}
//				continue;
//			}

			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
				} else {
					System.out.println(" 번호	/	제목	/	조회수");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf(" %d	/	%s	/	%d\n", article.id, article.title, article.viewcount);
					}
				}

			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String regDate = Util.getNowDateTimeStr();
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);
				articles.add(article);

				System.out.println(id + "번 글이 생성되었습니다");
				lastArticleId++;
			} else if (command.startsWith("article detail ")) {
				if (command.split(" ").length == 2) {
					System.out.println("detail 뒤에 번호를 입력해주세요");
					continue;
				} else if (command.split(" ")[2].matches("[^0-9]+")) {
					System.out.println("detail 뒤에 숫자만 입력해주세요");
					continue;
				}

				String[] cmdBits = command.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				foundArticle.increaseView();

				System.out.printf("번호	: %d\n", foundArticle.id);
				System.out.printf("날짜	: %s\n", foundArticle.regDate);
				System.out.printf("조회수	: %s\n", foundArticle.viewcount);
				if (foundArticle.ModifyDate != null) {
					System.out.println("수정된 날짜	: " + foundArticle.ModifyDate);
				}
				System.out.printf("제목	: %s\n", foundArticle.title);
				System.out.printf("내용	: %s\n", foundArticle.body);

			} else if (command.startsWith("article delete")) {
				if (command.split(" ").length == 2) {
					System.out.println("delete 뒤에 번호를 입력해주세요");
					continue;
				} else if (command.split(" ")[2].matches("[^0-9]+")) {
					System.out.println("delete 뒤에 숫자만 입력해주세요");
					continue;
				}

				String cmdBits = command.split(" ")[2];
				int id = Integer.parseInt(cmdBits);

				int foundIndex = -1;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				articles.remove(foundIndex);

				System.out.printf("%d번 게시물이 삭제되었습니다\n", id);
			} else if (command.startsWith("article modify")) {
				if (command.split(" ").length == 2) {
					System.out.println("modify 뒤에 번호를 입력해주세요");
					continue;
				} else if (command.split(" ")[2].matches("[^0-9]+")) {
					System.out.println("modify 뒤에 숫자만 입력해주세요");
					continue;
				}
				String cmdBits = command.split(" ")[2];
				int id = Integer.parseInt(cmdBits);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
				System.out.printf("%d번 게시물의 제목과 내용중 무엇을 수정하시겠습니까?\n", id);
				String command_modify = sc.nextLine().trim();
				if (command_modify.equals("제목")) {
					System.out.printf("%d번 게시물의 제목을 수정합니다\n", id);
					System.out.printf("제목 : ");
					String title = sc.nextLine();

					String ModifyDate = Util.getNowDateTimeStr();

					foundArticle.title = title;
					foundArticle.ModifyDate = ModifyDate;

					System.out.printf("%d번 게시물의 제목이 수정되었습니다\n", id);

				} else if (command_modify.equals("내용")) {
					System.out.printf("%d번 게시물의 내용을 수정합니다\n", id);
					System.out.printf("내용 : ");
					String body = sc.nextLine();

					String ModifyDate = Util.getNowDateTimeStr();

					foundArticle.body = body;
					foundArticle.ModifyDate = ModifyDate;

					System.out.printf("%d번 게시물의 내용이 수정되었습니다\n", id);

				} else {
					System.out.println("'제목' 혹은 '내용'을 입력해주세요");
				}
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}

		System.out.println("==프로그램 끝==");

		sc.close();

	}

	static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다");

		for (int i = 0; i < 3; i++) {
			int id = lastArticleId + 1;
			String regDate = Util.getNowDateTimeStr();
			String title = "제목" + (i + 1);
			String body = "내용" + (i + 1);

			Article article = new Article(id, regDate, title, body);
			articles.add(article);

			lastArticleId++;
		}
	}
}

class Article {
	int id;
	String regDate;
	String title;
	String body;
	String ModifyDate;
	int viewcount;

	Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.viewcount = 0;
	}

	void increaseView() {
		this.viewcount++;
	}
}
//
//class Member {
//	int idnum;
//	String signupdate;
//	String id;
//	String passwords;
//	String name;
//	String phonenumber;
//	String logindate;
//
//	Member(int idnum, String signupdate, String id, String name, String passwords, String phonenumber) {
//		this.idnum = idnum;
//		this.signupdate = signupdate;
//		this.id = id;
//		this.passwords = passwords;
//		this.name = name;
//		this.phonenumber = phonenumber;
//	}
//}