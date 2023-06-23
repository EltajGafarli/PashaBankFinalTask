package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.Card;
import az.pashabank.cardzone.model.dto.CardDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CardMapper {
    Card cardDtoToCard(CardDto cardDto);

    CardDto cardToCardDto(Card card);
}
