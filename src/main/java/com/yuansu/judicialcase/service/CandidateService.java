package com.yuansu.judicialcase.service;

import com.yuansu.judicialcase.dao.Candidate;

import java.util.List;
public interface CandidateService {
    Candidate queryByPid(Integer pid);

    List<Candidate> getSimilarCandidatesByPaid(Integer pid);
}
