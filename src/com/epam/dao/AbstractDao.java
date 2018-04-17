package com.epam.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Stanislau_Makouski
 *
 * Defines abstract hibernate methods for work with database tables
 * 
 * @param <PK> specifies identifier
 * @param <T> specifies persistent class
 */
public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;


	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Constructor saves second parameter in variable in order to use it in hibernate operations
	 * 
	 */
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	/**
	 * 
	 * @return hibernate session
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Method to return database data by specified key
	 * 
	 * @param unique identifier
	 * @return a persistent instance
	 */
	@SuppressWarnings("unchecked")
	protected T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	/**
	 * Method to save entity in the database
	 * 
	 * @param entity to make persistent
	 */
	protected void persist(T entity) {
		getSession().persist(entity);
	}

	/**
	 * Method to update data about certain instance 
	 * 
	 * @param entity containing updated information
	 */
	protected void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * Method to delete instance from database
	 * 
	 * @param entity to be removed
	 */
	protected void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	 * Method retrieve instance of {@link Criteria} essential for information search
	 * 
	 * @return {@link Criteria}
	 */
	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}
}
