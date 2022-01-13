package com.week2.casestudy.controller;
import com.week2.casestudy.domain.BankAccount;
import com.week2.casestudy.dto.AppResponce;
import com.week2.casestudy.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bank")
@RestController // = @Component
public class BankController {
    @Autowired
    private BankService service;

    @PostMapping // POST -> http://localhost:8080/bank/
    public ResponseEntity<AppResponce<Integer>> createBankAccount(@RequestBody BankAccount ba) {

        service.createNewAccount(ba);

        var response = new AppResponce<Integer>();
        response.setMsg("account created successfully");
        response.setSts("success");
        response.setBody(0);
        return ResponseEntity.ok(response);
    }

    @PutMapping // PUT -> http://localhost:8080/123456
    public ResponseEntity<AppResponce<Double>> withdrawMoney(@RequestBody BankAccount ba) {
        double amt = service.withdraw(ba.getAcNum(), ba.getBalance());
        var response = new AppResponce<Double>();
        response.setMsg("account created successfully");
        response.setSts("success");
        response.setBody(amt);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{prefix}")
    public ResponseEntity<AppResponce<List<BankAccount>>> accountsStartWith(@PathVariable String prefix) {
        var response = new AppResponce<List<BankAccount>>();
        response.setMsg("account list");
        response.setSts("success");
        response.setBody(service.namesStartsWith(prefix));

        return ResponseEntity.ok(response);
    }
}
