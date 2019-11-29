package com.wxmetro.ic.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxmetro.ic.modules.icmgmt.entity.KeepAliveEntity;

import java.util.HashMap;

public class JsonTest {

    public static void main(String[] args) {
        KeepAliveEntity entity = new KeepAliveEntity();

        entity.setSerialNo("test");
        entity.setToken("otk");
        HashMap<String, String > map = new HashMap<String, String>();
        map.put("1","0");
        map.put("2","0");
        map.put("3","0");
        map.put("4","0");

        entity.setBoxes(map);


        ObjectMapper mapper = new ObjectMapper();

        String text = null;
        try {
            text = mapper.writeValueAsString(map);
            System.out.println(text);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }


}
