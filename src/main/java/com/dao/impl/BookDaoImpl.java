package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bo.InterestAlgorithm;
import com.bo.PaginationBO;
import com.dao.BookDao;
import com.dao.ObjectDao;
import com.google.gson.Gson;
import com.helper.Checker;
import com.model.Book;
import com.model.BookComment;
import com.model.UserInterestAlgorithm;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private ObjectDao objectDao;

	@Autowired
	private DataSource dataSource;

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
					InterestAlgorithm jsonToObject = new Gson().fromJson(userInterestAlgorithm.getInterestAlgorithm(),
							InterestAlgorithm.class);
					if (null != jsonToObject && null != jsonToObject.getFavouriteBookSet()
							&& !jsonToObject.getFavouriteBookSet().isEmpty()) {
						query.append(" ORDER BY FIELD(book_id, ").append(jsonToObject.getFavouriteBookSet())
								.append(") DESC");
					}
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

	@Override
	public List<Book> bookListAdminPanel(PaginationBO pagination, String timeZone) throws Exception {
		Connection con = null;
		List<Book> books = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer(
					"SELECT * FROM book b LEFT JOIN library l ON b.library_id=l.library_id ");
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

			Book book = null;
			books = new ArrayList<>();
			while (rs.next()) {
				book = new Book();
				book.setBookId(rs.getLong("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPages(rs.getInt("pages"));
				book.setRating(rs.getString("rating"));
				book.setBookUniqueUid(rs.getString("book_unique_uid"));
				book.setTotalStock(rs.getInt("total_stock"));
				book.setAvailableStock(rs.getInt("availalble_stock"));
				book.setIsActive(rs.getBoolean("is_active"));
				book.setLibraryName(rs.getString("library_name"));

				books.add(book);
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
			if (null != ps) {
				try {
					ps.close();
				} catch (Exception e) {

				}
			}
			if (null != rs) {
				try {
					rs.close();
				} catch (Exception e) {

				}
			}
		}
		return books;
	}

	@Override
	public Long bookListCountAdminPanel(PaginationBO pagination) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long totalCount = 0L;

		try {
			con = dataSource.getConnection();
			String query = "SELECT COUNT(*) AS total_count FROM book";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			if (rs.next()) {
				totalCount = rs.getLong("total_count");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
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
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return totalCount;
	}

	@Override
	public List<BookComment> getBookCommentList(PaginationBO pagination) throws Exception {
		List<BookComment> bookCommentList = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer(
					"SELECT bc.*,u.first_name,u.last_name from  book_comment bc INNER JOIN user u on bc.user_id=u.user_id where book_comment_id is not null ");
			if (null != pagination.getId() && pagination.getId() > 0) {
				query.append(" AND bc.book_id= ? ");
			}

			if (Checker.paginationChecker(pagination)) {
				System.out.print("INside first");
				query.append(" LIMIT ? OFFSET ?");
			}

			ps = con.prepareStatement(query.toString());
			int count = 1;
			if (null != pagination.getId() && pagination.getId() > 0) {
				ps.setLong(count++, pagination.getId());
			}
			if (Checker.paginationChecker(pagination)) {
				System.out.print("INside first");
				ps.setInt(count++, pagination.getNumPerPage());
				ps.setInt(count++, (pagination.getPageNo() - 1) * pagination.getNumPerPage());
			}
			System.out.println("Query For Comment List==>" + ps.toString());
			rs = ps.executeQuery();
			bookCommentList = new ArrayList<>();
			BookComment bookComment = null;
			while (rs.next()) {
				bookComment = new BookComment();
				bookComment.setBookCommentId(rs.getLong("book_comment_id"));
				bookComment.setCommentText(rs.getString("comment_text"));
				bookComment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String commentedBy = (firstName != null && !firstName.isEmpty() ? firstName : "")
						+ (lastName != null && !lastName.isEmpty()
								? (firstName != null && !firstName.isEmpty() ? " " : "") + lastName
								: "");
				bookComment.setCommentedBy(commentedBy);
				bookComment.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				bookComment.setIsDisabled(rs.getBoolean("is_disabled"));
				bookCommentList.add(bookComment);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
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
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bookCommentList;
	}

	@Override
	public Long getBookCommentsCount(PaginationBO pagination) throws Exception {
		Long commentCount = 0L;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer(
					"SELECT COUNT(*) as totalComments FROM book_comment bc INNER JOIN user u ON bc.user_id = u.user_id WHERE bc.book_comment_id IS NOT NULL");

			if (pagination.getId() != null && pagination.getId() > 0) {
				query.append(" AND bc.book_id = ?");
			}

			ps = con.prepareStatement(query.toString());

			if (pagination.getId() != null && pagination.getId() > 0) {
				ps.setLong(1, pagination.getId());
			}

			rs = ps.executeQuery();

			if (rs.next()) {
				commentCount = rs.getLong("totalComments");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
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
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return commentCount;
	}

}
