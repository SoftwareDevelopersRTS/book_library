package com.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bo.InterestAlgorithm;
import com.bo.PaginationBO;
import com.dao.BookDao;
import com.dao.ObjectDao;
import com.google.gson.Gson;
import com.model.Book;
import com.model.UserInterestAlgorithm;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private ObjectDao objectDao;

	@Override
	public List<Book> getUserWiseBookList(PaginationBO pagination) throws Exception {
		List<Book> books = null;
		Connection con = null;
		try {
			StringBuffer query = new StringBuffer("SELECT * FROM book where book_id is not null ");
			if (null != pagination.getUserId() && pagination.getUserId() > 0) {
				UserInterestAlgorithm userInterestAlgorithm = objectDao.getObjectByParam(UserInterestAlgorithm.class,
						"user", pagination.getUserId());
				if (null != userInterestAlgorithm && null != userInterestAlgorithm.getInterestAlgorithm()
						&& !userInterestAlgorithm.getInterestAlgorithm().trim().isEmpty()) {
					InterestAlgorithm jsonToObject=new Gson().fromJson(userInterestAlgorithm.getInterestAlgorithm(), InterestAlgorithm.class);
					
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (null != con) {
				try {
					con.close();
				} catch (Exception e) {

				}
			}
		}
		return books;
	}

}
