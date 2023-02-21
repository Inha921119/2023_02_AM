package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.Controller;
import com.KoreaIT.java.AM.controller.MemberController;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;

public class App {

	private static List<Article> articles;
	private static List<Member> members;

	static {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);
		
		Controller controller;

		articleController.makeTestData();
		

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해 주세요.");
				continue;
			}
			if (command.equals("exit")) {
				break;
			}
			
			String[] cmdBits = command.split(" ");
			String controllerName = cmdBits[0];
			
			if(cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요");
				continue;
			}
			
			String actionMethodName = cmdBits[1];
			controller = null;
			
			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			}
			else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			
			controller.doAction(command, actionMethodName);

//			if (command.equals("member join")) {
//
//				memberController.doJoin();
//
//			}
//
//			if (command.equals("login")) {
//				
//				memberController.doLogin();
//				
//			}
//
//			if (command.equals("logout")) {
//				
//				memberController.doLogout();
//				
//			}
//
//			if (MemberController.foundMember == null) {
//				System.out.println("로그인을 해주세요");
//				continue;
//			}
//
//			if (command.equals("article list")) {
//
//				articleController.showList(command);
//				
//			} else if (command.equals("article write")) {
//				
//				articleController.doWrite(command);
//
//			} else if (command.startsWith("article detail")) {
//				
//				articleController.showDetail(command);
//
//			} else if (command.startsWith("article delete")) {
//				
//				articleController.doDelete(command);
//
//			} else if (command.startsWith("article modify")) {
//				
//				articleController.doModify(command);
//
//			} else {
//			}
		}
		System.out.println("== 프로그램 끝 ==");

		sc.close();
	}

	
}