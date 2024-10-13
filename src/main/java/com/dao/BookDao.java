package com.dao;

import com.bo.PaginationBO;
import com.model.Book;
import com.model.BookComment;

import java.util.List;

public interface BookDao {

	public List<Book> getUserWiseBookList(PaginationBO pagination) throws Exception;

	public List<Book> bookListAdminPanel(PaginationBO pagination, String timeZone) throws Exception;

	public Long bookListCountAdminPanel(PaginationBO pagination) throws Exception;

	public List<BookComment> getBookCommentList(PaginationBO pagination) throws Exception;

	public Long getBookCommentsCount(PaginationBO pagination) throws Exception;
}
