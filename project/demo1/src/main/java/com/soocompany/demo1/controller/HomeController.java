package com.soocompany.demo1.controller;

import com.soocompany.demo1.dto.BoardVO;
import com.soocompany.demo1.service.BoardServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// 서비스에서 데이터 가져오기
	@Autowired
	private BoardServiceImpl service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		List<BoardVO> boardList = service.selectBoard();
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		if (boardList.size() != 0)
			model.addAttribute("title", boardList.get(0).getTitle());
		
		return "home";
	}
	
	
	@RequestMapping(value="/board")
	public ModelAndView showBoard(Map<String, String> param) {
		ModelMap model = new ModelMap();
		
		List<BoardVO> list = service.selectBoardList();
		
		model.addAttribute("list", list);
		
		return new ModelAndView("boardMain", model);
	}
	
	@RequestMapping(value="/contents")
	public ModelAndView write(Map<String, String> param) {
		ModelAndView mav = new ModelAndView("write");
		
		mav.addObject("board", service.editBoard());
		
		return mav;
	}
	
	@RequestMapping(value="/contents/{id}")
	public ModelAndView read(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("write");
		mav.addObject("board", service.boardDetail(id));
		return mav;
	}
	
}
