package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.MediaSize.ISO;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="lumen_payment")
public class Payment {
	
	@Id
	@Column(name="payment_id")	
	private int paymentId;
	
	@Column(name="customerName")
	private String customerName;
	
	
	
	
	@Column(name="payment_date")
	
	private LocalDate paymentDate;
	
	private double amount;
	

}
