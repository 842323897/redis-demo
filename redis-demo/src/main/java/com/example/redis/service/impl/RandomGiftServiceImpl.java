package com.example.redis.service.impl;

import com.example.redis.entity.RandomGiftUser;
import com.example.redis.service.RandomGiftService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RandomGiftServiceImpl implements RandomGiftService {
	// 抽奖名单
	private final static String SPOP_USERS_SET = "pop:user:set";

	//中奖名单
	private final static String GIFT_USER_LIST = "gift:user:list";

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void randomGift() {
		this.initUser();

		String third = this.popUser(3);
		String second = this.popUser(2);
		String first = this.popUser(1);

		System.out.println("一等奖" + first);
		System.out.println("二等奖" + second);
		System.out.println("三等奖" + third);
		System.out.println("=========================================================");

		this.saveRandomGiftUser(first, second, third);
	}

	/**
	 * 查询中奖名单
	 *
	 * @return
	 */
	@Override
	public List<RandomGiftUser> listGiftUser() {

		List<RandomGiftUser> randomGiftUserList = null;

		if (redisTemplate.hasKey(GIFT_USER_LIST)) {
			randomGiftUserList = redisTemplate.opsForList().range(GIFT_USER_LIST, 0, -1);

			try {
				System.out.println("中奖名单： " + new ObjectMapper().writeValueAsString(randomGiftUserList));
				System.out.println("=========================================================");
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return randomGiftUserList;
	}

	/**
	 * 保存中奖用户到 redis
	 *
	 * @param first  一等奖
	 * @param second 二等奖
	 * @param third  三等奖
	 */
	@Override
	public void saveRandomGiftUser(String first, String second, String third) {
		RandomGiftUser user = new RandomGiftUser(first, second, third, new Date());

		redisTemplate.opsForList().rightPush(GIFT_USER_LIST, user);
	}

	/**
	 * 初始化抽奖名单
	 */
	private void initUser() {
		redisTemplate.delete(SPOP_USERS_SET);

		for (int i = 1; i <= 30; i++) {
			this.redisTemplate.opsForSet().add(SPOP_USERS_SET, "user" + i);
		}

	}

	/**
	 * 随机抽取用户
	 *
	 * @param count
	 */
	private String popUser(int count) {
		String result = "";

		List<String> list = redisTemplate.opsForSet().pop(SPOP_USERS_SET, count);
		result = StringUtils.join(list, ",");

		return result;
	}
}
