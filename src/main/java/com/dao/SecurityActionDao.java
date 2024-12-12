package com.dao;

import java.util.List;

import com.model.Module;

public interface SecurityActionDao {

	List<Module> moduleWiseActionList() throws Exception;

}
