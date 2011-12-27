package cn.edu.ahnu.mps.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-23
 * Time: 上午10:39
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Location {

    @Id
    private Integer id;

    private Double latitude;

    private Double longitude;

    @OneToOne
    @JoinColumn(name = "ID")
    private Police police;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Police getPolice() {
        return police;
    }

    public void setPolice(Police police) {
        this.police = police;
    }
}
