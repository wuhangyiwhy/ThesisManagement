package cn.why.thesis.controller;
import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.why.thesis.entity.Item;
import cn.why.thesis.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemservice;
	
	/**
	 * 显示学院信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/itemlist")
	public String itemlist(Model model) {
		List<Item> lsitem = itemservice.queryItemList();
		model.addAttribute("lsitem", lsitem);
		return "thesis/index";
	}
	
	@RequestMapping("/allitem")
	public String allitem(Model model) {
		List<Item> lsitem = itemservice.queryItemList();
		model.addAttribute("ls", lsitem);
		return "thesis/teacher/inc_menu";
	}
	
	
	

	
	
	
	
	
	
}
