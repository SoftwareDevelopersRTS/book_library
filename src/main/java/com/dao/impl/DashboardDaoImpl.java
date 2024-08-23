package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import com.bo.DashboardCountBO;
import com.dao.DashboardDao;
import com.helper.TypeConstants;

public class DashboardDaoImpl implements DashboardDao{
	
	private final DataSource dataSource;
	
	public DashboardDaoImpl(DataSource dataSource) {
		this.dataSource= dataSource;
	}

	@Override
	public DashboardCountBO dashboardAllCounts() throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		DashboardCountBO dashboardCountBO=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			StringBuffer query=new StringBuffer("SELECT \r\n"
					+ "    SUM(total_stock) AS total_stock,\r\n"
					+ "    SUM(availalble_stock) AS availalble_stock,\r\n"
					+ "    SUM(CASE WHEN is_active THEN total_stock ELSE 0 END) AS total_active_stock,\r\n"
					+ "    SUM(CASE WHEN is_active = false THEN total_stock ELSE 0 END) AS total_inactive_stock,\r\n"
					+ "    SUM(CASE WHEN is_active THEN availalble_stock ELSE 0 END) AS active_available_stock,\r\n"
					+ "    SUM(CASE WHEN is_active = false THEN availalble_stock ELSE 0 END) AS inactive_available_stock\r\n"
					+ "FROM \r\n"
					+ "    book");
			 ps=con.prepareStatement(query.toString());
			 rs=ps.executeQuery();
			 dashboardCountBO=new DashboardCountBO();

			
			
		}
		catch(Exception e) {
			
		}
		finally{
			if(null!=con) {
				try {
					con.close();
				}catch(Exception e) {
					
				}
			}
			if(null!=ps) {
				try {
					ps.close();
				}catch(Exception e) {
					
				}
			}
			if(null!=rs) {
				try {
					rs.close();
				}catch(Exception e) {
					
				}
			}
		}
		return dashboardCountBO;
	}

}
