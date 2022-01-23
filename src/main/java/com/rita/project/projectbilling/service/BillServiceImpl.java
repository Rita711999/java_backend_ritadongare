package com.rita.project.projectbilling.service;

import com.rita.project.projectbilling.domain.Bill;
import com.rita.project.projectbilling.dto.BillDto;
import com.rita.project.projectbilling.exception.InvalidException;
import com.rita.project.projectbilling.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.sql.SQLException;
import java.util.Optional;

@Transactional(
       // isolation = Isolation.READ_UNCOMMITTED,
        rollbackFor={SQLException.class},
        noRollbackFor = InvalidException.class
)

@Service
public class BillServiceImpl implements  BillService{
    @Autowired
    private BillRepository repository;

    @Override
    public BillDto createBill(BillDto dto) {

        var bill = new Bill();
        bill.setBillDate(dto.getBillDate());
        bill.setBilAmount(dto.getBillAmount());
        bill.setBillStatus(bill.getBillStatus());
        bill.setPaidDate(dto.getPaidDate());
        bill.setTreatment(dto.getTreatment());
        bill.setPatientName(dto.getPatientName());
        repository.save(bill);

        return dto;
    }

    @Override
    public BillDto updateBill(BillDto dto) throws InvalidException {
        Bill bill1 = repository.findById(dto.getId()).orElseThrow(() -> new InvalidException("ID is not valid"));
   var bill = new Bill();

        bill.setBillDate(dto.getBillDate());
        bill.setBilAmount(dto.getBillAmount());
        bill.setBillStatus(bill.getBillStatus());
        bill.setPaidDate(dto.getPaidDate());
        bill.setTreatment(dto.getTreatment());
        bill.setPatientName(dto.getPatientName());
        bill.setId(dto.getId());

        repository.save(bill);

        return  dto;
    }
    @Override

    public boolean PaidBill(Long id) throws InvalidException{
        Optional<Bill> ba = repository.findById(id);
        Bill oldBill = ba.orElseThrow(() -> new InvalidException("ID is not valid"));
        boolean newStatus = true;
        Bill bill = new Bill();

        bill.setBillDate(oldBill.getBillDate());
        bill.setBilAmount(oldBill.getBilAmount());
        bill.setBillStatus(newStatus);
        bill.setPaidDate(oldBill.getPaidDate());
        bill.setTreatment(oldBill.getTreatment());
        bill.setPatientName(oldBill.getPatientName());
        bill.setId(oldBill.getId());

        repository.save(bill);

        return bill.getBillStatus();
    }

    @Override
    public List<BillDto> findByUnpaidBill(String treatment) {
        return null;
    }
}
