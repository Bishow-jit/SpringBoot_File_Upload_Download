package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

//		String email1 = "BISHOWJIT@GMAIL.COM";
//		String email2 = "SAHA@YAHOO.COM";
//		String email3 = "Jack@HOTMAIL.COM";
//		String email4 = "SAHA@GMAIL.COM";
//		String email5 = "RON@YAHOO.COM";
//
//		ArrayList<String> email_List = new ArrayList<>();
//		email_List.add(email1);
//		email_List.add(email2);
//		email_List.add(email3);
//		email_List.add(email4);
//		email_List.add(email5);
//
//		System.out.println("EMAIL LIST:"+email_List);
//
//		HashSet<String> distinctEmail = new HashSet<>();
//
//		for (String e:email_List){
//			distinctEmail.add(e.substring(e.lastIndexOf("@")));
//		}
//
//		System.out.print("ALL Email domain :"+distinctEmail+"========");
//		System.out.println("Count:"+distinctEmail.size());
	}

}
