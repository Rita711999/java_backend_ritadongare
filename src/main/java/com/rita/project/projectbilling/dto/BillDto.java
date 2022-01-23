package com.rita.project.projectbilling.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@Setter
@Getter
public class BillDto {
    private Long id;
    private String patientName;
    private Date billDate;
    private String treatment;
    private boolean billStatus;
    private Integer billAmount;
    private Date paidDate;

}
