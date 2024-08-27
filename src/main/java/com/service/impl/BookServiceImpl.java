package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.Response;
import com.dao.ObjectDao;
import com.exceptions.DuplicateEntryException;
import com.exceptions.NotFoundException;
import com.exceptions.RequiredFieldsMissingException;
import com.helper.AppConstants;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.Book;
import com.model.BookAndBookCategory;
import com.model.BookCategory;
import com.model.BookComment;
import com.model.BookHashTags;
import com.model.BookLike;
import com.model.Library;
import com.model.User;
import com.service.BookService;
import com.utils.RandomCreator;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private ObjectDao objectDao;

	@Override
	public Response addBook(Book book) throws Exception {
		Response response = new Response();
		try {
			if (bookNullChecker("ADD", book)) {
				book.setBookId(null);
				Library library = objectDao.getObjectById(Library.class, book.getLibId());
				book.setLibrary(library);
				book.setBookUniqueUid(RandomCreator.generateUID(AppConstants.BOOK_UID_PREFIX, 8));
				objectDao.saveObject(book);

				if (null != book.getHashTags() && book.getHashTags().size() > 0) {
					saveBookHashtags(book.getHashTags(), book);
				}
				if (null != book.getBookCategoryList() && book.getBookCategoryList().size() > 0) {
					saveBookAndBookCategory(book.getBookCategoryList(), book);
				}
				response.setStatus(ErrorConstants.SUCESS);
				response.setMessage("Book Added Sucessfully");
				response.setResult(book.getBookId());

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
	public Response addMultipleBook(List<Book> bookList) throws Exception {
		Response response = new Response();
		Long sucess = 0L;
		Long failure = 0L;
		for (Book book : bookList) {
			try {
				response = addBook(book);
				if (response.getStatus() == ErrorConstants.SUCESS) {
					sucess++;
				} else {
					failure++;
				}

			} catch (Exception e) {
				e.printStackTrace();
				response.setResult(e.getMessage());
				return response;
			}
		}
		response.setResult(null);
		response.setStatus(ErrorConstants.SUCESS);
		response.setMessage("Books Added Sucessfully...sucess(" + sucess + ")," + "failure(" + failure + ")");
		return response;
	}

	@Override
	public Boolean bookNullChecker(String operationType, Book book) throws Exception {
		if ("ADD".equalsIgnoreCase(operationType)) {
			return null != book && null != book.getLibId();
		} else {
			return null != book && null != book.getBookId();
		}
	}

	@Override
	public void saveBookAndBookCategory(List<Long> bookcategoryIdList, Book book) throws Exception {
		BookAndBookCategory bookAndBookCategory = null;
		for (Long bookCategoryId : bookcategoryIdList) {
			if (null != bookCategoryId) {
				BookCategory bookCategory = objectDao.getObjectById(BookCategory.class, bookCategoryId);
				bookAndBookCategory = new BookAndBookCategory();
				bookAndBookCategory.setBookCategory(bookCategory);
				bookAndBookCategory.setBook(book);
				objectDao.saveObject(bookAndBookCategory);
			}
		}

	}

	@Override
	public void saveBookHashtags(List<String> hashTags, Book book) throws Exception {
		BookHashTags bookHashTag = null;
		for (String hashtag : hashTags) {
			if (null != hashtag) {
				bookHashTag = new BookHashTags();
				bookHashTag.setHashtag(hashtag);
				bookHashTag.setBook(book);
				objectDao.saveObject(bookHashTag);
			}
		}
	}

	@Override
	public Response bookLike(BookLike bookLike) throws Exception {
		Response response = new Response();
		try {
			if (null != bookLike && null != bookLike.getUserId() && null != bookLike.getBookId()) {
				Book book = objectDao.getObjectById(Book.class, bookLike.getBookId());
				User user = objectDao.getObjectById(User.class, bookLike.getUserId());
				if (book != null && user != null) {
					BookLike existingBookLike = objectDao.getObjectByTwoParams(BookLike.class, "book", book, "user",
							user);
					if (null != existingBookLike) {
						if (bookLike.getIsLiked() == existingBookLike.getIsLiked()) {
							throw new DuplicateEntryException(
									"Already " + (bookLike.getIsLiked() ? "Liked" : "Disliked") + " the Book");
						} else {
							existingBookLike.setIsLiked(bookLike.getIsLiked());
							objectDao.updateObject(existingBookLike);
							response.setStatus(ErrorConstants.SUCESS);
							return response;
						}
					}
					bookLike.setBookLikeDislikeId(null);
					bookLike.setBook(book);
					bookLike.setUser(user);
					if (bookLike.getIsLiked() == null) {
						bookLike.setIsLiked(true);
					}
					objectDao.saveObject(bookLike);
					response.setStatus(ErrorConstants.SUCESS);
					response.setMessage("Book " + (bookLike.getIsLiked() ? "Liked" : "Disliked") + " Sucessfully...");
					return response;
				} else {
					response.setStatus(ErrorConstants.NOT_FOUND);
					response.setMessage(CommonMessages.NOT_FOUND);
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
	public Response bookComment(BookComment bookComment) throws Exception {
		Response response = new Response();
		try {
			if (null != bookComment && null != bookComment.getUserId() && null != bookComment.getBookId()
					&& null != bookComment.getCommentText() && !bookComment.getCommentText().isEmpty()) {
				Book book = objectDao.getObjectById(Book.class, bookComment.getBookId());
				User user = objectDao.getObjectById(User.class, bookComment.getUserId());
				if (null != user && null != book) {
					if (null != bookComment.getBookCommentId()) {
						BookComment comment = objectDao.getObjectById(BookComment.class,
								bookComment.getBookCommentId());
						comment.setBook(book);
						comment.setUser(user);
						comment.setCommentText(bookComment.getCommentText());
						objectDao.updateObject(comment);
						response.setStatus(ErrorConstants.SUCESS);
						response.setMessage("Comment Updated Sucessfully...");
					} else {

						bookComment.setBook(book);
						bookComment.setUser(user);
						objectDao.saveObject(bookComment);
						response.setStatus(ErrorConstants.SUCESS);
						response.setMessage("Comment Added Sucessfully...");
					}
				} else {
					throw new NotFoundException(CommonMessages.NOT_FOUND);
				}

			} else {
				throw new RequiredFieldsMissingException(CommonMessages.REQUIRED_FIELD_MISSING);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
