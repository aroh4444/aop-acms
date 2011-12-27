package cn.edu.ahnu.mps.service;

import cn.edu.ahnu.mps.domain.Work;
import cn.edu.ahnu.mps.exception.ServiceException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-21
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("unchecked")
@Service
public class WorkService {

    @Autowired
    private SessionFactory sessionFactory;


    public List<Work> getWorks(){
        try {
            Session session = sessionFactory.openSession();
            String hql = "from Work";
            Query query = session.createQuery(hql);
            return new ArrayList<Work>(query.list());
        } catch (HibernateException e) {
            throw new ServiceException("获取工作信息失败",e.getCause());
        }
    }

}
