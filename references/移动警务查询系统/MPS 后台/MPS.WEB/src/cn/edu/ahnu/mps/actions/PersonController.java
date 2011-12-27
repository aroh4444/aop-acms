package cn.edu.ahnu.mps.actions;


import cn.edu.ahnu.mps.domain.Person;
import cn.edu.ahnu.mps.service.PersonService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-9
 * Time: 下午1:09
 * To change this template use File | Settings | File Templates.
 */
@Results({@Result(name = "success", type = "redirectAction", location = "person")})
public class PersonController extends BaseController<Person>{

    @Autowired
    private PersonService personService;

    private int id;

    private Person person;

    private List<Person> list;

    private Integer ageBegin;

    private Integer ageEnd;

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;

        if(this.id > 0){
            person = personService.get(id);
        }
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

    public Object getModel() {
        return list != null ? list : person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    //返回不带id的GET请求
    //这里如果请求中封装了参数，则类似下面的url
    //http://localhost:8080/person.xml?model.name=陈杰&model.sex=m
    public HttpHeaders index() {
        //personService = new PersonService();
        list = personService.getPersons(person,ageBegin,ageEnd);
        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    //返回带id的GET请求
    public HttpHeaders show() {
        //在setId中已经获取了id对应的实例。所以这里，直接返回就可以了
        return new DefaultHttpHeaders("show");
    }

}
