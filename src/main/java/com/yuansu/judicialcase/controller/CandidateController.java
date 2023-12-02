package com.yuansu.judicialcase.controller;

import com.yuansu.judicialcase.service.CandidateService;
import com.yuansu.judicialcase.service.SearchService;
import com.yuansu.judicialcase.util.RespBean;
import com.yuansu.judicialcase.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    SearchService searchService;

    @GetMapping("/detail/{pid}")
    public RespBean getCandidateDetail(@PathVariable Integer pid){
        return RespBean.success(candidateService.queryByPid(pid));
    }

    @GetMapping("/similar/{pid}")
    public RespBean getSimilarCandidates(@PathVariable Integer pid){
        return RespBean.success(candidateService.getSimilarCandidatesByPaid(pid));
    }

    @RequestMapping("/search")
    public RespBean getSearchResult(@RequestBody Map data){
        String searchWay = (String) data.get("searchWay");
        Integer size = 10;
        if(data.get("size")!=null&& !data.get("size").toString().isEmpty()){
            size = (Integer) data.get("size");
        }
        Integer page = (Integer) data.get("page");
        String keyword = (String) data.get("keyword");
        SearchVo searchVo = new SearchVo(keyword,searchWay,page,size);
        return searchService.getJudicialCaseBySearch(searchVo);
    }
}
