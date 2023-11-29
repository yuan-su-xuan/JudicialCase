package com.yuansu.judicialcase.dao;


import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudicialCaseES {
    Integer pid;
    String fact;
    String reason;
    String result;
    List<String> charge;
    List<Integer> article;

    public JudicialCaseES(JSONObject jsonObject){
        pid = jsonObject.getInteger("pid");
        fact = jsonObject.getString("fact");
        reason = jsonObject.getString("reason");
        result = jsonObject.getString("result");
        String chargeString = jsonObject.getString("charge");
        if(chargeString.startsWith("[")){
            charge = List.of(jsonObject.getJSONArray("charge").toArray(new String[0]));
        }else{
            charge = List.of(new String[]{jsonObject.getString("charge")});
        }

        String articleString = jsonObject.getString("article");
        if(articleString.startsWith("[")){
            article = List.of(jsonObject.getJSONArray("article").toArray(new Integer[0]));
        }else{
            article = List.of(new Integer[]{jsonObject.getInteger("article")});
        }

    }
}
