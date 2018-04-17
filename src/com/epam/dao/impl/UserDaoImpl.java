package com.epam.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epam.dao.AbstractDao;
import com.epam.dao.UserDao;
import com.epam.model.users.User;

/**
 * 
 * @author Stanislau_Makouski
 *
 * Implementation of {@link UserDao}
 */
@Repository
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	public User findById(Integer id) {

		User user = getByKey(id);

		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}

		return user;
	}

	public User findByUsername(String username) {

		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("username", username));

		User user = (User) criteria.uniqueResult();

		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}

		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {

		Criteria criteria = createEntityCriteria().addOrder(Order.asc("username"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<User> users = (List<User>) criteria.list();

		return users;
	}

	public void save(User user) {

		persist(user);
	}

	public void deleteByUsername(String username) {

		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("username", username));

		User user = (User) criteria.uniqueResult();

		delete(user);
	}

}
