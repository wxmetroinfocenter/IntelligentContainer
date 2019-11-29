package com.wxmetro.ic.modules.icmgmt.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public abstract class ApiBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
