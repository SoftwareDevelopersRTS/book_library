package com.dao;

import java.io.Serializable;

public interface ObjectDao {

	public void saveObject(Object object);

	public void updateObject(Object object);

	public <T> T getObjectById(Class<T> entityClass, Serializable id);

	public <T> T getObjectByParam(Class<T> entity, String param, Object paramValue);
	
	public <T> T getObjectByTwoParams(Class<T> entity, String param1, Object paramValue1, String param2, Object paramValue2);
	
	public void deleteObject(Object entity);


}
