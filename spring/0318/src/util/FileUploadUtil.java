package util;
import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	

	public String uploadImg(HttpServletRequest request, MultipartFile image) {
		String root  = request.getServletContext().getRealPath("/");
		System.out.println("root: "+root);
		String uploadPath = root+"upload"+File.separator;
		System.out.println("uploadPath: "+uploadPath);
		String uuid = UUID.randomUUID().toString();
		System.out.println("uuid: "+uuid);
		String original = image.getOriginalFilename();
		String exe = original.substring(original.indexOf("."));
		
		
		File file = new File(uploadPath+uuid+exe);
		
		try {
			image.transferTo(file);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return uuid+exe;
	}
	
}
