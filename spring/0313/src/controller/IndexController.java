package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

//POJO : 상속이나 구현을 할 필요가 없는 자바 

//decoration pattern
@Controller
public class IndexController {

	@RequestMapping("/index.gm")
	public void hhhhh() {		
		/*
		 * View
		 * - InternalResourceView(JSP)
		 * - redirectView
		 * - JackksonJsonView
		 * */			
	}
	
	
}
