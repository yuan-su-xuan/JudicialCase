package com.yuansu.judicialcase.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.alibaba.fastjson.JSONObject;
import com.yuansu.judicialcase.config.CommonConfig;
import com.yuansu.judicialcase.dao.JudicialCaseES;
import com.yuansu.judicialcase.service.SearchService;
import com.yuansu.judicialcase.util.RespBean;
import com.yuansu.judicialcase.util.RespBeanEnum;
import com.yuansu.judicialcase.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

@Component
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ElasticsearchClient client;

    @Override
    public RespBean getJudicialCaseBySearch(SearchVo searchVo) {
        List<JudicialCaseES> list = new LinkedList<>();
        try {
            int page = searchVo.getPage();
            int size = searchVo.getSize();
            SearchResponse<JudicialCaseES> response8 = client.search(s -> s.index(CommonConfig.ELASTIC_SEARCH_INDEX)
                            .query(q -> q.fuzzy(f -> f.field(searchVo.getSearchWay()).value(searchVo.getKeyword())
                                    .fuzziness("0"))).from((page-1)*size).size(size), JudicialCaseES.class);
            response8.hits().hits().forEach(e -> {
 /*               String str = e.source().toString();
                JSONObject jsonObject = JSONObject.parseObject(str);
                JudicialCaseES judicialCaseES = new JudicialCaseES(jsonObject);*/
                list.add(e.source());
            });
            return RespBean.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return RespBean.error(RespBeanEnum.ERROR);
        }
    }
}
