package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import com.bo.DashboardCountBO;
import com.bo.PaginationBO;
import com.dao.DashboardDao;

@Repository
public class DashboardDaoImpl implements DashboardDao {

	private final DataSource dataSource;

	public DashboardDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public DashboardCountBO dashboardAllCounts(PaginationBO pagination) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		DashboardCountBO dashboardCountBO = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer("SELECT \r\n" + "    SUM(total_stock) AS total_stock,\r\n"
					+ "    SUM(availalble_stock) AS availalble_stock,\r\n"
					+ "    SUM(CASE WHEN is_active THEN total_stock ELSE 0 END) AS total_active_stock,\r\n"
					+ "    SUM(CASE WHEN is_active = false THEN total_stock ELSE 0 END) AS total_inactive_stock,\r\n"
					+ "    SUM(CASE WHEN is_active THEN availalble_stock ELSE 0 END) AS active_available_stock,\r\n"
					+ "    SUM(CASE WHEN is_active = false THEN availalble_stock ELSE 0 END) AS inactive_available_stock\r\n"
					+ "FROM \r\n" + "    book");
			ps = con.prepareStatement(query.toString());
			System.out.println("Dashboard Count Query---->" + ps.toString());
			rs = ps.executeQuery();
			dashboardCountBO = new DashboardCountBO();

			while (rs.next()) {
				dashboardCountBO.setBooksTotalCount(
						"Total(" + rs.getLong("total_stock") + ")," + "Active(" + rs.getLong("total_active_stock")
								+ ")," + "Inactive(" + rs.getLong("total_inactive_stock") + ")");
				dashboardCountBO.setBooksAvailableCount("Total(" + rs.getLong("availalble_stock") + ")," + "Active("
						+ rs.getLong("active_available_stock") + ")," + "Inactive("
						+ rs.getLong("inactive_available_stock") + ")");
			}
			ps.close();
			rs.close();

			query = new StringBuffer("SELECT \r\n" + " COUNT(book_category_id) AS total_category_count,"
					+ "    COUNT(CASE WHEN is_active = true THEN book_category_id ELSE NULL END) AS active_category_count,\r\n"
					+ "    COUNT(CASE WHEN is_active = false OR is_active IS NULL THEN book_category_id ELSE NULL END) AS inactive_category_count \r\n"
					+ "FROM \r\n" + "    book_category;");

			ps = con.prepareStatement(query.toString());
			System.out.println("Dashboard BookCategory  Query---->" + ps.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				dashboardCountBO.setBookCategoryTotalCount("Total(" + rs.getLong("total_category_count") + "),"
						+ "Active(" + rs.getLong("active_category_count") + ")," + "Inactive("
						+ rs.getLong("inactive_category_count") + ")");
			}

			ps.close();
			rs.close();

			query = new StringBuffer("SELECT \r\n"
					+ "    COUNT(user_id) AS total_user_count,\r\n"
					+ "    COUNT(CASE WHEN is_active = true THEN user_id ELSE NULL END) AS active_user_count,\r\n"
					+ "    COUNT(CASE WHEN is_active = false OR is_active IS NULL THEN user_id ELSE NULL END) AS inactive_user_count\r\n"
					+ "FROM user;");

			ps = con.prepareStatement(query.toString());
			System.out.println("Dashboard User Count Query---->" + ps.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				dashboardCountBO.setUserCount(
						"Total(" + rs.getLong("total_user_count") + ")," + "Active(" + rs.getLong("active_user_count")
								+ ")," + "Inactive(" + rs.getLong("inactive_user_count") + ")");
			}

		} catch (Exception e) {

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
		return dashboardCountBO;
	}

}
