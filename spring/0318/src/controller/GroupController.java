package controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import service.GroupsService;
import service.IdolsService;
import util.FileUploadUtil;
import vo.Group;
import vo.Idol;

@Controller
public class GroupController {
	
	private GroupsService groupsService;
	private IdolsService idolsSerivce;
	private FileUploadUtil fileUploadUtil;
	
	public void setIdolsSerivce(IdolsService idolsSerivce) {
		this.idolsSerivce = idolsSerivce;
	}
	
	public void setGroupsService(GroupsService groupsService) {
		this.groupsService = groupsService;
	}
	
	public void setFileUploadUtil(FileUploadUtil fileUploadUtil) {
		this.fileUploadUtil = fileUploadUtil;
	}
	
	
	@RequestMapping(value="/group/update/{no}", method=RequestMethod.GET)
	public String updateForm(@PathVariable int no, Model model) {
		
		//@PathVariable : Url 경로 안에 있는 변수를 받아오는 것
		model.addAttribute("status", "수정");
		model.addAttribute("group", groupsService.getGroup(no));
		
		
		return "groupForm";
	}
	
	@RequestMapping(value="/group", method=RequestMethod.PUT)
	public String update(Group group) {
		
		groupsService.update(group);
		
		return "redirect:/group";
	}
	
	@RequestMapping(value="/group/${no}", method=RequestMethod.DELETE)
	public String delete(@PathVariable int no) {
		groupsService.remove(no);
		return "redirect:/group";
	}
	@RequestMapping(value= {"/","index.html"})
	public String index() {
		return "redirect:/group";
	}
	
	@RequestMapping("/group")
	public String groupPage(Model model ) {
		List<Group> groups = groupsService.getList();
		List<Idol> idols = null;
		
		model.addAttribute("groups", groups);
		
		
			idols =  idolsSerivce.getList();
				model.addAttribute("idols",idols);	
		
		return "index";
		
	}
	@RequestMapping(value="/group/insert", method=RequestMethod.GET)
	public String insertForm(Model model) {
		model.addAttribute("status","등록");
		return "groupForm";
	}
	@RequestMapping(value="/group", method=RequestMethod.POST)
	public String insertGroup(HttpServletRequest request, MultipartFile image, Group group ) {
		String imageName = fileUploadUtil.uploadImg(request, image);
		group.setProfile("/upload/"+imageName); 
		groupsService.register(group);
		
		return "redirect:/group";
	}
	
}
