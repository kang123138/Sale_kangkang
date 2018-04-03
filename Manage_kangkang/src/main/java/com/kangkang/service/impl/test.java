package com.atguigu.service.impl;

import java.sql.Timestamp;
import java.util.Date;

public class test {

	public static void main(String[] args) {

	Date date = new Date(); 
	Timestamp timeStamp = new Timestamp(date.getTime());
	System.out.println(timeStamp);

	}

}
