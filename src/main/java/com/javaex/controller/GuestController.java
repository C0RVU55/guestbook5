package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;


@Controller
@RequestMapping(value="/guest")
public class GuestController {
	
	//필드
	@Autowired
	private GuestDao gDao;
	
	//리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("list");
		
		List<GuestVo> guestList = gDao.getList();
		System.out.println("컨트롤러 list : "+guestList.toString());
		
		model.addAttribute("gList", guestList);
		
		return "addList";
	}
	
	//등록 --> @ModelAttribute
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestVo gVo) {
		System.out.println("add");
		System.out.println("컨트롤러 add : "+gVo.toString());
		
		gDao.contentAdd(gVo);
		
		return "redirect:/guest/list";
	}
	
	//삭제폼 
	@RequestMapping(value="/dform", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() { 
		System.out.println("deleteForm");
		
		return "deleteForm";
	}
	
	//삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo gVo) {
		System.out.println("delete");
		System.out.println("컨트롤러 delete : "+gVo);
		
		int count = gDao.contentDelete(gVo);
		
		//비번 실패시 문구 출력
		if(count == 0) { 
			return "redirect:/guest/dform?result=0&no="+gVo.getNo();
		}
		
		return "redirect:/guest/list";
	}
	
	
	///////////////////Map 적용////////////////////
	
	
	/* 리스트는 불가능 --> Dao에 설명
	@RequestMapping(value="/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(Model model) {
		System.out.println("list2");
		Map<String, Object> guestMap = gDao.getList2();
		
		model.addAttribute("gMap", guestMap);
		
		return "addList";
	}
	*/
	
	//등록2 (테스트O)
	@RequestMapping(value="/add2", method= {RequestMethod.GET, RequestMethod.POST})
	public String add2(@RequestParam("name") String name,
					@RequestParam("password") String password,
					@RequestParam("content") String content) {
		System.out.println("add2");
		System.out.println("컨트롤러 add2 : "+name+" "+password+" "+content);
		
		gDao.contentAdd2(name, password, content);
		
		return "redirect:/guest/list";
	}
	
	//삭제2 (테스트O)
	@RequestMapping(value="/delete2", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete2(@RequestParam("password") String password, @RequestParam("no") int no) {
		System.out.println("delete2");
		System.out.println("컨트롤러 delete2 : "+no+" "+password);
		
		int count = gDao.contentDelete2(no, password);
		
		//비번 실패시 문구 출력
		if(count == 0) { 
			return "redirect:/guest/dform?result=0&no="+no;
		}
		
		return "redirect:/guest/list";
	}
	
}
