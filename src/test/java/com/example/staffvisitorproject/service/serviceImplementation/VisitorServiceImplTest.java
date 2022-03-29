package com.example.staffvisitorproject.service.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.staffvisitorproject.dto.VisitLogDTO;
import com.example.staffvisitorproject.dto.VisitorRegDTO;
import com.example.staffvisitorproject.model.Staff;
import com.example.staffvisitorproject.model.VisitLog;
import com.example.staffvisitorproject.model.Visitor;
import com.example.staffvisitorproject.repository.StaffRepository;
import com.example.staffvisitorproject.repository.VisitLogRepository;
import com.example.staffvisitorproject.repository.VisitorRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {VisitorServiceImpl.class})
@ExtendWith(SpringExtension.class)
class VisitorServiceImplTest {
    @MockBean
    private StaffRepository staffRepository;

    @MockBean
    private VisitLogRepository visitLogRepository;

    @MockBean
    private VisitorRepository visitorRepository;

    @Autowired
    private VisitorServiceImpl visitorServiceImpl;

    @Test
    void testSaveVisitor() {
        Visitor visitor = new Visitor();
        visitor.setEmailAddress("42 Main St");
        visitor.setFullName("Dr Jane Doe");
        visitor.setHomeAddress("42 Main St");
        visitor.setId(123L);
        visitor.setPhoneNumber("4105551212");

        Visitor visitor1 = new Visitor();
        visitor1.setEmailAddress("42 Main St");
        visitor1.setFullName("Dr Jane Doe");
        visitor1.setHomeAddress("42 Main St");
        visitor1.setId(123L);
        visitor1.setPhoneNumber("4105551212");
        Optional<Visitor> ofResult = Optional.of(visitor1);
        when(this.visitorRepository.save((Visitor) any())).thenReturn(visitor);
        when(this.visitorRepository.findByEmailAddress((String) any())).thenReturn(ofResult);

        VisitorRegDTO visitorRegDTO = new VisitorRegDTO();
        visitorRegDTO.setAddress("42 Main St");
        visitorRegDTO.setEmail("jane.doe@example.org");
        visitorRegDTO.setFullName("Dr Jane Doe");
        visitorRegDTO.setPhoneNumber("4105551212");
        assertNull(this.visitorServiceImpl.saveVisitor(visitorRegDTO));
        verify(this.visitorRepository).findByEmailAddress((String) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testSaveVisitor2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.staffvisitorproject.service.serviceImplementation.VisitorServiceImpl.saveVisitor(VisitorServiceImpl.java:32)
        //   In order to prevent saveVisitor(VisitorRegDTO)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveVisitor(VisitorRegDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        Visitor visitor = new Visitor();
        visitor.setEmailAddress("42 Main St");
        visitor.setFullName("Dr Jane Doe");
        visitor.setHomeAddress("42 Main St");
        visitor.setId(123L);
        visitor.setPhoneNumber("4105551212");
        when(this.visitorRepository.save((Visitor) any())).thenReturn(visitor);
        when(this.visitorRepository.findByEmailAddress((String) any())).thenReturn(null);

        VisitorRegDTO visitorRegDTO = new VisitorRegDTO();
        visitorRegDTO.setAddress("42 Main St");
        visitorRegDTO.setEmail("jane.doe@example.org");
        visitorRegDTO.setFullName("Dr Jane Doe");
        visitorRegDTO.setPhoneNumber("4105551212");
        this.visitorServiceImpl.saveVisitor(visitorRegDTO);
    }

    @Test
    void testSaveVisitor3() {
        Visitor visitor = new Visitor();
        visitor.setEmailAddress("42 Main St");
        visitor.setFullName("Dr Jane Doe");
        visitor.setHomeAddress("42 Main St");
        visitor.setId(123L);
        visitor.setPhoneNumber("4105551212");
        when(this.visitorRepository.save((Visitor) any())).thenReturn(visitor);
        when(this.visitorRepository.findByEmailAddress((String) any())).thenReturn(Optional.empty());

        VisitorRegDTO visitorRegDTO = new VisitorRegDTO();
        visitorRegDTO.setAddress("42 Main St");
        visitorRegDTO.setEmail("jane.doe@example.org");
        visitorRegDTO.setFullName("Dr Jane Doe");
        visitorRegDTO.setPhoneNumber("4105551212");
        assertSame(visitor, this.visitorServiceImpl.saveVisitor(visitorRegDTO));
        verify(this.visitorRepository).save((Visitor) any());
        verify(this.visitorRepository).findByEmailAddress((String) any());
    }

    @Test
    void testGetVisitors() {
        ArrayList<Visitor> visitorList = new ArrayList<>();
        when(this.visitorRepository.findAll()).thenReturn(visitorList);
        List<Visitor> actualVisitors = this.visitorServiceImpl.getVisitors();
        assertSame(visitorList, actualVisitors);
        assertTrue(actualVisitors.isEmpty());
        verify(this.visitorRepository).findAll();
    }

    @Test
    void testGetVisitor() {
        Visitor visitor = new Visitor();
        visitor.setEmailAddress("42 Main St");
        visitor.setFullName("Dr Jane Doe");
        visitor.setHomeAddress("42 Main St");
        visitor.setId(123L);
        visitor.setPhoneNumber("4105551212");
        Optional<Visitor> ofResult = Optional.of(visitor);
        when(this.visitorRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(visitor, this.visitorServiceImpl.getVisitor(123L));
        verify(this.visitorRepository).findById((Long) any());
    }

    @Test
    void testAddVisit() {
        Visitor visitor = new Visitor();
        visitor.setEmailAddress("42 Main St");
        visitor.setFullName("Dr Jane Doe");
        visitor.setHomeAddress("42 Main St");
        visitor.setId(123L);
        visitor.setPhoneNumber("4105551212");
        Optional<Visitor> ofResult = Optional.of(visitor);
        when(this.visitorRepository.findById((Long) any())).thenReturn(ofResult);

        Staff staff = new Staff();
        staff.setEmailAddress("42 Main St");
        staff.setFullName("Dr Jane Doe");
        staff.setHomeAddress("42 Main St");
        staff.setId(123L);
        staff.setPassword("iloveyou");
        staff.setPhoneNumber("4105551212");
        staff.setUsername("janedoe");

        Visitor visitor1 = new Visitor();
        visitor1.setEmailAddress("42 Main St");
        visitor1.setFullName("Dr Jane Doe");
        visitor1.setHomeAddress("42 Main St");
        visitor1.setId(123L);
        visitor1.setPhoneNumber("4105551212");

        VisitLog visitLog = new VisitLog();
        visitLog.setDateOfVisit("2020-03-01");
        visitLog.setId(123L);
        visitLog.setReason("Just cause");
        visitLog.setStaff(staff);
        visitLog.setVisitor(visitor1);
        when(this.visitLogRepository.save((VisitLog) any())).thenReturn(visitLog);

        Staff staff1 = new Staff();
        staff1.setEmailAddress("42 Main St");
        staff1.setFullName("Dr Jane Doe");
        staff1.setHomeAddress("42 Main St");
        staff1.setId(123L);
        staff1.setPassword("iloveyou");
        staff1.setPhoneNumber("4105551212");
        staff1.setUsername("janedoe");
        Optional<Staff> ofResult1 = Optional.of(staff1);
        when(this.staffRepository.findById((Long) any())).thenReturn(ofResult1);

        VisitLogDTO visitLogDTO = new VisitLogDTO();
        visitLogDTO.setReason("Just cause");
        visitLogDTO.setStaffId(123L);
        visitLogDTO.setVisitorId(123L);
        assertSame(visitLog, this.visitorServiceImpl.addVisit(visitLogDTO));
        verify(this.visitorRepository).findById((Long) any());
        verify(this.visitLogRepository).save((VisitLog) any());
        verify(this.staffRepository).findById((Long) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddVisit2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.staffvisitorproject.service.serviceImplementation.VisitorServiceImpl.addVisit(VisitorServiceImpl.java:58)
        //   In order to prevent addVisit(VisitLogDTO)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addVisit(VisitLogDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.visitorRepository.findById((Long) any())).thenReturn(null);

        Staff staff = new Staff();
        staff.setEmailAddress("42 Main St");
        staff.setFullName("Dr Jane Doe");
        staff.setHomeAddress("42 Main St");
        staff.setId(123L);
        staff.setPassword("iloveyou");
        staff.setPhoneNumber("4105551212");
        staff.setUsername("janedoe");

        Visitor visitor = new Visitor();
        visitor.setEmailAddress("42 Main St");
        visitor.setFullName("Dr Jane Doe");
        visitor.setHomeAddress("42 Main St");
        visitor.setId(123L);
        visitor.setPhoneNumber("4105551212");

        VisitLog visitLog = new VisitLog();
        visitLog.setDateOfVisit("2020-03-01");
        visitLog.setId(123L);
        visitLog.setReason("Just cause");
        visitLog.setStaff(staff);
        visitLog.setVisitor(visitor);
        when(this.visitLogRepository.save((VisitLog) any())).thenReturn(visitLog);

        Staff staff1 = new Staff();
        staff1.setEmailAddress("42 Main St");
        staff1.setFullName("Dr Jane Doe");
        staff1.setHomeAddress("42 Main St");
        staff1.setId(123L);
        staff1.setPassword("iloveyou");
        staff1.setPhoneNumber("4105551212");
        staff1.setUsername("janedoe");
        Optional<Staff> ofResult = Optional.of(staff1);
        when(this.staffRepository.findById((Long) any())).thenReturn(ofResult);

        VisitLogDTO visitLogDTO = new VisitLogDTO();
        visitLogDTO.setReason("Just cause");
        visitLogDTO.setStaffId(123L);
        visitLogDTO.setVisitorId(123L);
        this.visitorServiceImpl.addVisit(visitLogDTO);
    }

    @Test
    void testAddVisit3() {
        when(this.visitorRepository.findById((Long) any())).thenReturn(Optional.empty());

        Staff staff = new Staff();
        staff.setEmailAddress("42 Main St");
        staff.setFullName("Dr Jane Doe");
        staff.setHomeAddress("42 Main St");
        staff.setId(123L);
        staff.setPassword("iloveyou");
        staff.setPhoneNumber("4105551212");
        staff.setUsername("janedoe");

        Visitor visitor = new Visitor();
        visitor.setEmailAddress("42 Main St");
        visitor.setFullName("Dr Jane Doe");
        visitor.setHomeAddress("42 Main St");
        visitor.setId(123L);
        visitor.setPhoneNumber("4105551212");

        VisitLog visitLog = new VisitLog();
        visitLog.setDateOfVisit("2020-03-01");
        visitLog.setId(123L);
        visitLog.setReason("Just cause");
        visitLog.setStaff(staff);
        visitLog.setVisitor(visitor);
        when(this.visitLogRepository.save((VisitLog) any())).thenReturn(visitLog);

        Staff staff1 = new Staff();
        staff1.setEmailAddress("42 Main St");
        staff1.setFullName("Dr Jane Doe");
        staff1.setHomeAddress("42 Main St");
        staff1.setId(123L);
        staff1.setPassword("iloveyou");
        staff1.setPhoneNumber("4105551212");
        staff1.setUsername("janedoe");
        Optional<Staff> ofResult = Optional.of(staff1);
        when(this.staffRepository.findById((Long) any())).thenReturn(ofResult);

        VisitLogDTO visitLogDTO = new VisitLogDTO();
        visitLogDTO.setReason("Just cause");
        visitLogDTO.setStaffId(123L);
        visitLogDTO.setVisitorId(123L);
        assertNull(this.visitorServiceImpl.addVisit(visitLogDTO));
        verify(this.visitorRepository).findById((Long) any());
        verify(this.staffRepository).findById((Long) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddVisit4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.staffvisitorproject.service.serviceImplementation.VisitorServiceImpl.addVisit(VisitorServiceImpl.java:57)
        //   In order to prevent addVisit(VisitLogDTO)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addVisit(VisitLogDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        Visitor visitor = new Visitor();
        visitor.setEmailAddress("42 Main St");
        visitor.setFullName("Dr Jane Doe");
        visitor.setHomeAddress("42 Main St");
        visitor.setId(123L);
        visitor.setPhoneNumber("4105551212");
        Optional<Visitor> ofResult = Optional.of(visitor);
        when(this.visitorRepository.findById((Long) any())).thenReturn(ofResult);

        Staff staff = new Staff();
        staff.setEmailAddress("42 Main St");
        staff.setFullName("Dr Jane Doe");
        staff.setHomeAddress("42 Main St");
        staff.setId(123L);
        staff.setPassword("iloveyou");
        staff.setPhoneNumber("4105551212");
        staff.setUsername("janedoe");

        Visitor visitor1 = new Visitor();
        visitor1.setEmailAddress("42 Main St");
        visitor1.setFullName("Dr Jane Doe");
        visitor1.setHomeAddress("42 Main St");
        visitor1.setId(123L);
        visitor1.setPhoneNumber("4105551212");

        VisitLog visitLog = new VisitLog();
        visitLog.setDateOfVisit("2020-03-01");
        visitLog.setId(123L);
        visitLog.setReason("Just cause");
        visitLog.setStaff(staff);
        visitLog.setVisitor(visitor1);
        when(this.visitLogRepository.save((VisitLog) any())).thenReturn(visitLog);
        when(this.staffRepository.findById((Long) any())).thenReturn(null);

        VisitLogDTO visitLogDTO = new VisitLogDTO();
        visitLogDTO.setReason("Just cause");
        visitLogDTO.setStaffId(123L);
        visitLogDTO.setVisitorId(123L);
        this.visitorServiceImpl.addVisit(visitLogDTO);
    }

    @Test
    void testAddVisit5() {
        Visitor visitor = new Visitor();
        visitor.setEmailAddress("42 Main St");
        visitor.setFullName("Dr Jane Doe");
        visitor.setHomeAddress("42 Main St");
        visitor.setId(123L);
        visitor.setPhoneNumber("4105551212");
        Optional<Visitor> ofResult = Optional.of(visitor);
        when(this.visitorRepository.findById((Long) any())).thenReturn(ofResult);

        Staff staff = new Staff();
        staff.setEmailAddress("42 Main St");
        staff.setFullName("Dr Jane Doe");
        staff.setHomeAddress("42 Main St");
        staff.setId(123L);
        staff.setPassword("iloveyou");
        staff.setPhoneNumber("4105551212");
        staff.setUsername("janedoe");

        Visitor visitor1 = new Visitor();
        visitor1.setEmailAddress("42 Main St");
        visitor1.setFullName("Dr Jane Doe");
        visitor1.setHomeAddress("42 Main St");
        visitor1.setId(123L);
        visitor1.setPhoneNumber("4105551212");

        VisitLog visitLog = new VisitLog();
        visitLog.setDateOfVisit("2020-03-01");
        visitLog.setId(123L);
        visitLog.setReason("Just cause");
        visitLog.setStaff(staff);
        visitLog.setVisitor(visitor1);
        when(this.visitLogRepository.save((VisitLog) any())).thenReturn(visitLog);
        when(this.staffRepository.findById((Long) any())).thenReturn(Optional.empty());

        VisitLogDTO visitLogDTO = new VisitLogDTO();
        visitLogDTO.setReason("Just cause");
        visitLogDTO.setStaffId(123L);
        visitLogDTO.setVisitorId(123L);
        assertNull(this.visitorServiceImpl.addVisit(visitLogDTO));
        verify(this.visitorRepository).findById((Long) any());
        verify(this.staffRepository).findById((Long) any());
    }
}

