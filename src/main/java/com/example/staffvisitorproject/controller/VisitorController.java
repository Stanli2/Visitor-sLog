package com.example.staffvisitorproject.controller;

import com.example.staffvisitorproject.dto.VisitLogDTO;
import com.example.staffvisitorproject.dto.VisitorRegDTO;
import com.example.staffvisitorproject.model.VisitLog;
import com.example.staffvisitorproject.model.Visitor;
import com.example.staffvisitorproject.service.VisitorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api")
@AllArgsConstructor
public class VisitorController {
    private final VisitorService visitorService;

    @PostMapping("/visitor")
    public ResponseEntity<Visitor> addVisitor(@RequestBody VisitorRegDTO request) {
        return ResponseEntity.ok().body(visitorService.saveVisitor(request));
    }

    @GetMapping("/visitors")
    public ResponseEntity<List<Visitor>> getVisitors() {
        return ResponseEntity.ok().body(visitorService.getVisitors());
    }

    @GetMapping("/visitor/{id}")
    public ResponseEntity<Visitor> getVisitor(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(visitorService.getVisitor(id));
    }

    @PostMapping("/visit")
    public ResponseEntity<VisitLog> logVisit(@RequestBody VisitLogDTO request) {
        return ResponseEntity.ok().body(visitorService.addVisit(request));
    }
}
