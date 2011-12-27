package cn.edu.ahnu.mps.actions;

import cn.edu.ahnu.mps.domain.LegalCase;
import cn.edu.ahnu.mps.domain.Suspect;
import cn.edu.ahnu.mps.service.CaseService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-17
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
@Results({@Result(name = "success",type = "redirectAction",location = "suspect")})
public class SuspectController extends BaseController<Suspect>{

    private Suspect suspect;

    private int id;

    private List<Suspect> list;

    private int caseId;

    @Autowired
    private CaseService caseService;

    public Suspect getSuspect() {
        return suspect;
    }

    public void setSuspect(Suspect suspect) {
        this.suspect = suspect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public Object getModel() {
        return list != null ? list : suspect;
    }

    @Override
    public HttpHeaders index() {
        //
        list = caseService.getSuspectsByCase(caseId);
        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    public HttpHeaders show() {
        return null;
    }
}
