package cn.edu.ahnu.mps.actions;

import cn.edu.ahnu.mps.domain.Fugitive;
import cn.edu.ahnu.mps.domain.Suspect;
import cn.edu.ahnu.mps.service.FugitiveService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-18
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */
@Results({@Result(name = "success",type = "redirectAction",location = "fugitive")})
public class FugitiveController extends BaseController<Fugitive>{

    private Fugitive fugitive;

    private int id;

    private List<Fugitive> list;

    private Integer ageBegin;

    private Integer ageEnd;

    private Integer statureBegin;

    private Integer statureEnd;

    private Integer caseId;

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    @Autowired
    private FugitiveService fugitiveService;

    public Object getModel(){

        return list != null ? list : fugitive;
    }

    public Fugitive getFugitive() {
        return fugitive;
    }

    public void setFugitive(Fugitive fugitive) {
        this.fugitive = fugitive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAgeBegin() {
        return ageBegin;
    }

    public void setAgeBegin(Integer ageBegin) {
        this.ageBegin = ageBegin;
    }

    public Integer getAgeEnd() {
        return ageEnd;
    }

    public void setAgeEnd(Integer ageEnd) {
        this.ageEnd = ageEnd;
    }

    public Integer getStatureBegin() {
        return statureBegin;
    }

    public void setStatureBegin(Integer statureBegin) {
        this.statureBegin = statureBegin;
    }

    public Integer getStatureEnd() {
        return statureEnd;
    }

    public void setStatureEnd(Integer statureEnd) {
        this.statureEnd = statureEnd;
    }

    @Override
    public HttpHeaders index() {

        if(this.caseId != null){
            list = fugitiveService.getSuspectsByCase(caseId);
        }else{
            if(this.fugitive != null){
                list = fugitiveService.getLegalCasesBySuspect(this.fugitive.getSuspect(),ageBegin,ageEnd,statureBegin,statureEnd);
            }else{
                list = fugitiveService.getLegalCasesBySuspect(null,ageBegin,ageEnd,statureBegin,statureEnd);
            }
        }

        this.simplyList();   //对返回的内容进行了一些裁剪
        return new DefaultHttpHeaders("index").disableCaching();
    }

    private void simplyList(){
        if(list != null){
            for(Fugitive f : list){
                f.setLegalCase(null);
                f.getSuspect().setLegalCase(null);
            }
        }
    }

    @Override
    public HttpHeaders show() {

        return null;
    }
}
