package az.pashabank.cardzone.scheduler;

import az.pashabank.cardzone.client.SimpleClient;
import az.pashabank.cardzone.dao.entity.Transaction;
import az.pashabank.cardzone.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CalculateCashBack {
    private final TransactionService transactionService;
    private final SimpleClient client;

    @Scheduled(fixedRate = 1000)
    public void calculatePerDay() {
        List<Transaction> allTranscations = this.transactionService.findAll();
        for (var transaction : allTranscations) {
            this.transactionService.calculateAndSaveCashBack(transaction.getId());
        }
    }
}
