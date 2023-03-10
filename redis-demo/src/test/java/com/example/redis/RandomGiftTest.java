package com.example.redis;

import com.example.redis.service.RandomGiftService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RandomGiftTest {
	@Autowired
	private RandomGiftService randomGiftService;

	@Test
	public void randomGift(){
		this.randomGiftService.randomGift();
		this.randomGiftService.listGiftUser();
	}
}
