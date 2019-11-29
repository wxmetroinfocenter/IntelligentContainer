package com.wxmetro.ic.modules.icmgmt.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
public class KeepAliveEntity extends ApiBaseEntity {

    private String serialNo;
    private String token;

    private Map<String ,String> boxes = new HashMap<String,String>();


    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getBoxes() {
        return boxes;
    }

    public void setBoxes(Map<String, String> boxes) {
        this.boxes = boxes;
    }
}
