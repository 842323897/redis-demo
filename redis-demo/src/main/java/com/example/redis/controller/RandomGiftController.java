package com.example.redis.controller;

import com.example.redis.entity.RandomGiftUser;
import com.example.redis.service.RandomGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RandomGiftController {
	@Autowired
	private RandomGiftService randomGiftService;

	@GetMapping("/randomGift")
	public ResponseEntity randomGift() {
		this.randomGiftService.randomGift();
		List<RandomGiftUser> randomGiftUsers = this.randomGiftService.listGiftUser();

		return ResponseEntity.ok(randomGiftUsers);
	}
}
