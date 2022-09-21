package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payment;
import com.example.demo.repos.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repo;
	
	public Payment save(Payment entity) {
		return this.repo.save(entity);
	}
	public List<Payment> findAll() {
		return this.repo.findAll();
	}
}
