package com.mobile.police.person;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-2-9
 * Time: 上午11:32
 * To change this template use File | Settings | File Templates.
 */

/**
 * 人口信息实体
 */

//为了在两个Activity之间可以传递对象，这里实现Parcelable接口
public class Person implements Parcelable{


    private int id;

    private String idCard;
    private String name;
    private String sex;
    private String race;
    private String birthTime;
    private String country;
    private String province;
    private String city;
    private String detailAddr;
    private String work;

    private String remark;
    private int age;

    public Person(){}
    
    public Person(Parcel source){
		id = source.readInt();
		idCard = source.readString();
		name = source.readString();
		sex = source.readString();
		race = source.readString();
		birthTime = source.readString();
		country = source.readString();
		province = source.readString();
		city = source.readString();
		detailAddr = source.readString();
		work = source.readString();
		remark = source.readString();
		age = source.readInt();
    }
    
    

    public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Creator() {

		@Override
		public Person createFromParcel(Parcel source) {

			return new Person(source);
		}

		@Override
		public Person[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Person[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		
		parcel.writeInt(id);
		parcel.writeString(idCard);
		parcel.writeString(name);
		parcel.writeString(sex);
		parcel.writeString(race);
		parcel.writeString(birthTime);
		parcel.writeString(country);
		parcel.writeString(province);
		parcel.writeString(city);
		parcel.writeString(detailAddr);
		parcel.writeString(work);
		parcel.writeString(remark);
		parcel.writeInt(age);
		
	}
}
