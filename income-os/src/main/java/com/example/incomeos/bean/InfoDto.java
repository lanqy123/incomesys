package com.example.incomeos.bean;

import java.util.Date;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-11-08 16:01:47
 **/
public class InfoDto {
    private Integer id;
    private String name;
    private String money;
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private java.util.Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
