package com.KoreaIT.java.AM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.printf("명령어를 입력하세요 : ");
			String command = sc.nextLine();
			
			if(command.equals("exit")) {
				break;
			}
		}

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}
}