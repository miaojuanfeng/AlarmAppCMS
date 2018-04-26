package com.alarm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alarm.model.Discuss;

@Repository
public class DiscussDaoImpl implements DiscussDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	private Session getSession() {
        return sessionFactory.openSession();
    }

	public Discuss selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Discuss where deleted=0 and id=?0";
        Query query = getSession().createQuery(hql).setInteger("0", id);
        if( query != null ){
        	return (Discuss)query.uniqueResult();
        }
        return null;
	}

	public int insert(Discuss discuss) {
		// TODO Auto-generated method stub
		Session session = getSession();
        Transaction tran = session.beginTransaction();
        session.save(discuss);    //执行
        tran.commit();  //提交
        session.close();
        return 1;
	}

	public int updateByPrimaryKey(Discuss discuss) {
		// TODO Auto-generated method stub
		String hql="update Discuss set title=:title, content=:content, modify_date=:modifyDate where deleted=0 and id=:id";
		Query query = getSession().createQuery(hql);
		query.setProperties(discuss);
        return query.executeUpdate();
	}

	public int deleteByPrimaryKey(Discuss discuss) {
		// TODO Auto-generated method stub
		String hql="update Discuss set deleted=1, modify_date=:modifyDate where deleted=0 and id=:id";
		Query query = getSession().createQuery(hql);
		query.setProperties(discuss);
        return query.executeUpdate();
	}

	public Long selectCount() {
		// TODO Auto-generated method stub
		String hql="select count(id) from Discuss where deleted=0";
		Query query = getSession().createQuery(hql);
        if( query != null ){
        	return (Long)query.uniqueResult();
        }
		return null;
	}

	public List<Discuss> selectAll(String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Discuss where deleted=0";
		if( orderBy != null && ascend != null ){
			hql = hql + " order by " + orderBy + " " + ascend;
		}
		Query query = getSession().createQuery(hql);
		if( offset > -1 ){
			query.setFirstResult(offset);
		}
		if( pageSize > 0 ){
			query.setMaxResults(pageSize);
		}
        return (List<Discuss>)query.list();
	}
	
	public List<Discuss> selectByUser(Integer user_id, String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Discuss d where deleted=0 and (d.user.id = " + user_id + "or d.id in (select c.discuss.id from Comment c where c.comment.user.id = "+user_id+" or c.user.id = "+user_id+")) group by d.id";
		if( orderBy != null && ascend != null ){
			hql = hql + " order by " + orderBy + " " + ascend;
		}
		Query query = getSession().createQuery(hql);
		if( offset > -1 ){
			query.setFirstResult(offset);
		}
		if( pageSize > 0 ){
			query.setMaxResults(pageSize);
		}
        return (List<Discuss>)query.list();
	}

}
