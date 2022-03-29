package com.example.staffvisitorproject.service;

import com.example.staffvisitorproject.dto.VisitLogDTO;
import com.example.staffvisitorproject.dto.VisitorRegDTO;
import com.example.staffvisitorproject.model.VisitLog;
import com.example.staffvisitorproject.model.Visitor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VisitorService {
    Visitor saveVisitor(VisitorRegDTO request);
    List<Visitor> getVisitors();
    Visitor getVisitor(Long id);
    VisitLog addVisit(VisitLogDTO request);
}
