package com.yuansu.judicialcase.service.impl;

import com.yuansu.judicialcase.dao.Candidate;
import com.yuansu.judicialcase.service.CandidateService;
import com.yuansu.judicialcase.util.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        //TODO: 调用 相似度像算法接口获得相似度最高n个的pid List
        //这里先mock一下
        List<Integer> pidList = List.of(2726895,819051,1381368,3501816,4011147);

        return mongoUtil.queryByPidList(pidList);
    }

}
