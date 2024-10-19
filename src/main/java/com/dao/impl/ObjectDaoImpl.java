package com.dao.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.ObjectDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import com.dao.AbstractDao;
@Repository("ObjectDao")
@Transactional
public class ObjectDaoImpl  implements ObjectDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveObject(Object object) {
		entityManager.persist(object);
	}

	@Override
	public void updateObject(Object object) {
		entityManager.merge(object);

	}

	@Override
	public <T> T getObjectById(Class<T> entityClass, Serializable id) {
		return entityManager.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObjectByParam(Class<T> entity, String param, Object paramValue) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
		Root<T> root = criteriaQuery.from(entity);
		Predicate predicate = criteriaBuilder.equal(root.get(param), paramValue);
		criteriaQuery.where(predicate);
		try {
			return entityManager.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObjectByTwoParams(Class<T> entity, String param1, Object paramValue1, String param2, Object paramValue2) {
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
	    Root<T> root = criteriaQuery.from(entity);

	    // Create predicates for each parameter
	    Predicate predicate1 = criteriaBuilder.equal(root.get(param1), paramValue1);
	    Predicate predicate2 = criteriaBuilder.equal(root.get(param2), paramValue2);

	    // Combine the predicates using the AND operator
	    criteriaQuery.where(criteriaBuilder.and(predicate1, predicate2));

	    try {
	        return entityManager.createQuery(criteriaQuery).getSingleResult();
	    } catch (Exception e) {
	        return null;
	    }
	}
	@Override
	public void deleteObject(Object entity) {
		entityManager.remove(entity);
	}


	@Override
	public <T> List<T> getAllRecords(Class<T> entityClass) {
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
	    Root<T> root = criteriaQuery.from(entityClass);
	    criteriaQuery.select(root);

	    try {
	    	return entityManager.createQuery(criteriaQuery).getResultList();
	    } catch (Exception e) {
	        return null;
	    }
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListByTwoParams(Class<T> entity, String param1, Object paramValue1, String param2, Object paramValue2) {
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
	    Root<T> root = criteriaQuery.from(entity);

	    // Create predicates for each parameter
	    Predicate predicate1 = criteriaBuilder.equal(root.get(param1), paramValue1);
	    Predicate predicate2 = criteriaBuilder.equal(root.get(param2), paramValue2);

	    // Combine the predicates using the AND operator
	    criteriaQuery.where(criteriaBuilder.and(predicate1, predicate2));

	    try {
	        return entityManager.createQuery(criteriaQuery).getResultList();
	    } catch (Exception e) {
	        return Collections.emptyList();
	    }
	}
	
	


	@Override
	public <T> T getFirstRecordOrderedBy(Class<T> entity, String restrictionParam, Object restrictionValue,
			String orderByParam, boolean ascending) throws Exception {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
	    Root<T> root = criteriaQuery.from(entity);

	    // Apply the restriction (filter) based on the provided param and value
	    Predicate restrictionPredicate = criteriaBuilder.equal(root.get(restrictionParam), restrictionValue);
	    criteriaQuery.where(restrictionPredicate);

	    // Set the order by condition
	    if (ascending) {
	        criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderByParam)));
	    } else {
	        criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderByParam)));
	    }

	    try {
	        // Limit the result to 1 record
	        return entityManager.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();
	    } catch (Exception e) {
	        return null; // Handle the exception based on your needs (e.g., logging)
	    }
	}




}
