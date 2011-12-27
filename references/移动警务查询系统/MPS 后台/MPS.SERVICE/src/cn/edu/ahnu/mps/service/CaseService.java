package cn.edu.ahnu.mps.service;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-10
 * Time: 上午10:45
 * To change this template use File | Settings | File Templates.
 */

import cn.edu.ahnu.mps.domain.LegalCase;
import cn.edu.ahnu.mps.domain.Suspect;
import cn.edu.ahnu.mps.domain.Unit;
import cn.edu.ahnu.mps.domain.Victim;
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
 * 案件查询业务逻辑
 */
@SuppressWarnings("unchecked")
@Service
public class CaseService {

    @Autowired
    private SessionFactory sessionFactory;

    public LegalCase get(int id){
        try {
            Session session = sessionFactory.openSession();
            return (LegalCase)session.get(LegalCase.class, id);
        } catch (Exception e) {
            throw new ServiceException("获取案件信息失败",e.getCause());
        }
    }

    /**
     * 根据model中封装的查询条件进行查询
     * 并返回查询后的结果
     * @param model
     * @return
     */
    public List<LegalCase> getCases(LegalCase model, String dateBegin, String dateEnd){
        try {
            Session session = sessionFactory.openSession();
            StringBuilder hql = new StringBuilder("from LegalCase l where 1=1");

            //Todo:根据需要构造查询条件
            if(model != null){
                String category = model.getCategory();
                if(category != null){
                    category = new String(category.getBytes("ISO-8859-1"), "UTF-8");
                    hql.append(" and l.category = '").append(category).append("'");
                }

                String timing = model.getTiming();
                if(timing != null){
                    timing = new String(timing.getBytes("ISO-8859-1"), "UTF-8");
                    hql.append(" and l.timing = '").append(timing).append("'");
                }

                String site = model.getSite();
                if(site != null){
                    site = new String(site.getBytes("ISO-8859-1"), "UTF-8");
                    hql.append(" and l.site = '").append(site).append("'");
                }

                String target = model.getTarget();
                if(target != null){
                    target = new String(target.getBytes("ISO-8859-1"), "UTF-8");
                    hql.append(" and l.target = '").append(target).append("'");
                }

                String method = model.getMethod();
                if(method != null){
                    method = new String(method.getBytes("ISO-8859-1"), "UTF-8");
                    hql.append(" and l.method = '").append(method).append("'");
                }
            }

            if(dateBegin != null){
                hql.append(" and l.eventTime >= '").append(dateBegin)
                                .append("'");
            }

            if(dateEnd != null){
                hql.append(" and l.eventTime <= '").append(dateEnd).append("'");
            }

            Query query = session.createQuery(hql.toString());

            return new ArrayList<LegalCase>(query.list());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("获取案件信息失败",e.getCause());
        }
    }

    //根据案件查询嫌疑人
    public List<Suspect> getSuspectsByCase(int caseId){
        try {
            Session session = sessionFactory.openSession();
            String hql = "from Suspect where legalCase.id = "+caseId;
            Query query = session.createQuery(hql);

            return new ArrayList<Suspect>(query.list());
        } catch (HibernateException e) {
            throw new ServiceException("获取嫌疑人信息失败",e.getCause());
        }
    }

    public List<Victim> getVictimsByCase(int caseId){
        try {
            Session session = sessionFactory.openSession();
            String hql = "from Victim where legalCase.id = "+ caseId;
            Query query = session.createQuery(hql);

            return new ArrayList<Victim>(query.list());
        } catch (HibernateException e) {
            throw new ServiceException("获取受害人信息失败",e.getCause());
        }
    }

    public List<Unit> getUnitsByCase(int caseId){
        try {
            Session session = sessionFactory.openSession();
            String hql = "from Unit where legalCase.id = "+ caseId;
            Query query = session.createQuery(hql);

            return new ArrayList<Unit>(query.list());
        } catch (HibernateException e) {
            throw new ServiceException("获取受害单位信息失败",e.getCause());
        }
    }
}
