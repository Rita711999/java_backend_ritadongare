package com.rita.project.projectbilling.domain;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String patientName;

    @Column(unique = false, nullable = true)
    private Date billDate;


    @Column(unique = true, nullable = false)
    private String treatment;

    @Column(unique = false, nullable = true)
    private Date paidDate;

    @Column(unique = false, nullable = true)
    private Boolean billStatus;

    @Column(unique = false, nullable = false)
    private Integer bilAmount;
}
