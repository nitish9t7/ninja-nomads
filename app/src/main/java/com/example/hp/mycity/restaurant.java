package com.example.hp.mycity;

/**
 * Created by hp on 25-02-2019.
 */

public class restaurant {
    private String name;
    private String add;
    private String ph;
    private String order;

    public restaurant() {
        this.name = name;
        this.add = add;
        this.ph = ph;
        this.order=order;
    }

    public String getName() {
        return name;
    }

    public String getAdd() {
        return add;
    }

    public String getPh() {
        return ph;
    }

    public String getOrder() {
        return order;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
