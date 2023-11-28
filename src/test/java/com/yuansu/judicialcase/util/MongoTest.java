package com.yuansu.judicialcase.util;

import com.yuansu.judicialcase.dao.Candidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootTest
public class MongoTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void find(){
        Query query = new Query();

        // pid = 1
        query.addCriteria(Criteria.where("pid").is(1984205));

        List<Candidate> candidates = mongoTemplate.find(query, Candidate.class);
        // print
        candidates.forEach(System.out::println);
    }

    @Test
    void find50(){
        Query query = new Query();

        // 全部的前50条
        query.limit(50);

        List<Candidate> candidates = mongoTemplate.find(query, Candidate.class);
        // print
        candidates.forEach(System.out::println);
    }
}
