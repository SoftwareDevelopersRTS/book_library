package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.PaginationBO;
import com.bo.Response;
import com.helper.AppConstants;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.Library;
import com.service.LibraryService;
import com.utils.MailUtility;

@RestController
@CrossOrigin
@RequestMapping("/api/library/")
public class LibraryController {

	private final LibraryService libraryService;

	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@PostMapping("add")
	public Response addLibrary(@RequestBody Library library) {
		Response response = new Response();
		try {
			return libraryService.addLibrary(library);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("add-multiple")
	public Response addMultipleLibrary(@RequestBody List<Library> libraryList) {
		Response response = new Response();
		try {
			return libraryService.addMultipleLibrary(libraryList);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@GetMapping("details-by-id/{libraryId}")
	public Response getLibraryById(@PathVariable Long libraryId) {
		Response response = new Response();
		try {
			return libraryService.getLibraryById(libraryId);

		} catch (Exception e) {
			e.printStackTrace();
			MailUtility.sendExceptionEmailToDeveloper(AppConstants.DEVELOPER_EMAILS, e.getMessage(), e.toString());
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}

	@PostMapping("list")
	public Response getLibraryList(@RequestBody PaginationBO pagination) {
		Response response = new Response();
		try {
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage("Libraries Get Sucessfully...");
			response.setListCount(libraryService.getLibraryCount(pagination));
			response.setResult(libraryService.getLibraryList(pagination));
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(CommonMessages.SOMETHING_WENT_WRONG_TRY_AGAIN);
		}
		return response;
	}
}
