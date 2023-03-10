package com.example.redis.service;


import com.example.redis.entity.RandomGiftUser;

import java.util.List;

public interface RandomGiftService {
	/**
	 * 抽奖
	 */
	void randomGift();

	/**
	 * 保存中奖用户到redis
	 *
	 * @param first  一等奖
	 * @param second 二等奖
	 * @param third  三等奖
	 */
	void saveRandomGiftUser(String first, String second, String third);

	/**
	 * 查询中奖名单
	 *
	 * @return
	 */
	List<RandomGiftUser> listGiftUser();

}
