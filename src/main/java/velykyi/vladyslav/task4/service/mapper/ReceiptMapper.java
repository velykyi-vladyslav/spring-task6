package velykyi.vladyslav.task4.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import velykyi.vladyslav.task4.dto.ReceiptDto;
import velykyi.vladyslav.task4.model.Receipt;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)

public abstract class ReceiptMapper {

    abstract public ReceiptDto receiptToReceiptDto(Receipt receipt);

    abstract public Receipt receiptDtoToReceipt(ReceiptDto receipt);
}
