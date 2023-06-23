package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.CardDto;
import az.pashabank.cardzone.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

//    @GetMapping(path = "")
//    public List<CardDto> getAllCards(Pageable pageable, Long customerId, Double minBalance, Double maxBalance){
//        return this.cardService.findAll(pageable, customerId, minBalance, maxBalance);
//    }

    @GetMapping(path = "")
    public List<CardDto> getAllCards(Pageable pageable) {
        return this.cardService.findAll(pageable);
    }

    @GetMapping(path = "/{id}")
    public CardDto getCardById(@PathVariable long id) {
        return this.cardService.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable long id) {
        this.cardService.deleteById(id);
    }

    @PostMapping(path = "")
    public void addCard(@Valid @RequestBody CardDto cardDto) {
        cardDto.setBalance(0.0);
        this.cardService.saveCard(cardDto);
    }
}
