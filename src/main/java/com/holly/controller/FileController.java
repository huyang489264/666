package com.holly.controller;

import com.alibaba.fastjson.JSONObject;
import com.holly.model.FileEntity;
import com.holly.service.FileService;

import com.holly.util.FileUploadTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class FileController {
	@Autowired
	private FileService fileservice;

	private static final Logger logger = Logger.getLogger(FileController.class.getName());

	/*
	 *跳转
	 * 
	 * */
	@RequestMapping(value="/loginUp")
	public String login() {
		System.out.println("dashjkdha:");
		return "login.jsp";
	}
	/*
	 *删除
	 *
	 * */
	@RequestMapping(value="/del")
	@ResponseBody
	public String del(long fileId,String path) {
		fileservice.del(fileId);
		FileUploadTool fileUploadTool = new FileUploadTool();
		fileUploadTool.del("D:\\workspace\\demo\\src\\main\\webapp\\"+path);
		return "成功";
	}
	/*
	 * 结果
	 * 
	 * */
	@RequestMapping(value="/resultListPage")
	public String result(Model model) {
		List<FileEntity> entity = fileservice.findAll();
		model.addAttribute("entity", entity);
		return "resultList.jsp";
	}
	/*
	 * 上传保存
	 * 
	 * */
	@RequestMapping(value = "/upload_aa")
	public String upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
                         HttpServletRequest request, ModelMap map) {
		String message = "";
		FileEntity entity = new FileEntity();
		String logoPathDir = request.getParameter("shipin");
		System.out.println("-------" + logoPathDir + "----------------------------------");
		FileUploadTool fileUploadTool = new FileUploadTool();
		try {
			entity = fileUploadTool.createFile(logoPathDir, multipartFile, request);
			if (entity != null) {
				fileservice.saveFile(entity);
				message = "上传成功";
				map.addAttribute("result",message);
			} else {
				message = "上传失败";
				map.addAttribute("result",message);
			}

		} catch (Exception e) {

		}finally {
			if("".equals(message)){
				message = "上传失败";
				logger.info("保存信息时失败");
				map.addAttribute("result",message);
			}
			map.addAttribute("entity",entity);
			String s = JSONObject.toJSONString(entity);
			logger.info(s);
			return "result.jsp";
		}

	}


}
