package cn.seiei.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.seiei.vo.ForJsonPostListVo;

/**
 * 前端使用 POST 方法，传递数字数组
 * 
 * @author Seiei
 *
 */
@Controller
public class ForPostIntegerListController {

	/**
	 * spring mvc
	 * request.getParameterNames()在默认content-type=application/x-www-form-urlencoded;
	 * charset=UTF-8的时候可以获取到值，但是在content-type=application/json的情况下获取不到值。
	 * 通过查询资料，tomcat对content-type=application/x-www-form-urlencoded进行了特殊处理，Content-Type不是application/x-www-form-urlencoded的POST请求是不会读取请求体数据和进行相应的参数处理的，即不会解析表单数据来放到request
	 * parameter map中。
	 * 所以通过request.getParameter(name)是获取不到的，因此在没有后端没有@requestBody这个注解的时候是解析不到参数的。
	 * 
	 */

	/**
	 * 前端请求头 contentType: 'application/json'，后端接受要标注 @RequestBody 后端直接使用
	 * List<Integer> List 作为接受参数接受
	 * 
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/testForPostListAndForApplication_json", method = RequestMethod.POST)
	@ResponseBody
	public int testForPostList(@RequestBody List<Integer> list) {
		System.out.println(list.size());
		return 1;
	}

	/**
	 * 前端请求头 contentType: 'application/json'，后端接受要标注 @RequestBody
	 * 后端使用自定义的VO与之对应，要注意VO的属性名称与传入的 JSON 对象的键名要一致
	 * 
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/testForPostListAndForApplication_json2", method = RequestMethod.POST)
	@ResponseBody
	public int testForPostList2(@RequestBody ForJsonPostListVo forJsonPostListVo) {
		System.out.println(forJsonPostListVo.getList().size());
		return 1;
	}

	/**
	 *	前端请求头 contentType: 'application/x-www-form-urlencoded' 
	 * 	前端直接传入的数据是：var formdata = {"list": [1,2,3]}; 此时后端接受需要在声明 @RequestParam("list[]")
	 * 	注意：此时前端不能传入空数组，否则会报错，不能传递自定义对象
	 * 
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/testForPostListAndForX_www_form_urlencoded", method = RequestMethod.POST)
	@ResponseBody
	public int testForPostList2(@RequestParam("list[]") ArrayList<Integer> list) {
		System.out.println(list.size());
		return 1;
	}

	/**
	 * 前端请求头 contentType: 'application/x-www-form-urlencoded'
	 * 前端直接传入的数据是：var formdata = {"list": [1,2,3].join()}; 数组转化成字符串
	 * 此时后端正常接受参数
	 *
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/testForPostListAndForX_www_form_urlencoded2", method = RequestMethod.POST)
	@ResponseBody
	public int testForPostList3(ArrayList<Integer> list) {
		System.out.println(list.size());
		return 1;
	}

	/**
	 * 前端请求头 contentType: 'application/x-www-form-urlencoded'
	 * 前端直接传入的数据是：var formdata = {"list": [1,2,3].join()}; 数组转化成字符串
	 * 后端使用自定义的VO与之对应，要注意VO的属性名称与传入的 JSON 对象的键名要一致
	 * 
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/testForPostListAndForX_www_form_urlencoded3", method = RequestMethod.POST)
	@ResponseBody
	public int testForPostList4(ForJsonPostListVo forJsonPostListVo) {
		System.out.println(forJsonPostListVo.getList().size());
		return 1;
	}
}
