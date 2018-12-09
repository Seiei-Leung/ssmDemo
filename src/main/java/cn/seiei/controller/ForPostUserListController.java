package cn.seiei.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.seiei.vo.Uservo;

/**
 * 前端使用 POST 方法，传递自定义对象数组，只有下面一个方法
 * 
 * @author Seiei
 *
 */
@Controller
@RequestMapping("/PostUservo/")
public class ForPostUserListController {

	/**
	 * 前端请求头  contentType: 'application/json'，后端接受要标注 @RequestBody
	 * 后端直接使用 List<Integer> List 作为接受参数接受
	 * 
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/testForPostListAndForApplication_json", method = RequestMethod.POST)
	@ResponseBody
	public int testForPostList(@RequestBody List<Uservo> list) {
		System.out.println(list.size());
		return 1;
	}
}
