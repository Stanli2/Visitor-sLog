package com.example.staffvisitorproject.service;

import com.example.staffvisitorproject.dto.StaffRegDTO;
import com.example.staffvisitorproject.model.Staff;
import com.example.staffvisitorproject.dto.LoginRequest;

import java.util.List;

public interface StaffService {
    String loginUser(LoginRequest request);
    Staff getStaff(Long id);
    List<Staff> getStaffList();
    Staff addStaff(StaffRegDTO staffDetails);
}
