package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.Transaction;
import az.pashabank.cardzone.model.dto.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    Transaction transactionDtoToTransaction(TransactionDto transactionDto);

    TransactionDto transactionToTransactionDto(Transaction transaction);
}
