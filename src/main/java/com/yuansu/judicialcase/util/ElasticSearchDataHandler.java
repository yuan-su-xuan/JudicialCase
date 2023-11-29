package com.yuansu.judicialcase.util;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuansu.judicialcase.config.CommonConfig;
import com.yuansu.judicialcase.dao.JudicialCaseES;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.*;

public class ElasticSearchDataHandler {
    @Autowired
    private ElasticsearchClient client;

    static String dir = "D:\\desktop\\candidate\\candidate_55192";

    public ElasticSearchDataHandler(ElasticsearchClient client){
        this.client =client;
    }
    public void readDataFromFile() throws IOException {
        List<JudicialCaseES> judicialCaseESList = new LinkedList<>();
        File file = new File(dir);
        File[] files = file.listFiles();
        assert files != null;
        for (File f : files) {
            String s = readJsonFile(f);
            JSONObject jObj = JSON.parseObject(s);
            JudicialCaseES judicialCaseES = new JudicialCaseES(jObj);
            judicialCaseESList.add(judicialCaseES);
            if(judicialCaseESList.size()==100){
                bulkDocument(judicialCaseESList);
                System.out.println("添加完100个");
                judicialCaseESList = new LinkedList<>();
            }
        }

        bulkDocument(judicialCaseESList);
    }

    String readJsonFile(File jsonFile) {
        String jsonStr = "";
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    void bulkDocument(List<JudicialCaseES> list){
        try {
            List<BulkOperation> bulkOperationArrayList = new ArrayList<>();
            //遍历添加到bulk中
            for(JudicialCaseES judicialCaseES : list){
                bulkOperationArrayList.add(BulkOperation.of(o->o.index(i->i.document(judicialCaseES))));
            }
            BulkResponse bulkResponse = client.bulk(b -> b.index(CommonConfig.ELASTIC_SEARCH_INDEX)
                    .operations(bulkOperationArrayList));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
