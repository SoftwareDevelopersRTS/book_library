package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bo.PaginationBO;
import com.dao.BookCategoryDao;
import com.helper.Checker;
import com.model.BookCategory;

@Repository
public class BookCategoryDaoImpl implements BookCategoryDao {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<BookCategory> getBookCategoryList(PaginationBO pagination) throws Exception {
		List<BookCategory> bookCategoryList = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer(
					"SELECT bc.* from book_category bc where bc.book_category_id is not null");
			if (Checker.paginationChecker(pagination)) {
				query.append(" LIMIT ? OFFSET ?");
			}

			ps = con.prepareStatement(query.toString());
			int count = 1;
			if (Checker.paginationChecker(pagination)) {
				ps.setInt(count++, pagination.getNumPerPage());
				ps.setInt(count++, (pagination.getPageNo() - 1) * pagination.getNumPerPage());
			}
			rs = ps.executeQuery();
			bookCategoryList = new ArrayList<>();
			BookCategory category = null;
			while (rs.next()) {
				category = new BookCategory();
				category.setBookCategoryId(rs.getLong("book_category_id"));
				category.setBookCategoryUniqueId(rs.getString("book_category_unique_id"));
				category.setBookCategoryName(rs.getString("book_category_name"));
				category.setDescription(rs.getString("description"));
				category.setCategoryImagePath(rs.getString("category_image_path"));
				category.setIsActive(rs.getBoolean("is_active"));
				category.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				bookCategoryList.add(category);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (null != con) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != ps) {
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != rs) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bookCategoryList;
	}

	@Override
	public Long getBookCategoryCount(PaginationBO pagination) throws Exception {
		Long count = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			String query = "SELECT COUNT(*) FROM book_category WHERE book_category_id IS NOT NULL";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getLong(1);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return count;
	}

}
