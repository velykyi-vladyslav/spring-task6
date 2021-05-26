package velykyi.vladyslav.task4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import velykyi.vladyslav.task4.controller.StatusController;
import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.dto.ReceiptDto;
import velykyi.vladyslav.task4.exceptions.ReceiptNotFoundException;
import velykyi.vladyslav.task4.exceptions.RoleNotFoundException;
import velykyi.vladyslav.task4.exceptions.StatusNotFoundException;
import velykyi.vladyslav.task4.model.Receipt;
import velykyi.vladyslav.task4.model.Status;
import velykyi.vladyslav.task4.repository.ReceiptRepository;
import velykyi.vladyslav.task4.repository.StatusRepository;
import velykyi.vladyslav.task4.service.ReceiptService;
import velykyi.vladyslav.task4.service.StatusService;
import velykyi.vladyslav.task4.service.mapper.ReceiptMapper;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;



    @Override
    public ReceiptDto getReceiptDtoById(Long id) {
        log.info("getReceiptDtoById by id: {}", id);
        Receipt receipt = receiptRepository.findById(id).orElseThrow(RoleNotFoundException::new);

        return map(receipt);
    }

    @Override
    public ReceiptDto getReceiptDtoByEmployee(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public ReceiptDto getReceiptDtoByStatus(ReceiptDto receiptDto) {
        return null;
    }

    @Override
    public Receipt getReceipt(Long id) {
        log.info("getReceipt by id: {}", id);
        return receiptRepository.findById(id).orElseThrow(ReceiptNotFoundException::new);
    }

    @Override
    public ReceiptDto createReceipt() {
        log.info("Service: createReceipt");
        return saveNewReceipt(new Receipt());
    }

    @Override
    public void deleteReceipt(Long id) {
        log.info("deleteReceipt by id: {}", id);
        Receipt receipt = receiptRepository.findById(id).orElseThrow(ReceiptNotFoundException::new);

        receiptRepository.delete(receipt);
    }

    @Override
    public ReceiptDto updateReceipt(Long id, ReceiptDto receiptDto) {
        log.info("updateReceipt by id: {}", id + " ; receiptDto for update: " + receiptDto);

        if (!receiptRepository.existsById(id)) {
            log.error("receipt is not exists with this id: {}", id);
            throw new ReceiptNotFoundException();
        }

        Receipt receipt = receiptRepository.save(map(receiptDto));
        return map(receipt);
    }

    private Receipt map(ReceiptDto receiptDto) {
        log.info("Mapping [ReceiptDTO] to [Receipt]");
        return receiptMapper.receiptDtoToReceipt(receiptDto);
    }

    private ReceiptDto map(Receipt receipt) {
        log.info("Mapping [Receipt] to [ReceiptDTO]");
        return receiptMapper.receiptToReceiptDto(receipt);
    }
    private ReceiptDto saveNewReceipt(Receipt receipt){
        //todo change this code
        receipt.setBill(BigDecimal.ZERO);
        Status status = new Status();
        status.setName("created");
        status.setId(1L);
        receipt.setParentStatus(status);
        receiptRepository.save(receipt);
        return map(receipt);
    }
}
