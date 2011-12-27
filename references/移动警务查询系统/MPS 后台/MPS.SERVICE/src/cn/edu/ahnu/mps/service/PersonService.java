package cn.edu.ahnu.mps.service;

import cn.edu.ahnu.mps.domain.Person;
import cn.edu.ahnu.mps.exception.ServiceException;
import org.aspectj.weaver.patterns.ExactAnnotationTypePattern;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-9
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("unchecked")
@Service
public class PersonService{

    @Autowired
    private SessionFactory sessionFactory;

    public Person get(int id){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Person where id = :id");
        query.setInteger("id",id);

        List<Person> personList = (List<Person>)query.list();
        return personList.size()>0 ? personList.get(0) : null;
    }

    public List<Person> getAll(){

        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("person.selectAll");
        List<Person> persons = query.list();
        System.out.println("当前大小："+persons.size());
        return persons;
    }

    /**
     * 根据给定的条件查询人口信息列表
     * @param person
     * @param ageBegin
     * @param ageEnd
     * @return
     */
    public List<Person> getPersons(Person person, Integer ageBegin, Integer ageEnd) {

        try {
            Session session = sessionFactory.openSession();
            StringBuilder hql = new StringBuilder("from Person p where 1=1");

            //Todo:根据需要构造查询条件
            if(person != null){
                String name = person.getName();
                if(name != null && !name.equals("")){
                    name = new String(name.getBytes("iso-8859-1"),"UTF-8");
                    //System.out.println("姓名："+name);
                    hql.append(" and p.name like '%"+ name).append("%'");
                }
                String sex = person.getSex();
                if(sex != null && !sex.equals("")){
                    hql.append(" and p.sex = '" + sex).append("'");
                }

                String idCard = person.getIdCard();
                if(idCard != null && !idCard.equals("")){
                    hql.append(" and p.idCard like '%" + idCard).append("%'");
                }

                String province = person.getProvince();
                if(province != null && !province.equals("")){
                    province = new String(province.getBytes("iso-8859-1"),"UTF-8");
                    hql.append(" and p.province = '" + province).append("'");
                }

                String city = person.getCity();
                if(city != null && !city.equals("")){
                    city = new String(city.getBytes("iso-8859-1"),"UTF-8");
                    hql.append(" and p.city = '" + city).append("'");
                }

                String work = person.getWork();
                if(work != null && !work.equals("")){
                    work = new String(work.getBytes("iso-8859-1"),"UTF-8");
                    hql.append(" and p.work = '" + work).append("'");
                }

            }
            if(ageBegin != null){
                hql.append(" and p.age >=" + ageBegin);

            }

            if(ageEnd != null){
                hql.append(" and p.age <= "+ ageEnd);
            }

            Query query = session.createQuery(hql.toString());

            return new ArrayList<Person>(query.list());

        } catch (Exception e) {
            throw new ServiceException("查询人口信息失败",e.getCause());
        }
    }
}
