package com.example.staffvisitorproject.service.serviceImplementation;

import com.example.staffvisitorproject.dto.LoginRequest;
import com.example.staffvisitorproject.dto.StaffRegDTO;
import com.example.staffvisitorproject.model.Staff;
import com.example.staffvisitorproject.repository.StaffRepository;
import com.example.staffvisitorproject.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public String loginUser(LoginRequest request) {
        final Staff validUser = staffRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword()).orElseThrow(
                () -> new IllegalArgumentException("Incorrect username or password")
        );
        return (validUser != null) ? "Logged in successfully" : "Incorrect login details";
    }

    @Override
    public Staff getStaff(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    @Override
    public List<Staff> getStaffList() {
        return staffRepository.findAll();
    }

    @Override
    public Staff addStaff(StaffRegDTO staffDetails) {
        final Staff staff = staffRepository.findByEmailAddress(staffDetails.getEmail()).orElse(null);

        if (staff == null) {
            Staff newStaff = new Staff();
            newStaff.setFullName(staffDetails.getName());
            newStaff.setEmailAddress(staffDetails.getEmail());
            newStaff.setHomeAddress(staffDetails.getAddress());
            newStaff.setPhoneNumber(staffDetails.getPhoneNumber());
            newStaff.setUsername(staffDetails.getUsername());
            newStaff.setPassword(staffDetails.getPassword());

            return staffRepository.save(newStaff);
        }
        return null;
    }
}
