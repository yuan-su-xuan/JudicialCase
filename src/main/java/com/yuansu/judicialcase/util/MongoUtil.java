package com.yuansu.judicialcase.util;

import com.yuansu.judicialcase.dao.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoUtil {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Candidate queryByPid(Integer pid){
        Query query = new Query();
        query.addCriteria(Criteria.where("pid").is(pid));

        return mongoTemplate.findOne(query, Candidate.class);
    }

    public List<Candidate> queryByPidList(List<Integer> pidList){
        Query query = new Query();
        query.addCriteria(Criteria.where("pid").in(pidList));
        return mongoTemplate.find(query, Candidate.class);
    }

    public List<Candidate> queryByReason(String reason, int page, int size) {
        Query query = new Query();
        query.addCriteria(Criteria.where("reason").regex(reason, "i"));

        Pageable pageable = PageRequest.of(page, size);
        query.with(pageable);

        return mongoTemplate.find(query, Candidate.class);
    }

    public List<Candidate> queryByResult(String result, int page, int size) {
        Query query = new Query();
        query.addCriteria(Criteria.where("reason").regex(result, "i"));

        Pageable pageable = PageRequest.of(page, size);
        query.with(pageable);

        return mongoTemplate.find(query, Candidate.class);
    }

    public List<Candidate> queryByCharge(String charge, int page, int size) {
        Query query = new Query();
        query.addCriteria(Criteria.where("reason").regex(charge, "i"));

        Pageable pageable = PageRequest.of(page, size);
        query.with(pageable);

        return mongoTemplate.find(query, Candidate.class);
    }
}

