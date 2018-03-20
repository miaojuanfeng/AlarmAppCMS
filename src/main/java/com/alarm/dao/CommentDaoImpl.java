package com.alarm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alarm.model.Comment;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	private Session getSession() {
        return sessionFactory.openSession();
    }

	public Comment selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Comment where deleted=0 and id=?0";
        Query query = getSession().createQuery(hql).setInteger("0", id);
        if( query != null ){
        	return (Comment)query.uniqueResult();
        }
        return null;
	}

	public int insert(Comment comment) {
		// TODO Auto-generated method stub
		Session session = getSession();
        Transaction tran = session.beginTransaction();
        session.save(comment);    //执行
        tran.commit();  //提交
        session.close();
        return 1;
	}

	public int updateByPrimaryKey(Comment comment) {
		// TODO Auto-generated method stub
		String hql="update Comment set content=:content, modify_date=:modifyDate where deleted=0 and id=:id";
		Query query = getSession().createQuery(hql);
		query.setProperties(comment);
        return query.executeUpdate();
	}

	public int deleteByPrimaryKey(Comment comment) {
		// TODO Auto-generated method stub
		String hql="update Comment set deleted=1, modify_date=:modifyDate where deleted=0 and id=:id";
		Query query = getSession().createQuery(hql);
		query.setProperties(comment);
        return query.executeUpdate();
	}

	public Long selectCount() {
		// TODO Auto-generated method stub
		String hql="select count(id) from Comment where deleted=0";
		Query query = getSession().createQuery(hql);
        if( query != null ){
        	return (Long)query.uniqueResult();
        }
		return null;
	}

	public List<Comment> selectAll(String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "";
		if( orderBy != null && ascend != null ){
			hql="from Comment where deleted=0 order by " + orderBy + " " + ascend;
		}else{
			hql="from Comment where deleted=0";
		}
		Query query = getSession().createQuery(hql);
		if( offset >= -1 ){
			query.setFirstResult(offset);
		}
		if( pageSize > 0 ){
			query.setMaxResults(pageSize);
		}
        return (List<Comment>)query.list();
	}

	public List<Comment> selectByDiscussId(Integer DiscussId, String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "";
		if( orderBy != null && ascend != null ){
			hql="from Comment where deleted=0 and discuss_id = " + DiscussId + " order by " + orderBy + " " + ascend;
		}else{
			hql="from Comment where deleted=0 and discuss_id = " + DiscussId;
		}
		Query query = getSession().createQuery(hql);
		if( offset >= -1 ){
			query.setFirstResult(offset);
		}
		if( pageSize > 0 ){
			query.setMaxResults(pageSize);
		}
        return (List<Comment>)query.list();
	}

}
