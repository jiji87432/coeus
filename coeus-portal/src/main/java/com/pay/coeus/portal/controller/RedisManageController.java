package com.pay.coeus.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pay.coeus.common.utils.RedisUtil;

/**
 * redis管理
 * @Description: 这里用一句话描述这个类的作用
 * @see: RedisManageController 此处填写需要参考的类
 * @version 2017年10月26日 上午3:35:28 
 * @author yuze.luo
 */
@Controller
@RequestMapping("/redisManage")
public class RedisManageController {

	/**
	 * @Description redis查询跳转
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toRedisManageQuery.action")
	public ModelAndView toRedisManageQuery() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/redis/redisManageQuery");
		return model;
	}

	/**
	 * @Description redis查询列表
	 * @param request
	 * @param form
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/query.action")
	public String query(@RequestParam("redisKey") String redisKey, Model model) {
		String redisValue = RedisUtil.getValue(redisKey);
		if (redisValue != null) {
			model.addAttribute("redisValue", redisValue);;
		}
		model.addAttribute("redisKey", redisKey);
		return "/redis/redisManageDetail";
	}

	/**
	 * @Description 保存redis信息
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/saveRedisManage.action")
	public String saveRedisManage(@RequestParam("redisKey") String redisKey,
			@RequestParam("redisValue") String redisValue,
			@RequestParam(defaultValue="0") String seconds) {
		RedisUtil.setKeyValue(redisKey, redisValue, Integer.parseInt(seconds));
		return "redirect:/redisManage/toRedisManageQuery.action";
	}

	/**
	 * @Description 删除redis key
	 * @param redisKey
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/deleteRedisMange.action")
	public String deleteRedisMange(@RequestParam("redisKey") String redisKey) {
		RedisUtil.removeKeyValue(redisKey);
		return "redirect:/redisManage/toRedisManageQuery.action";
	}

}
