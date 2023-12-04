package com.yuansu.judicialcase.service.impl;

import com.yuansu.judicialcase.dao.Candidate;
import com.yuansu.judicialcase.dao.Similarity;
import com.yuansu.judicialcase.service.CandidateService;
import com.yuansu.judicialcase.util.MongoUtil;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    MongoUtil mongoUtil;

    @Override
    public Candidate queryByPid(Integer pid) {
        return mongoUtil.queryByPid(pid);
    }

    @Override
    public List<Candidate> getSimilarCandidatesByPaid(Integer pid) {

        Map<String, Object> param = new LinkedHashMap<>();
        param.put("pid", pid);
        param.put("n", 30);
//        RestClient.solve(param);
        Similarity similarity = mongoUtil.querySimilarityByPid(pid);

        //这里先mock一下
        List<Integer> pidList = new ArrayList<>();

        for (Map.Entry<String, Double> entry : similarity.getSimilarity_ls().entrySet()) {
            pidList.add(Integer.valueOf(entry.getKey()));
            if (pidList.size() >= 10) {
                break;
            }
        }

        return mongoUtil.queryByPidList(pidList);
    }

}

class RestClient {

    public static void solve(Map<String, Object> requestParams) {
        // 创建 RestTemplate 实例
        RestTemplate restTemplate = new RestTemplate();

        // 设置请求的 URL
        String apiUrl = "http://127.0.0.1:5000/BM25";

        // 发送 GET 请求，并接收响应
        String response = restTemplate.postForObject(apiUrl, requestParams, String.class);

        // 处理响应
        System.out.println("Response: " + response);
    }
}
