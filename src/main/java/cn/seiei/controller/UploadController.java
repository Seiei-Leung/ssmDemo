package cn.seiei.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	// 上传图片
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestParam("pictureFile") MultipartFile pictureFile)
			throws IllegalStateException, IOException {
		// 使用UUID给图片重命名，并去掉四个“-”
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		// 获取文件的扩展名
		if (pictureFile != null) {
			String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
			pictureFile.transferTo(new File("F:\\115\\" + name + "." + ext));
			System.out.println(pictureFile.getOriginalFilename());
			System.out.println(ext);
		}
		return "helloworld";
	}

	// 使用Html5 FormData实现多文件上传
	@RequestMapping(value = "/file/upload2", method = RequestMethod.POST)
	public String upload2(HttpServletRequest request, @RequestParam("pictureFile") List<MultipartFile> pictureFiles)
			throws IllegalStateException, IOException {

		// 获取文件的扩展名
		for (MultipartFile file : pictureFiles) {
			// 使用UUID给图片重命名，并去掉四个“-”
			String name = UUID.randomUUID().toString().replaceAll("-", "");
			String fileName = file.getOriginalFilename(); // 获取文件名
			file.transferTo(new File("F:\\115\\" + name + "." + fileName));
		}
		return "helloworld";
	}
}
