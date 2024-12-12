package com.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.ObjectDao;
import com.dao.SecurityActionDao;
import com.model.Module;
import com.model.SecurityAction;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SecurityActionDaoImpl implements SecurityActionDao {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ObjectDao objectDao;

	@Override
	public List<Module> moduleWiseActionList() throws Exception {
		List<Module> moduleList = null;
		try {
			moduleList = new ArrayList<>();
			moduleList = objectDao.getAllRecords(Module.class);

			for (Module module : moduleList) {
				module.setSecurityActionList(objectDao.getListByOneParam(SecurityAction.class, "module", module));
			}

		} catch (Exception e) {
			throw e;
		}
		return moduleList;
	}

}
