package com.alarm.dao;

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
	
	private Session getSession()
    {
        return sessionFactory.openSession();
    }

	public Comment selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
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

}
