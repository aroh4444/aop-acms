package cn.edu.ahnu.mps.actions;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-10
 * Time: 上午11:08
 * To change this template use File | Settings | File Templates.
 */

import cn.edu.ahnu.mps.domain.LegalCase;
import cn.edu.ahnu.mps.service.CaseService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 案件查询
 */
@Results({@Result(name = "success", type = "redirectAction", location = "case")})
public class CaseController extends BaseController<LegalCase>{

    private int id;

    private LegalCase legalCase;

    private List<LegalCase> list;

    @Autowired
    private CaseService caseService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        if(this.id > 0){
            this.legalCase = caseService.get(id);
        }
    }

    public Object getModel(){

        return list != null ? list : legalCase;
    }

    public LegalCase getLegalCase() {
        return legalCase;
    }

    public void setLegalCase(LegalCase legalCase) {
        this.legalCase = legalCase;
    }

    private String dateBegin;
    private String dateEnd;

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    @Override
    public HttpHeaders index() {
        list = caseService.getCases(legalCase, dateBegin, dateEnd);
        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }
}
