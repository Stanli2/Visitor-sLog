package com.example.staffvisitorproject.dto;

import lombok.Data;

@Data
public class VisitLogDTO {
    private Long visitorId;
    private Long staffId;
    private String reason;
}
