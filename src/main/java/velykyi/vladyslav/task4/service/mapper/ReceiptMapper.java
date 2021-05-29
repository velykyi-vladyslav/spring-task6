package velykyi.vladyslav.task4.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import velykyi.vladyslav.task4.dto.ReceiptDto;
import velykyi.vladyslav.task4.dto.StatusDto;
import velykyi.vladyslav.task4.model.Receipt;
import velykyi.vladyslav.task4.model.Status;
import velykyi.vladyslav.task4.model.enums.Statuses;
import velykyi.vladyslav.task4.service.StatusService;


@Mapper(componentModel = "spring")
public abstract class ReceiptMapper {
    @Autowired
    private StatusService statusService;

    abstract public ReceiptDto receiptToReceiptDto(Receipt receipt);

    abstract public Receipt receiptDtoToReceipt(ReceiptDto receipt);

    public StatusDto mapStatusDtoToStatus(Status parentStatus) {
        return new StatusDto(parentStatus.getName());
    }

    public Status statusToStatusDto(StatusDto parentStatus) {
        return statusService.getStatus(Statuses.valueOf(parentStatus.getName()));
    }

}
