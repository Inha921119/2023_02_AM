package com.KoreaIT.java.AM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		System.out.printf("명령어를 입력하세요 : ");

		String cmd = sc.nextLine();

		System.out.printf("입력된 명령어 : %s\n", cmd);

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}
}
