package az.pashabank.cardzone.controller;


import az.pashabank.cardzone.model.dto.TransactionDto;
import az.pashabank.cardzone.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/{id}/transactions")
    public void doTransaction(@PathVariable long id, @Valid @RequestBody TransactionDto transactionDto) {
        System.out.println(transactionDto);
        transactionService.doTransaction(id, transactionDto);
    }
}
