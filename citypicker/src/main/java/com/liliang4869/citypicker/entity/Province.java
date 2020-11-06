package com.liliang4869.citypicker.entity;



import com.liliang4869.citypicker.CommonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "province",createInDb = false)
public class Province implements CommonUtil.ObjectNameCallback {
    @Id
    @Property(nameInDb = "code")
    private Long code;
    @Property(nameInDb = "name")
    private String name;
    @Generated(hash = 2009151253)
    public Province(Long code, String name) {
        this.code = code;
        this.name = name;
    }
    @Generated(hash = 1309009906)
    public Province() {
    }
    public Long getCode() {
        return this.code;
    }
    public void setCode(Long code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Province{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String getObjectName() {
        return name;
    }
}
