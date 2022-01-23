package com.rita.project.projectbilling.service;
import com.rita.project.projectbilling.dto.BillDto;

import java.util.List;

public interface BillService {

     BillDto createBill(BillDto dto);

     BillDto updateBill (BillDto dto);

     boolean PaidBill(Long id);

     List<BillDto>findByUnpaidBill(String treatment);
}