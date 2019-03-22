package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.PhonebookService;

@Controller
public class PhoneController {
	private PhonebookService phonebookService;
	
	public void setPhonebookService(PhonebookService phonebookService) {
		this.phonebookService = phonebookService;
	}
	
	@RequestMapping("/index.html")
	public void phonebook(Model model) {
		model.addAttribute("phonebook", phonebookService.getList());
	}
}
