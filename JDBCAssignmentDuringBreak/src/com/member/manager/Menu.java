package com.member.manager;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println(" Select from Menu");
			System.out.println(" Press 1 to Insert a new member into the table");
			System.out.println(" Press 2 to Update membership type");
			System.out.println(" Press 3 to Update membership fees");
			System.out.println(" Press 4 to Delete memberhsip detail");
			System.out.println(" Press 5 to Display details of all member");
			System.out.println(" press 6 to exit");
			Scanner scanner = new Scanner(System.in);
			int ch = scanner.nextInt();
			System.out.println("-----------------------------------------------");
			if(ch==1) {
				Member member = new Member();
				member.insertDetails();	
			}else if(ch==2) {
				Member member = new Member();
				member.updateMemberType();	
			}else if(ch==3) {
				Member member = new Member();
				member.updateMembershipFees();
			}else if(ch==4) {
				Member member = new Member();
				member.deleteRecode();
			}else if(ch==5) {
				Member member = new Member();
				member.displayRecords();
			}else {
				System.out.println("Clossing Program");
				break;
			}
		}
	}

}
