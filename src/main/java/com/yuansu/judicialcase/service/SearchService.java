package com.yuansu.judicialcase.service;

import com.yuansu.judicialcase.dao.JudicialCaseES;
import com.yuansu.judicialcase.util.RespBean;
import com.yuansu.judicialcase.vo.SearchVo;

import java.util.List;

public interface SearchService {

    RespBean getJudicialCaseBySearch(SearchVo searchVo);

    RespBean getSearchByMongo(SearchVo searchVo);
}
