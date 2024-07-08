package com.dao.impl;

import com.bo.PaginationBO;
import com.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Address;
import com.model.User;

import jakarta.transaction.Transactional;

@Repository("UserDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private DataSource dataSource;

	public List<User> getUserList(PaginationBO pagination) throws Exception {
		List<User> userList = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer(
					"SELECT u.*,a.* FROM user u LEFT JOIN address a ON u.address_id=a.address_id where u.user_id is not null ");
			if (null != pagination.getSearchKey() && !pagination.getSearchKey().isEmpty()) {
				query.append(
						" AND ( u.first_name LIKE ? OR u.last_name LIKE ? OR u.mobile LIKE ? OR u.email LIKE ? ) ");
			}
			if (null != pagination.getOrderBy() && null != pagination.getOrderType()
					&& !pagination.getOrderBy().isEmpty() && !pagination.getOrderType().isEmpty()) {
				query.append(" ORDER BY " + pagination.getOrderBy() + " " + pagination.getOrderType());
			} else {
				query.append(" ORDER BY u.user_register_date DESC ");
			}
			if (null != pagination.getPageNo() && null != pagination.getNumPerPage() && pagination.getPageNo() > 0
					&& pagination.getNumPerPage() > 0) {
				query.append(" LIMIT ? OFFSET ?");
			}
			int count = 1;
			ps = con.prepareStatement(query.toString());
			if (null != pagination.getSearchKey() && !pagination.getSearchKey().isEmpty()) {
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
			}
			if (null != pagination.getPageNo() && null != pagination.getNumPerPage() && pagination.getPageNo() > 0
					&& pagination.getNumPerPage() > 0) {
				ps.setInt(count++, pagination.getNumPerPage());
				ps.setInt(count++, (pagination.getPageNo() - 1) * pagination.getNumPerPage());
			}

			rs = ps.executeQuery();
			userList = new ArrayList<>();
			User user = null;
			Address address = null;
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setBirthDate(rs.getString("birth_date"));
				user.setMobile(rs.getString("mobile"));
				user.setEmail(rs.getString("email"));
				if (rs.getLong("address_id") > 0) {
					address = new Address();
					address.setAddressId(rs.getLong("address_id"));
					address.setCity(rs.getString("city"));
					address.setStreet(rs.getString("street"));
					address.setState(rs.getString("state"));
					address.setCountry(rs.getString("country"));
					address.setZipCode(rs.getString("zip_code"));
					user.setAddress(address);
				}
				user.setUserUniqueUID(rs.getString("user_unique_uid"));
				user.setIsActive(rs.getBoolean("is_active"));
				user.setUserType(rs.getString("user_type"));
				if (rs.getTimestamp("user_register_date") != null) {
					user.setUserRegisterDate(rs.getTimestamp("user_register_date").toLocalDateTime());
				}

				userList.add(user);
			}

		} catch (SQLException e) {
			throw e;
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
		return userList;
	}

	@Override
	public Long getUserCount(PaginationBO pagination) throws Exception {
		Long count = 0L;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer(
					"SELECT COUNT(*) FROM user u LEFT JOIN address a ON u.address_id=a.address_id where u.user_id is not null ");
			if (null != pagination.getSearchKey() && !pagination.getSearchKey().isEmpty()) {
				query.append(
						" AND ( u.first_name LIKE ? OR u.last_name LIKE ? OR u.mobile LIKE ? OR u.email LIKE ? ) ");
			}
			int countIndex = 1;
			ps = con.prepareStatement(query.toString());
			if (null != pagination.getSearchKey() && !pagination.getSearchKey().isEmpty()) {
				ps.setString(countIndex++, "%" + pagination.getSearchKey() + "%");
				ps.setString(countIndex++, "%" + pagination.getSearchKey() + "%");
				ps.setString(countIndex++, "%" + pagination.getSearchKey() + "%");
				ps.setString(countIndex++, "%" + pagination.getSearchKey() + "%");
			}

			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (SQLException e) {
			throw e;
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
		return count;
	}

}
