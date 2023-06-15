package com.member.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.db.connect.CreateConnection;


public class Member {
	
    private int memberId;
    private String memberName;
    private String memberType;
    private double membershipFees;
    
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(int memberId, String memberName, String memberType, double memberFees) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberType = memberType;
		this.membershipFees = memberFees;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public double getMembershipFees() {
		return membershipFees;
	}
	public void setMembershipFees(double membershipFees) {
		this.membershipFees = membershipFees;
	}
	public void insertDetails() {
		Scanner scanner = new Scanner(System.in);
		Member member = new Member();
		System.out.println("Enter the memberId: ");
		member.setMemberId(scanner.nextInt());
		scanner.nextLine();
		System.out.println("Enter the memberName: ");
		member.setMemberName(scanner.nextLine());
		System.out.println("Enter the memberType ex:- Gold, Silver, Platinum, Diamond: ");
		member.setMemberType(scanner.nextLine());
		System.out.println("Enter the membershipFees: ");
		member.setMembershipFees(scanner.nextDouble());
		try {
			CreateConnection cc = new CreateConnection();
			Connection connection = cc.create();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Member(memberId,memberName,memberType,membershipFees) VALUE(?,?,?,?)");
			statement.setInt(1, member.getMemberId());
			statement.setString(2, member.getMemberName());
			statement.setString(3, member.getMemberType());
			statement.setDouble(4, member.getMembershipFees());
			int status = statement.executeUpdate();
			if(status>0) {
			System.out.println("recode inserted successfully");
			}else {
				System.out.println("No record added ! Something Went Wrong");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	} 
    public void displayRecords(){
    	try {
    	CreateConnection cc = new CreateConnection();
    	Connection connection = cc.create();
    	Statement  statement  = connection.createStatement();
    	ResultSet result = statement.executeQuery("select * from Member");
    	System.out.println("MemberId   MemberName  MemberType  MembershipFees");
    	while(result.next()) {
    		System.out.print(result.getInt("memberId")+"          ");
    		System.out.print(result.getString("memberName")+"      ");
    		System.out.print(result.getString("memberType")+"          ");
    		System.out.println(result.getDouble("membershipFees")+"         ");
    		
    	}
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
   	 
    }
    public void deleteRecode() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Enter Memberid to delete record: ");
    	memberId = scanner.nextInt();
    	try {
    		CreateConnection cc = new CreateConnection();
    	    Connection connection = cc.create();
    	    PreparedStatement statement = connection.prepareStatement("Delete from Member where memberId=?");
    	    statement.setInt(1, memberId);
    	    int result = statement.executeUpdate();
    	    if(result>0) {
    	    	System.out.println("Record deleted succesfully00");
    	    }else {
    	    	System.out.println("No Record Found");
    	    }
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
    }
    public void updateMemberType() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Enter Memberid to update record: ");
    	memberId = scanner.nextInt();
    	scanner.nextLine();
    	System.out.println("Enter the new membershipType to update ex:- Gold, Silver,Platinum,Diamond ");
    	
    	memberType = scanner.nextLine();
    	
    	try {
    		CreateConnection cc = new CreateConnection();
    	    Connection connection = cc.create();
    	    PreparedStatement statement = connection.prepareStatement("Update Member Set memberType =? where memberId=?");
    	    statement.setString(1, memberType);
    	    statement.setInt(2, memberId);
    	    int result = statement.executeUpdate();
    	    if(result>0) {
    	    	System.out.println("Record updated succesfully00");
    	    }else {
    	    	System.out.println("No Record Found");
    	    }
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }
public void updateMembershipFees() {
	Scanner scanner = new Scanner(System.in);
	System.out.println("Enter Memberid to update record: ");
	memberId = scanner.nextInt();
	scanner.nextLine();
	System.out.println("Enter the new membershipfees to update ");
	memberType = scanner.nextLine();
	
	try {
		CreateConnection cc = new CreateConnection();
	    Connection connection = cc.create();
	    PreparedStatement statement = connection.prepareStatement("Update Member Set membershipFees =? where memberId=?");
	    statement.setDouble(1, membershipFees);
	    statement.setInt(2, memberId);
	    int result = statement.executeUpdate();
	    if(result>0) {
	    	System.out.println("Record updated succesfully00");
	    }else {
	    	System.out.println("No Record Found");
	    }
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
}
