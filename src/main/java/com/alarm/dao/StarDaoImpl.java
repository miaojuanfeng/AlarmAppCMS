package com.alarm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alarm.model.Star;

@Repository
public class StarDaoImpl implements StarDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	private Session getSession() {
        return sessionFactory.openSession();
    }
	
	public Star selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Star where deleted=0 and id=?0";
        Query query = getSession().createQuery(hql).setInteger("0", id);
        if( query != null ){
        	return (Star)query.uniqueResult();
        }
        return null;
	}

	public Long selectCount() {
		// TODO Auto-generated method stub
		String hql="select count(id) from Star where deleted=0";
		Query query = getSession().createQuery(hql);
        if( query != null ){
        	return (Long)query.uniqueResult();
        }
		return null;
	}

	public List<Star> selectAll(String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "";
		if( orderBy != null && ascend != null ){
			hql="from Star where deleted=0 order by " + orderBy + " " + ascend;
		}else{
			hql="from Star where deleted=0";
		}
		Query query = getSession().createQuery(hql);
		if( offset > -1 ){
			query.setFirstResult(offset);
		}
		if( pageSize > 0 ){
			query.setMaxResults(pageSize);
		}
        return (List<Star>)query.list();
	}

	public int updateByPrimaryKey(Star star) {
		// TODO Auto-generated method stub
		String hql="update Star set week=:week, star_num=:starNum, like_num=:likeNum, like_user=:likeUser, modify_date=:modifyDate where deleted=0 and id=:id";
		Query query = getSession().createQuery(hql);
		query.setProperties(star);
        return query.executeUpdate();
	}

	public int insert(Star star) {
		// TODO Auto-generated method stub
		Session session = getSession();
        Transaction tran = session.beginTransaction();
        session.save(star);    //执行
        tran.commit();  //提交
        session.close();
        return 1;
	}

	public int deleteByPrimaryKey(Star user) {
		// TODO Auto-generated method stub
		String hql="update User set deleted=1, modify_date=:modifyDate where deleted=0 and id=:id";
		Query query = getSession().createQuery(hql);
		query.setProperties(user);
        return query.executeUpdate();
	}

	public List<Star> selectAll(Integer week) {
		// TODO Auto-generated method stub
		String hql = "from Star where deleted=0 and week="+week+" order by star_num desc";
		Query query = getSession().createQuery(hql);
        return (List<Star>)query.list();
	}

	public Star selectByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		String hql="from Star where deleted=0 and user_id="+user_id;
		Query query = getSession().createQuery(hql);
        if( query != null ){
        	return (Star)query.uniqueResult();
        }
		return null;
	}

}
