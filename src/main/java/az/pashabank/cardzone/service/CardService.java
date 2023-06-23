package az.pashabank.cardzone.service;

import az.pashabank.cardzone.dao.entity.Card;
import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.mapper.CardMapper;
import az.pashabank.cardzone.model.dto.CardDto;
import az.pashabank.cardzone.model.exception.CardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;


//    public List<CardDto> findAll(Pageable pageable, Long customerId, Double minBalance, Double maxBalance){
//        Page<Card> allBySpecification = cardRepository.findAllBySpecification(customerId, minBalance, maxBalance, pageable);
//        return allBySpecification.getContent().stream()
//                .map(cardMapper::cardToCardDto).toList();
////        return this.cardRepository.findAll(pageable).getContent().stream().map(this.cardMapper::cardToCardDto).toList();
//    }

    public List<CardDto> findAll(Pageable pageable) {
        return this.cardRepository.findAll(pageable).stream().map(this.cardMapper::cardToCardDto).toList();
    }

    public CardDto findById(long id) {
        Optional<Card> cardById = this.cardRepository.findById(id);

        Card card = cardById.orElseThrow(() -> new CardNotFoundException("card not found"));
        return cardMapper.cardToCardDto(card);
    }

    @Transactional
    public void deleteById(long id) {
        this.cardRepository.deleteById(id);
    }

    @Transactional
    public void saveCard(CardDto cardDto) {
        Card card = cardMapper.cardDtoToCard(cardDto);
        this.cardRepository.save(card);
    }

}
