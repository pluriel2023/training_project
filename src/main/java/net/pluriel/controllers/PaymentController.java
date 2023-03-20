package net.pluriel.controllers;

import net.pluriel.dto.requests.PaymentRequestDto;
import net.pluriel.dto.responses.PaymentResponseDto;
import net.pluriel.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> create(@RequestBody PaymentRequestDto PaymentRequestDto){
        return new ResponseEntity<PaymentResponseDto>(paymentService.create(PaymentRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> getOne(@PathVariable(name = "id") Integer PaymentId){
        return new ResponseEntity<PaymentResponseDto>(paymentService.getOne(PaymentId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> updatePayment(@PathVariable(name = "id") Integer PaymentId, @RequestBody PaymentRequestDto paymentRequestDto){
        return new ResponseEntity<PaymentResponseDto>(paymentService.update(paymentRequestDto, PaymentId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentResponseDto>> getAll(){
        return new ResponseEntity<List<PaymentResponseDto>>(paymentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/allPaginate")
    public ResponseEntity<Page<PaymentResponseDto>> getAllPaginate(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ){
        return new ResponseEntity<Page<PaymentResponseDto>>(paymentService.getAllInPage(page,size), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer PaymentId){
        paymentService.delete(PaymentId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
