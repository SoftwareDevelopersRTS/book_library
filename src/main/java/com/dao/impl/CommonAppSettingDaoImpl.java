package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bo.PaginationBO;
import com.dao.CommonAppSettingDao;
import com.model.CommonAppSetting;

@Repository
public class CommonAppSettingDaoImpl implements CommonAppSettingDao {

	@Override
	public List<CommonAppSetting> getCommonAppSettingList(PaginationBO pagination) throws Exception {
		List<CommonAppSetting> commonAppSettingList = null;
		try {

		} catch (Exception e) {
			throw e;
		}
		return commonAppSettingList;
	}

	@Override
	public Long getCommonAppSettingListCount(PaginationBO pagination) throws Exception {
		Long commonAppSettingListCount = 0L;
		try {

		} catch (Exception e) {
			throw e;
		}
		return commonAppSettingListCount;
	}

}
