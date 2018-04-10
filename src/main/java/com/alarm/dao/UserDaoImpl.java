package com.alarm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alarm.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	private Session getSession() {
        return sessionFactory.openSession();
    }
	
	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		String hql="from User where deleted=0 and id=?0";
        Query query = getSession().createQuery(hql).setInteger("0", id);
        if( query != null ){
        	return (User)query.uniqueResult();
        }
        return null;
	}

	public Long selectCount() {
		// TODO Auto-generated method stub
		String hql="select count(id) from User where deleted=0";
		Query query = getSession().createQuery(hql);
        if( query != null ){
        	return (Long)query.uniqueResult();
        }
		return null;
	}

	public List<User> selectAll(String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "";
		if( orderBy != null && ascend != null ){
			hql="from User where deleted=0 order by " + orderBy + " " + ascend;
		}else{
			hql="from User where deleted=0";
		}
		Query query = getSession().createQuery(hql);
		if( offset >= -1 ){
			query.setFirstResult(offset);
		}
		if( pageSize > 0 ){
			query.setMaxResults(pageSize);
		}
        return (List<User>)query.list();
	}

	public int updateByPrimaryKey(User user) {
		// TODO Auto-generated method stub
		String hql="update User set username=:username, password=:password, nickname=:nickname, platform=:platform, modify_date=:modifyDate where deleted=0 and id=:id";
		Query query = getSession().createQuery(hql);
		query.setProperties(user);
        return query.executeUpdate();
	}

	public int insert(User user) {
		// TODO Auto-generated method stub
		Session session = getSession();
        Transaction tran = session.beginTransaction();
        session.save(user);    //执行
        tran.commit();  //提交
        session.close();
        return 1;
	}

	public int deleteByPrimaryKey(User user) {
		// TODO Auto-generated method stub
		String hql="update User set deleted=1, modify_date=:modifyDate where deleted=0 and id=:id";
		Query query = getSession().createQuery(hql);
		query.setProperties(user);
        return query.executeUpdate();
	}

	public User selectByUsername(String username) {
		// TODO Auto-generated method stub
		String hql="from User where deleted=0 and username=?0";
        Query query = getSession().createQuery(hql).setString("0", username);
        if( query != null ){
        	return (User)query.uniqueResult();
        }
        return null;
	}

	public User selectByNumber(Integer number) {
		// TODO Auto-generated method stub
		String hql="from User where deleted=0 and number=?0";
        Query query = getSession().createQuery(hql).setInteger("0", number);
        if( query != null ){
        	return (User)query.uniqueResult();
        }
        return null;
	}

}
