package az.pashabank.cardzone.service;

import az.pashabank.cardzone.client.SimpleClient;
import az.pashabank.cardzone.dao.entity.Card;
import az.pashabank.cardzone.dao.entity.Transaction;
import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.dao.repository.TransactionRepository;
import az.pashabank.cardzone.mapper.TransactionMapper;
import az.pashabank.cardzone.model.dto.TransactionDto;
import az.pashabank.cardzone.model.exception.CardNotFoundException;
import az.pashabank.cardzone.model.exception.NotEnoughBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final CardRepository cardRepository;

    private final SimpleClient client;

    @Transactional
    public void doTransaction(long id, TransactionDto transactionDto) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("card not found"));
        String type = transactionDto.getType();
        double amount = transactionDto.getAmount();
        double balance = card.getBalance();

        if ("debit".equals(type)) {
            if (balance >= amount) {
                balance = balance - amount;


            } else {
                throw new NotEnoughBalanceException("not Enough balance");
            }
        } else {
            balance = balance + amount;
        }

        card.setBalance(balance);
        Transaction transaction = this.transactionMapper.transactionDtoToTransaction(transactionDto);
        transaction.setCard(card);
        Transaction save = this.transactionRepository.save(transaction);
        this.cardRepository.updateCardBalanceById(id, balance);

    }


    @Transactional
    public void calculateAndSaveCashBack(long id) {
        Transaction transaction = this.transactionRepository.findById(id).get();
        if (transaction.isHasCashback()) {
            System.out.println("Before: " + transaction);
            double cashback = client.calculateCashback(transaction.getAmount());
            Card card = transaction.getCard();
            card.setBalance(card.getBalance() + cashback);
            transaction.setHasCashback(false);
            this.cardRepository.save(card);
            this.transactionRepository.save(transaction);

        }
    }

    public List<Transaction> findAll() {
        return this.transactionRepository.findAll();
    }

}
