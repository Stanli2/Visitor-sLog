package com.example.staffvisitorproject.service.serviceImplementation;

import com.example.staffvisitorproject.dto.VisitLogDTO;
import com.example.staffvisitorproject.dto.VisitorRegDTO;
import com.example.staffvisitorproject.model.Staff;
import com.example.staffvisitorproject.model.VisitLog;
import com.example.staffvisitorproject.model.Visitor;
import com.example.staffvisitorproject.repository.StaffRepository;
import com.example.staffvisitorproject.repository.VisitLogRepository;
import com.example.staffvisitorproject.repository.VisitorRepository;
import com.example.staffvisitorproject.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {
    private final VisitorRepository visitorRepository;
    private final StaffRepository staffRepository;
    private final VisitLogRepository visitLogRepository;

    @Autowired
    public VisitorServiceImpl(VisitorRepository visitorRepository, StaffRepository staffRepository, VisitLogRepository visitLogRepository) {
        this.visitorRepository = visitorRepository;
        this.staffRepository = staffRepository;
        this.visitLogRepository = visitLogRepository;
    }

    @Override
    public Visitor saveVisitor(VisitorRegDTO request) {
        final Visitor visitor = visitorRepository.findByEmailAddress(request.getEmail()).orElse(null);
        if (visitor == null) {
            Visitor newVisitor = new Visitor();
            newVisitor.setFullName(request.getFullName());
            newVisitor.setEmailAddress(request.getEmail());
            newVisitor.setHomeAddress(request.getAddress());
            newVisitor.setPhoneNumber(request.getPhoneNumber());

            return visitorRepository.save(newVisitor);
        }
        return null;
    }

    @Override
    public List<Visitor> getVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public Visitor getVisitor(Long id) {
        return visitorRepository.findById(id).orElse(null);
    }

    @Override
    public VisitLog addVisit(VisitLogDTO request) {
        final Staff staff = staffRepository.findById(request.getStaffId()).orElse(null);
        final Visitor visitor = visitorRepository.findById(request.getVisitorId()).orElse(null);

        if (staff != null && visitor != null) {
            VisitLog log = new VisitLog();
            log.setVisitor(visitor);
            log.setStaff(staff);
            log.setReason(request.getReason());

            return visitLogRepository.save(log);
        }
        return null;
    }
}
