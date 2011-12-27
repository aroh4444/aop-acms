package cn.edu.ahnu.mps.service;

import cn.edu.ahnu.mps.domain.City;
import cn.edu.ahnu.mps.domain.Province;
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
 * Time: 上午11:56
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("unchecked")
@Service
public class ProvinceService {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Province> getProvinces(){
        try {
            Session session = sessionFactory.openSession();
            String hql = "from Province";
            Query query = session.createQuery(hql);
            return new ArrayList<Province>(query.list());
        } catch (HibernateException e) {
            throw new ServiceException("获取省信息失败",e.getCause());
        }
    }

    public List<City> getCities(){
        try {
            Session session = sessionFactory.openSession();
            String hql = "from City";
            Query query = session.createQuery(hql);
            return new ArrayList<City>(query.list());
        } catch (HibernateException e) {
            throw new ServiceException("获取城市信息失败",e.getCause());
        }

    }
}
