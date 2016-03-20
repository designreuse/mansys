package com.wicky.biz.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wicky.biz.entity.UserVO;
import com.wicky.biz.web.bind.annotation.CurrentUser;

@RequestMapping("/samples")
@Controller
public class SamplesController {

	@RequestMapping({ "", "/", "index", "/other" })
	public String index(@CurrentUser UserVO loginUser, Model model) {
		System.out.println("!index!");
		return "samples/tags_sample";
	}

	@RequestMapping(value = "/{id}/{str}.{type}")
	public ModelAndView helloWorld(@PathVariable String id, @PathVariable String str, @PathVariable String type) {
		System.out.println("1");
		System.out.println("id: " + id);
		System.out.println("str: " + str);
		System.out.println("type: " + type);
		return new ModelAndView("/helloWorld");
	}

	// <form method="post" action="hao.do">
	// a: <input id="a" type="text" name="a"/>
	// b: <input id="b" type="text" name="b"/>
	// <input type="submit" value="Submit" />
	// </form>
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("pojo") Pojo pojo) {
		System.out.println("2");
		return "helloWorld";
	}

	public class Pojo {
		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

		private String a;
		private int b;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("3");
		System.out.println(request.getParameter("a"));
		return "helloWorld";
	}

	@RequestMapping(value = "/requestParam", method = RequestMethod.GET)
	public String setupForm(@RequestParam(value = "a", required = true) String a, ModelMap model) {
		System.out.println("4");
		System.out.println(a);
		return "helloWorld";
	}

	// get cookie's value
	@RequestMapping("cookieValue")
	public String testCookieValue(@CookieValue("hello") String cookieValue, @CookieValue String hello) {
		System.out.println(cookieValue + "-----------" + hello);
		return "cookieValue";
	}

	// get header info in requestHeader
	@RequestMapping("testRequestHeader")
	public String testRequestHeader(@RequestHeader("Host") String hostAddr, @RequestHeader String Host,
			@RequestHeader String host) {
		System.out.println(hostAddr + "-----" + Host + "-----" + host);
		return "requestHeader";
	}
}
