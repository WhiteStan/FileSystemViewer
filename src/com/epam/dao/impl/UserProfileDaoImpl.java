package com.epam.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.epam.dao.AbstractDao;
import com.epam.dao.UserProfileDao;
import com.epam.model.users.UserProfile;

/**
 * 
 * @author Stanislau_Makouski
 *
 * Implementation of {@link UserProfileDao}
 */
@Repository
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao {

	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll() {

		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("type"));

		return (List<UserProfile>) criteria.list();
	}
}
