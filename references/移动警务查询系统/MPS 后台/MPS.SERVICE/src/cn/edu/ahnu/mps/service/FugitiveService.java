package cn.edu.ahnu.mps.service;

import cn.edu.ahnu.mps.domain.Fugitive;
import cn.edu.ahnu.mps.domain.LegalCase;
import cn.edu.ahnu.mps.domain.Suspect;
import cn.edu.ahnu.mps.exception.ServiceException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-18
 * Time: 上午8:59
 * To change this template use File | Settings | File Templates.
 */
@Service
@SuppressWarnings("unchecked")
/**
 * 提供根据案件名称模糊查询在逃人员
 * 根据
 */
public class FugitiveService {

    @Autowired
    private SessionFactory sessionFactory;

    //查询制定案件的所有在逃人员！
    //这里不是全部嫌疑人，而是在逃者
    public List<Fugitive> getSuspectsByCase(int caseId){
        try {
            Session session = sessionFactory.openSession();
            String hql = "from Fugitive f where f.legalCase.id = "+caseId;
            Query query = session.createQuery(hql);
            return new ArrayList<Fugitive>(query.list());
        } catch (HibernateException e) {
            throw new ServiceException("获取逃犯信息失败",e.getCause());
        }
    }

    //根据suspect中封装的查询条件，查询匹配到的逃犯
    public List<Fugitive> getLegalCasesBySuspect(Suspect suspect, Integer ageBegin,Integer ageEnd, Integer statureBegin, Integer statureEnd){
        try {
            Session session = sessionFactory.openSession();
            StringBuilder sb = new StringBuilder("from Fugitive f where 1=1");

            if(suspect != null){
                String name = suspect.getName();
                if(name != null && !name.equals("")){
                    name = new String(name.getBytes("iso-8859-1"),"UTF-8");
                    sb.append(" and f.suspect.name like '%").append(name).append("%'");
                }

                String sex = suspect.getSex();
                if(sex != null && !sex.equals("")){
                    sb.append(" and f.suspect.sex = '").append(sex).append("'");
                }

                String idCard = suspect.getIdCard();
                if(idCard != null && !idCard.equals("")){
                    sb.append(" and f.suspect.idCard like '%").append(idCard).append("%'");
                }

                String voice = suspect.getVoice();
                if(voice != null && !voice.equals("")){
                    voice = new String(voice.getBytes("iso-8859-1"),"UTF-8");
                    sb.append(" and f.suspect.voice = '").append(voice).append("'");
                }

                String face = suspect.getFace();
                if(face != null && !face.equals("")){
                    face = new String(face.getBytes("iso-8859-1"),"UTF-8");
                    sb.append(" and f.suspect.face = '").append(face).append("'");
                }

                String shape = suspect.getShape();
                if(shape != null && !shape.equals("")){
                    shape = new String(shape.getBytes("iso-8859-1"),"UTF-8");
                    sb.append(" and f.suspect.shape = '").append(shape).append("'");
                }
            }

            if(ageBegin != null){
                sb.append(" and f.suspect.age >= ").append(ageBegin);
            }
            if(ageEnd != null){
                sb.append(" and f.suspect.age <= ").append(ageEnd);
            }

            if(statureBegin != null){
                sb.append(" and f.suspect.stature >= ").append(statureBegin);
            }

            if(statureEnd != null){
                sb.append(" and f.suspect.stature <= ").append(statureEnd);
            }

            Query query = session.createQuery(sb.toString());
            return new ArrayList<Fugitive>(query.list());
        } catch (Exception e) {
            throw new ServiceException("获取逃犯信息失败",e.getCause());
        }
    }
}
