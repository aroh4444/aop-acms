package cn.edu.ahnu.mps.actions;

import cn.edu.ahnu.mps.domain.Suspect;
import cn.edu.ahnu.mps.domain.Unit;
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
 * Time: 上午11:49
 * To change this template use File | Settings | File Templates.
 */
@Results({@Result(name = "success",type = "redirectAction",location = "unit")})
public class UnitController extends BaseController<Unit>{


    private Unit unit;

    private int id;

    private List<Unit> list;

    private int caseId;

    @Autowired
    private CaseService caseService;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
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
        return list != null ? list : unit;
    }

    @Override
    public HttpHeaders index() {
        //
        list = caseService.getUnitsByCase(caseId);
        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    public HttpHeaders show() {
        return null;
    }
}
