package com.lin.feng.sheng.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lin.feng.sheng.controller.common.CommonController;
import com.lin.feng.sheng.service.outer.IOrderService;

@RestController
public class TeacherHelloWorldController extends CommonController {


	@Value("${server.port}")
	private int port;


	@Autowired
	IOrderService orderService;


	@RequestMapping("/addOrder")
    @ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			orderService.order();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "addOrder hello world !" ;
    }

	@RequestMapping("/noTxOrder")
	@ResponseBody
	public String noTxOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			orderService.noTxOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "noTxOrder hello world !" ;
	}


	@RequestMapping("/updateOrder")
	@ResponseBody
	public String updateOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			orderService.updateOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "updateOrder hello world !" ;
	}

}
