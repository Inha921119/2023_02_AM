package com.KoreaIT.java.AM.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	/** 현재 날짜, 시각 정보 String 리턴 */
	public static String getNowDateTimeStr() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date now = new Date();

		return sdf1.format(now);
	}
	
	public static void CommandHelp() {
		System.out.println("=================== 명령어 모음 ===================");
		System.out.println("member join : 회원가입을 합니다");
		System.out.println("member login : 로그인을 합니다");
		System.out.println("member logout : 로그아웃을 합니다");
		System.out.println("article list : 게시물의 목록을 보여줍니다");
		System.out.println("article write : 게시물을 작성합니다");
		System.out.println("article detail : 게시물을 열람합니다");
		System.out.println("article delete 숫자 : 숫자에 해당하는 게시글을 삭제합니다");
		System.out.println("article modify 숫자 : 숫자에 해당하는 게시글을 편집합니다");
		System.out.println("================================================");
	}
}