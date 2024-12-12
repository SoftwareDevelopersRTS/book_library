package com.dao;

import java.util.List;

import com.bo.PaginationBO;
import com.model.CommonAppSetting;

public interface CommonAppSettingDao {

	List<CommonAppSetting> getCommonAppSettingList(PaginationBO pagination) throws Exception;

	Long getCommonAppSettingListCount(PaginationBO pagination) throws Exception;
}
