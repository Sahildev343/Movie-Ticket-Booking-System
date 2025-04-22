package com.itvedant.movies.dao;

import java.time.LocalDateTime;

import com.itvedant.movies.entity.Booking;

public class AddPayment {

	private String TransactionId;
	
	 private String paymentStatus;
	 
	private Integer bookingId;

	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	
	
	
}
