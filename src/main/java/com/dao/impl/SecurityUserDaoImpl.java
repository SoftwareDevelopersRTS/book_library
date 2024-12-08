package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bo.PaginationBO;
import com.dao.SecurityUserDao;
import com.model.SystemUser;
import java.util.ArrayList;

@Repository
public class SecurityUserDaoImpl implements SecurityUserDao {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<SystemUser> employeeList(PaginationBO pagination) throws Exception {
		List<SystemUser> employeeList = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer("SELECT * from system_user ");
			PreparedStatement ps = con.prepareStatement(query.toString());

			ResultSet rs = ps.executeQuery();

			SystemUser user = null;
			employeeList = new ArrayList<>();
			while (rs.next()) {
				user = new SystemUser();
				user.setSystemUserId(rs.getLong("system_user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getString("mobile"));
				// user.setLastLoginDateTime((LocalDateTime)(rs.getDate("last_login_date_time")));
				employeeList.add(user);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {

				}
			}
		}

		return employeeList;
	}

	@Override
	public Long employeeListCount(PaginationBO pagination) throws Exception {
		Long count = 0L;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer("SELECT COUNT(*) AS total FROM system_user");
			PreparedStatement ps = con.prepareStatement(query.toString());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getLong("total"); // Use getLong to retrieve the count as Long
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// Log exception (optional)
				}
			}
		}

		return count;
	}

}
