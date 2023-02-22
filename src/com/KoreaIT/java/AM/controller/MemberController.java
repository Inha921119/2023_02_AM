package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.Util.Util;
import com.KoreaIT.java.AM.dto.Member;

public class MemberController extends Controller {
	
	private List<Member> members;
	private Scanner sc;
	public static Member foundMember = null;
	public static boolean logincheck = false;
	private String command;
	public String actionMethodName;

	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
	}
	int lastMemberId = 0;
	
	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;
				
		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");;
			break;
		}
	}

	
	private void doJoin() {
		int id = lastMemberId + 1;
		String regDate = Util.getNowDateTimeStr();
		String loginId = null;

		while (true) {

			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.println("이미 사용중인 아이디입니다");
				continue;
			}

			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 재확인: ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}
		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.println(id + "번 회원이 가입되었습니다");
		lastMemberId++;
	}
	
	private void doLogin() {
		if (logincheck == false) {
			System.out.printf("아이디 : ");
			String id = sc.nextLine();

			System.out.printf("비밀번호 : ");
			String passwords = sc.nextLine();

			for (Member member : members) {
				if (id.equals(member.loginId) && passwords.equals(member.loginPw)) {
					foundMember = member;
					logincheck = true;
					System.out.println("로그인 되었습니다");
					break;
				}
			}
			
			if (foundMember == null) {
				System.out.printf("로그인에 실패하였습니다.\n아이디나 비밀번호를 확인해주세요\n");
			}
		} else {
			System.out.printf("현재 접속중입니다.\n다시 로그인을 원하시면 로그아웃을 해주세요\n");
		}
	}
	
	private void doLogout () {
		foundMember = null;
		logincheck = false;
		System.out.println("로그아웃 되었습니다");
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}
}
