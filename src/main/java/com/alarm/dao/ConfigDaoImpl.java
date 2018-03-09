package com.alarm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alarm.model.Config;

@Repository
public class ConfigDaoImpl implements ConfigDao {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	private Session getSession()
    {
        return sessionFactory.openSession();
    }

	public Config selectByTitle(String title) {
		// TODO Auto-generated method stub
		String hql="from Config where deleted=0 and title=?0";
		Query query = getSession().createQuery(hql).setString("0", title);
        if( query != null ){
        	return (Config)query.uniqueResult();
        }
		return null;
	}

	public int updateValueByTitle(Config config) {
		// TODO Auto-generated method stub
		String hql="update Config set value=:value where deleted=0 and title=:title";
		Query query = getSession().createQuery(hql).setProperties(config);
        if( query != null ){
        	return query.executeUpdate();
        }
		return 0;
	}
	
}
