package com.week2.casestudy.controller;

import com.week2.casestudy.domain.BankAccount;
import com.week2.casestudy.dto.AppResponse;
import com.week2.casestudy.exception.InvalidAmountException;
import com.week2.casestudy.service.BankService;
import com.week2.casestudy.service.BankServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public ResponseEntity<AppResponse<Integer>> createBankAccount(@RequestBody BankAccount ba) {
        service.createNewAccount(ba);
        var response = new AppResponse<Integer>();
        response.setMsg("account created successfully");
        response.setSts("success");
        response.setBody(0);
        return ResponseEntity.ok(response);
    }

    @PutMapping // PUT -> http://localhost:8080/123456
    public ResponseEntity<AppResponse<Double>> withdrawMoney(@RequestBody BankAccount ba) {
        try {
            double amt = service.withdraw(ba.getAcNum(), ba.getBalance());
            var response = new AppResponse<Double>();
            response.setMsg("money withdrawn successfully");
            response.setSts("success");
            response.setBody(amt);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InvalidAmountException e) {
            var response = new AppResponse<Double>();
            response.setMsg(e.getMessage());
            response.setSts("fail");
            response.setBody(0.0);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/deposit") // PUT -> http://localhost:8080/bank/deposit
    public ResponseEntity<AppResponse<Double>> depositMoney(@RequestBody BankAccount ba) {
        try {
            double amt = service.deposit(ba.getAcNum(), ba.getBalance());
            var response = new AppResponse<Double>();
            response.setMsg("money deposit successfully");
            response.setSts("success");
            response.setBody(amt);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (InvalidAmountException e) {
            var response = new AppResponse<Double>();
            response.setMsg(e.getMessage());
            response.setSts("fail");
            response.setBody(0.0);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/withdraw") // PUT -> http://localhost:8080/bank/withdraw
    public ResponseEntity<AppResponse<Double>> withdraw(@RequestBody BankAccount ba) {
        try {
            double amt = service.withdraw(ba.getAcNum(), ba.getBalance());
            var response = new AppResponse<Double>();
            response.setMsg("money withdraw successfully");
            response.setSts("success");
            response.setBody(amt);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (InvalidAmountException e) {
            var response = new AppResponse<Double>();
            response.setMsg(e.getMessage());
            response.setSts("fail");
            response.setBody(0.0);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{prefix}")
    public ResponseEntity<AppResponse<List<BankAccount>>> accountsStartWith(@PathVariable String prefix) {
        var response = new AppResponse<List<BankAccount>>();
        response.setMsg("account list");
        response.setSts("success");
        response.setBody(service.namesStartsWith(prefix));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/accnum")
    public ResponseEntity<AppResponse<BankAccount>> findAccountByAcNum(@RequestBody BankAccount ba) {
        BankAccount acNum = service.findAccountByAcNum(ba.getAcNum());
        var response = new AppResponse<BankAccount>();
        response.setMsg("account found by AcNum");
        response.setSts("success");
        response.setBody(acNum);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
//        }catch (InvalidAmountException e) {
//            var response = new AppResponse<BankAccount>();
//            response.setMsg(e.getMessage());
//            response.setSts("fail");
//            response.setBody(0);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
    }

    @PutMapping("/activate")
    public ResponseEntity<AppResponse<Boolean>> activate(@RequestBody BankAccount ba) {
        Boolean stat = service.activateAccount(ba.getAcNum());
        var response = new AppResponse<Boolean>();
        response.setMsg("account activated");
        response.setSts("success");
        response.setBody(stat);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/deactivate")
    public ResponseEntity<AppResponse<Boolean>> deactivate(@RequestBody BankAccount ba) {
        Boolean stat = service.deActivateAccount(ba.getAcNum());
        var response = new AppResponse<Boolean>();
        response.setMsg("account deactivated");
        response.setSts("success");
        response.setBody(stat);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<AppResponse<List<BankAccount>>> findall() {
        var response = new AppResponse<List<BankAccount>>();
        response.setMsg("account list");
        response.setSts("success");
        response.setBody(service.findAllBankAccounts());
        return ResponseEntity.ok(response);
    }
}