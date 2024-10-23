package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bo.Response;
import com.dao.ObjectDao;
import com.exceptions.NotFoundException;
import com.exceptions.RequiredFieldsMissingException;
import com.helper.AppConstants;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.Book;
import com.model.BookCategory;
import com.model.ImageData;
import com.service.BookCategoryService;
import com.utils.FileUtility;
import com.utils.RandomCreator;
import com.utils.Utils;

@Service
public class BookCategoryServieImpl implements BookCategoryService {

	private final ObjectDao objectDao;

	private final FileUtility fileUtility;

	public BookCategoryServieImpl(ObjectDao objectDao, FileUtility fileUtility) {
		this.objectDao = objectDao;
		this.fileUtility = fileUtility;
	}

	@Override
	public Response addBookCategory(BookCategory bookCategory) throws Exception {
		Response response = new Response();
		try {
			if (null != bookCategory && null != bookCategory.getBookCategoryName()
					&& !bookCategory.getBookCategoryName().isEmpty()
					&& !bookCategory.getBookCategoryName().trim().isEmpty()) {
				BookCategory existingCategoryByName = objectDao.getObjectByParam(BookCategory.class, "bookCategoryName",
						bookCategory.getBookCategoryName());
				if (null != existingCategoryByName) {
					response.setStatus(ErrorConstants.ALREADY_EXIST);
					response.setMessage("Category Name Already Present..");
				} else {
					bookCategory.setBookCategoryUniqueId(
							RandomCreator.generateUID(AppConstants.BOOK_CATEGORY_UID_PREFIX, 8));
					bookCategory.setBookCategoryName(Utils.normalizeAndCapitalize(bookCategory.getBookCategoryName()));
					if (null != bookCategory.getIsActive()) {
						bookCategory.setIsActive(bookCategory.getIsActive());
					} else {
						bookCategory.setIsActive(true);
					}
					objectDao.saveObject(bookCategory);
					if (null != bookCategory.getImageDataBo()) {
						saveBookCategoryImage(bookCategory);
					}
					response.setStatus(ErrorConstants.SUCESS);
					response.setMessage("Category Added Sucessfully..");
					response.setResult(bookCategory.getBookCategoryId());
				}
			} else {
				response.setStatus(ErrorConstants.BAD_REQUEST);
				response.setMessage(CommonMessages.REQUIRED_FIELD_MISSING);
			}

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response addMultipleBookCategory(List<BookCategory> bookCategories) throws Exception {
		Response response = new Response();
		int sucessCount = 0;
		int errorCount = 0;
		try {
			if (null != bookCategories && bookCategories.size() > 0) {
				for (BookCategory bookCategory : bookCategories) {
					response = addBookCategory(bookCategory);
					if (response.getStatus() == ErrorConstants.SUCESS) {
						sucessCount++;
					} else {
						errorCount++;
					}
				}

			} else {
				response.setStatus(ErrorConstants.BAD_REQUEST);
				response.setMessage(CommonMessages.REQUIRED_FIELD_MISSING);
			}
			response.setResult(null);
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage(
					"Book Category Added Sucessfully...Sucess(" + sucessCount + "),Failure(" + errorCount + ")");
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response getBookCategoryById(Long categoryId) throws Exception {
		Response response = new Response();
		try {
			if (null != categoryId && categoryId > 0) {
				BookCategory book = objectDao.getObjectById(BookCategory.class, categoryId);
				if (null != book) {
					response.setResult(book);
					response.setStatus(ErrorConstants.SUCESS);
					response.setMessage("Book Category Get Sucessfully...");
				} else {
					throw new NotFoundException("Book Category Not Found");
				}
			} else {
				throw new RequiredFieldsMissingException("BookCategory Id is missing");
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	private void saveBookCategoryImage(BookCategory bookCategory) throws Exception {

		ImageData imageData = new ImageData();
		if (null != bookCategory.getImageDataBo().getEncodedFrontImage()
				&& !bookCategory.getImageDataBo().getEncodedFrontImage().isEmpty()) {
			String name = fileUtility.saveBase64Image(bookCategory.getImageDataBo().getEncodedFrontImage(),
					AppConstants.BOOK_CATEGORY_IMAGE_FOLDER);
			imageData.setFrontImagePath(name);
		}
		if (null != bookCategory.getImageDataBo().getEncodedBackImage()
				&& !bookCategory.getImageDataBo().getEncodedBackImage().isEmpty()) {
			String name = fileUtility.saveBase64Image(bookCategory.getImageDataBo().getEncodedBackImage(),
					AppConstants.BOOK_CATEGORY_IMAGE_FOLDER);
			imageData.setBackImagePath(name);
		}

		List<String> extraImageList = new ArrayList<String>();
		if (null != bookCategory.getImageDataBo().getExtraImages()
				&& bookCategory.getImageDataBo().getExtraImages().size() > 0) {
			for (String str : bookCategory.getImageDataBo().getExtraImages()) {
				extraImageList.add(fileUtility.saveBase64Image(str, AppConstants.BOOK_CATEGORY_IMAGE_FOLDER));
			}
		}
		imageData.setBookCategory(bookCategory);
		if (null != extraImageList && extraImageList.size() > 0) {
			imageData.setExtraImages(fileUtility.convertListToJson(extraImageList));
		}

		objectDao.saveObject(imageData);

	}

	@Override
	public Response changeBookCategoryStatus(Long bookCategoryId) throws Exception {
		Response response = new Response();

		if (bookCategoryId == null) {
			throw new RequiredFieldsMissingException(CommonMessages.REQUIRED_FIELD_MISSING);
		}

		BookCategory bookCategory = objectDao.getObjectById(BookCategory.class, bookCategoryId);
		if (bookCategory == null) {
			throw new NotFoundException("Book Not Found");
		}

		bookCategory.setIsActive(!Boolean.TRUE.equals(bookCategory.getIsActive()));
		objectDao.updateObject(bookCategory);

		response.setStatus(ErrorConstants.SUCESS);
		response.setMessage("Book Status Changed Successfully");

		return response;
	}

}
