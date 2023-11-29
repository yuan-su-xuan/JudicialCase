package com.yuansu.judicialcase.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchVo {
    String keyword;
    String searchWay;
    Integer page;
    Integer size;
}
