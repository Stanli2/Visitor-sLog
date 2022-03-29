package com.example.staffvisitorproject.controller;

import com.example.staffvisitorproject.dto.LoginRequest;
import com.example.staffvisitorproject.dto.StaffRegDTO;
import com.example.staffvisitorproject.model.Staff;
import com.example.staffvisitorproject.service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @PostMapping("/staff")
    public ResponseEntity<Staff> addNewStaff(@RequestBody StaffRegDTO staffDetails) {
        return ResponseEntity.ok().body(staffService.addStaff(staffDetails));
    }

    @GetMapping("/staff")
    public ResponseEntity<List<Staff>> getStaffList() {
        return ResponseEntity.ok().body(staffService.getStaffList());
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<Staff> getStaff (@PathVariable(value="id") Long id) {
        return ResponseEntity.ok().body(staffService.getStaff(id));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(staffService.loginUser(request));
    }
}
