package com.externalcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.ImageDataBO;
import com.bo.Response;
import com.utils.FileUtility;

@RestController
@CrossOrigin
@RequestMapping("/api/google-drive/")
public class GoogleController {

	@Autowired
	private FileUtility fileUtility;

	@PostMapping("upload-base64-test")
	public Response uploadBase64Test(@RequestBody ImageDataBO imageData) {
		Response response = new Response();
		try {

		} catch (Exception e) {

		}
		return response;
	}

}
