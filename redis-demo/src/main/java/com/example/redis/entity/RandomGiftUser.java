package com.example.redis.entity;

import java.io.Serializable;
import java.util.Date;

public class RandomGiftUser implements Serializable {
	private String first;
	private String second;
	private String third;
	private Date createDate;

	public RandomGiftUser(String first, String second, String third, Date createDate) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.createDate = createDate;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public String getThird() {
		return third;
	}

	public void setThird(String third) {
		this.third = third;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
