package com.example.staffvisitorproject.service.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.staffvisitorproject.dto.LoginRequest;
import com.example.staffvisitorproject.dto.StaffRegDTO;
import com.example.staffvisitorproject.model.Staff;
import com.example.staffvisitorproject.repository.StaffRepository;

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

@ContextConfiguration(classes = {StaffServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StaffServiceImplTest {
    @MockBean
    private StaffRepository staffRepository;

    @Autowired
    private StaffServiceImpl staffServiceImpl;

    @Test
    void testLoginUser() {
        Staff staff = new Staff();
        staff.setEmailAddress("42 Main St");
        staff.setFullName("Dr Jane Doe");
        staff.setHomeAddress("42 Main St");
        staff.setId(123L);
        staff.setPassword("iloveyou");
        staff.setPhoneNumber("4105551212");
        staff.setUsername("janedoe");
        Optional<Staff> ofResult = Optional.of(staff);
        when(this.staffRepository.findByUsernameAndPassword((String) any(), (String) any())).thenReturn(ofResult);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUsername("janedoe");
        assertEquals("Logged in successfully", this.staffServiceImpl.loginUser(loginRequest));
        verify(this.staffRepository).findByUsernameAndPassword((String) any(), (String) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testLoginUser2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.staffvisitorproject.service.serviceImplementation.StaffServiceImpl.loginUser(StaffServiceImpl.java:24)
        //   In order to prevent loginUser(LoginRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   loginUser(LoginRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.staffRepository.findByUsernameAndPassword((String) any(), (String) any())).thenReturn(null);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUsername("janedoe");
        this.staffServiceImpl.loginUser(loginRequest);
    }

    @Test
    void testLoginUser3() {
        when(this.staffRepository.findByUsernameAndPassword((String) any(), (String) any())).thenReturn(Optional.empty());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUsername("janedoe");
        assertThrows(IllegalArgumentException.class, () -> this.staffServiceImpl.loginUser(loginRequest));
        verify(this.staffRepository).findByUsernameAndPassword((String) any(), (String) any());
    }

    @Test
    void testLoginUser4() {
        when(this.staffRepository.findByUsernameAndPassword((String) any(), (String) any()))
                .thenThrow(new IllegalArgumentException("Incorrect username or password"));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUsername("janedoe");
        assertThrows(IllegalArgumentException.class, () -> this.staffServiceImpl.loginUser(loginRequest));
        verify(this.staffRepository).findByUsernameAndPassword((String) any(), (String) any());
    }

    @Test
    void testGetStaff() {
        Staff staff = new Staff();
        staff.setEmailAddress("42 Main St");
        staff.setFullName("Dr Jane Doe");
        staff.setHomeAddress("42 Main St");
        staff.setId(123L);
        staff.setPassword("iloveyou");
        staff.setPhoneNumber("4105551212");
        staff.setUsername("janedoe");
        Optional<Staff> ofResult = Optional.of(staff);
        when(this.staffRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(staff, this.staffServiceImpl.getStaff(123L));
        verify(this.staffRepository).findById((Long) any());
    }

    @Test
    void testGetStaff2() {
        when(this.staffRepository.findById((Long) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.staffServiceImpl.getStaff(123L));
        verify(this.staffRepository).findById((Long) any());
    }

    @Test
    void testGetStaffList() {
        ArrayList<Staff> staffList = new ArrayList<>();
        when(this.staffRepository.findAll()).thenReturn(staffList);
        List<Staff> actualStaffList = this.staffServiceImpl.getStaffList();
        assertSame(staffList, actualStaffList);
        assertTrue(actualStaffList.isEmpty());
        verify(this.staffRepository).findAll();
    }

    @Test
    void testGetStaffList2() {
        when(this.staffRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.staffServiceImpl.getStaffList());
        verify(this.staffRepository).findAll();
    }

    @Test
    void testAddStaff() {
        Staff staff = new Staff();
        staff.setEmailAddress("42 Main St");
        staff.setFullName("Dr Jane Doe");
        staff.setHomeAddress("42 Main St");
        staff.setId(123L);
        staff.setPassword("iloveyou");
        staff.setPhoneNumber("4105551212");
        staff.setUsername("janedoe");

        Staff staff1 = new Staff();
        staff1.setEmailAddress("42 Main St");
        staff1.setFullName("Dr Jane Doe");
        staff1.setHomeAddress("42 Main St");
        staff1.setId(123L);
        staff1.setPassword("iloveyou");
        staff1.setPhoneNumber("4105551212");
        staff1.setUsername("janedoe");
        Optional<Staff> ofResult = Optional.of(staff1);
        when(this.staffRepository.save((Staff) any())).thenReturn(staff);
        when(this.staffRepository.findByEmailAddress((String) any())).thenReturn(ofResult);

        StaffRegDTO staffRegDTO = new StaffRegDTO();
        staffRegDTO.setAddress("42 Main St");
        staffRegDTO.setEmail("jane.doe@example.org");
        staffRegDTO.setName("Name");
        staffRegDTO.setPassword("iloveyou");
        staffRegDTO.setPhoneNumber("4105551212");
        staffRegDTO.setUsername("janedoe");
        assertNull(this.staffServiceImpl.addStaff(staffRegDTO));
        verify(this.staffRepository).findByEmailAddress((String) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAddStaff2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.staffvisitorproject.service.serviceImplementation.StaffServiceImpl.addStaff(StaffServiceImpl.java:42)
        //   In order to prevent addStaff(StaffRegDTO)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addStaff(StaffRegDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        Staff staff = new Staff();
        staff.setEmailAddress("42 Main St");
        staff.setFullName("Dr Jane Doe");
        staff.setHomeAddress("42 Main St");
        staff.setId(123L);
        staff.setPassword("iloveyou");
        staff.setPhoneNumber("4105551212");
        staff.setUsername("janedoe");
        when(this.staffRepository.save((Staff) any())).thenReturn(staff);
        when(this.staffRepository.findByEmailAddress((String) any())).thenReturn(null);

        StaffRegDTO staffRegDTO = new StaffRegDTO();
        staffRegDTO.setAddress("42 Main St");
        staffRegDTO.setEmail("jane.doe@example.org");
        staffRegDTO.setName("Name");
        staffRegDTO.setPassword("iloveyou");
        staffRegDTO.setPhoneNumber("4105551212");
        staffRegDTO.setUsername("janedoe");
        this.staffServiceImpl.addStaff(staffRegDTO);
    }

    @Test
    void testAddStaff3() {
        Staff staff = new Staff();
        staff.setEmailAddress("42 Main St");
        staff.setFullName("Dr Jane Doe");
        staff.setHomeAddress("42 Main St");
        staff.setId(123L);
        staff.setPassword("iloveyou");
        staff.setPhoneNumber("4105551212");
        staff.setUsername("janedoe");
        when(this.staffRepository.save((Staff) any())).thenReturn(staff);
        when(this.staffRepository.findByEmailAddress((String) any())).thenReturn(Optional.empty());

        StaffRegDTO staffRegDTO = new StaffRegDTO();
        staffRegDTO.setAddress("42 Main St");
        staffRegDTO.setEmail("jane.doe@example.org");
        staffRegDTO.setName("Name");
        staffRegDTO.setPassword("iloveyou");
        staffRegDTO.setPhoneNumber("4105551212");
        staffRegDTO.setUsername("janedoe");
        assertSame(staff, this.staffServiceImpl.addStaff(staffRegDTO));
        verify(this.staffRepository).save((Staff) any());
        verify(this.staffRepository).findByEmailAddress((String) any());
    }

    @Test
    void testAddStaff4() {
        when(this.staffRepository.save((Staff) any())).thenThrow(new IllegalArgumentException("foo"));
        when(this.staffRepository.findByEmailAddress((String) any())).thenThrow(new IllegalArgumentException("foo"));

        StaffRegDTO staffRegDTO = new StaffRegDTO();
        staffRegDTO.setAddress("42 Main St");
        staffRegDTO.setEmail("jane.doe@example.org");
        staffRegDTO.setName("Name");
        staffRegDTO.setPassword("iloveyou");
        staffRegDTO.setPhoneNumber("4105551212");
        staffRegDTO.setUsername("janedoe");
        assertThrows(IllegalArgumentException.class, () -> this.staffServiceImpl.addStaff(staffRegDTO));
        verify(this.staffRepository).findByEmailAddress((String) any());
    }
}

