package com.yuansu.judicialcase.controller;

import com.yuansu.judicialcase.service.CandidateService;
import com.yuansu.judicialcase.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @GetMapping("/detail/{pid}")
    public RespBean getCandidateDetail(@PathVariable Integer pid){
        return RespBean.success(candidateService.queryByPid(pid));
    }

    @GetMapping("/similar/{pid}")
    public RespBean getSimilarCandidates(@PathVariable Integer pid){
        return RespBean.success(candidateService.getSimilarCandidatesByPaid(pid));
    }
}
