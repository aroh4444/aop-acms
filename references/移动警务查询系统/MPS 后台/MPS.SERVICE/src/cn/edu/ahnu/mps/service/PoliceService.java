package cn.edu.ahnu.mps.service;

import cn.edu.ahnu.mps.domain.Location;
import cn.edu.ahnu.mps.domain.Police;
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
 * Date: 11-2-23
 * Time: 上午10:52
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("unchecked")
@Service
public class PoliceService {

    @Autowired
    private SessionFactory sessionFactory;

    //获取所有具有位置信息的民警
    public List<Location> getLocations(){
        try {
            Session session = sessionFactory.openSession();
            String hql = "from Location";
            Query query = session.createQuery(hql);
            return new ArrayList<Location>(query.list());
        } catch (HibernateException e) {
            throw new ServiceException("获取位置信息失败",e.getCause());
        }
    }

}
