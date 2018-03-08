package com.alarm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alarm.model.EaUser;

@Repository
public class EaUserDaoImpl implements EaUserDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	private Session getSession()
    {
        return sessionFactory.openSession();
    }
	
	public String selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		String retval = null;
		
		String hql="SELECT nickname from EaUser where id=?0";
        Query query = getSession().createQuery(hql).setInteger("0", id);
        if( query != null ){
        	retval = query.uniqueResult().toString();
        }
		
//		String hql="from EaUser";
//        Query query = getSession().createQuery(hql);
//        if( query != null ){
//        	List<EaUser> eaUser = query.list();
//        	System.out.println(eaUser);
//        }
        
        return retval;
	}

}
