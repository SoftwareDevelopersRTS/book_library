package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bo.PaginationBO;
import com.dao.CommonAppSettingDao;
import com.model.CommonAppSetting;
import java.util.ArrayList;

@Repository
public class CommonAppSettingDaoImpl implements CommonAppSettingDao {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<CommonAppSetting> getCommonAppSettingList(PaginationBO pagination) throws Exception {
		List<CommonAppSetting> commonAppSettingList = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer("SELECT  * from common_app_setting ");
			PreparedStatement ps = con.prepareStatement(query.toString());

			ResultSet rs = ps.executeQuery();

			commonAppSettingList = new ArrayList<>();
			CommonAppSetting commonAppSetting = null;
			while (rs.next()) {
				commonAppSetting = new CommonAppSetting();
				commonAppSetting.setCommonAppSettingId(rs.getLong("common_app_setting_id"));
				commonAppSetting.setSettingName(rs.getString("setting_name"));
				commonAppSetting.setDescription(rs.getString("description"));
				commonAppSetting.setSettingType(rs.getString("setting_type"));
				commonAppSetting.setSettingValue(rs.getString("setting_value"));
				commonAppSettingList.add(commonAppSetting);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return commonAppSettingList;
	}

	@Override
	public Long getCommonAppSettingListCount(PaginationBO pagination) throws Exception {
		Long count = 0L;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String query = "SELECT COUNT(*) AS total_count FROM common_app_setting";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getLong("total_count");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return count;
	}

}
