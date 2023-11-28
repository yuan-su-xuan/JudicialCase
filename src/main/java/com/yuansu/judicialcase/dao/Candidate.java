package com.yuansu.judicialcase.dao;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "file")
public class Candidate {
    Integer pid;
    String qw;
    String fact;
    String reason;
    String result;
    List<String> charge;
    List<Integer> article;
}
