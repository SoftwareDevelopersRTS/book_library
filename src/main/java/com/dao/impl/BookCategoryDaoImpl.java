package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bo.PaginationBO;
import com.dao.BookCategoryDao;
import com.model.BookCategory;


@Repository
public class BookCategoryDaoImpl implements BookCategoryDao {

	@Autowired
	private DataSource dataSource;
	@Override
	public List<BookCategory> getBookCategoryList(PaginationBO pagination) throws Exception {
		List<BookCategory> bookCategoryList=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			con=dataSource.getConnection();
			StringBuffer query=new StringBuffer("SELECT bc.* from book_category bc where bc.book_category_id is not null");
			
		}catch(Exception e) {
			throw e;
		}finally {
			if(null!=con) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(null!=ps) {
				try {
					ps.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(null!=rs) {
				try {
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bookCategoryList;
	}

	@Override
	public Long getBookCategoryCount(PaginationBO pagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
