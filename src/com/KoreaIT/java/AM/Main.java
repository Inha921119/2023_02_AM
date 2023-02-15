package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		
		int id = 0;
		
		while(true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine().trim();
			
			if (command.length() == 0 ) {
				 System.out.println("명령어를 입력해주세요");
				 continue;
			}
			
			if (command.equals("article list")) {
				if (id == 0) {
					System.out.println("게시글이 없습니다.");
				}
			} else if(command.equals("exit")) {
				break;
			} else if (command.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String contents = sc.nextLine();
				
				id++;
				
				System.out.printf("%d번글이 생성되었습니다.\n", id);
				
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		System.out.println("== 프로그램 종료 ==");

		sc.close();
	}
}
