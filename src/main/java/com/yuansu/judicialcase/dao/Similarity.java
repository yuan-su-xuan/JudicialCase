package com.yuansu.judicialcase.dao;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "similarity")
public class Similarity {
    Integer pid;
    Map<String, Double> similarity_ls;
}
