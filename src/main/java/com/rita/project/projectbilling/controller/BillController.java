package com.rita.project.projectbilling.controller;
import com.rita.project.projectbilling.dto.AppResponse;
import com.rita.project.projectbilling.dto.BillDto;
import com.rita.project.projectbilling.exception.InvalidException;
import com.rita.project.projectbilling.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bill")
@RestController
public class BillController {

    @Autowired
    private BillService service;

    @PostMapping // http://localhost:8080/bill/
    public ResponseEntity<AppResponse<BillDto>> createBill(@RequestBody BillDto dto) {

        var svObj = service.createBill(dto);

        var response = new AppResponse<BillDto>();
        response.setStatus("success");
        response.setMessage("bill created successfully");
        response.setBody(svObj);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")   // put  http://localhost:8080/bill/update
    public ResponseEntity<AppResponse<BillDto>> updateBill(@RequestBody BillDto dto) {
    try{

        var updatedBill = service.createBill(dto);

        var response = new AppResponse<BillDto>();
        response.setStatus("success");
        response.setMessage("bill updated");
        response.setBody(updatedBill);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }catch (InvalidException e){
        var response = new AppResponse<BillDto>();
        response.setMessage("fail");
        response.setMessage(e.getMessage());
        response.setBody(response.getBody());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    }

    @PutMapping("/{id}")    //put ->   http:localhost:8080/bill/    id
      public ResponseEntity<AppResponse<Boolean>> paidBill(@PathVariable Long id) {
        try {
            boolean paidBill = service.PaidBill(id);
            var response = new AppResponse<Boolean>();
            response.setStatus("success");
            response.setMessage("mark as paid");
            response.setBody(paidBill);

            return ResponseEntity.ok(response);
        } catch (InvalidException e) {

            var response = new AppResponse<Boolean>();
            response.setStatus("fail");
            response.setMessage(e.getMessage());
            response.setBody(false);

            return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}