package velykyi.vladyslav.task4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.dto.ReceiptDto;
import velykyi.vladyslav.task4.exceptions.ReceiptNotFoundException;
import velykyi.vladyslav.task4.exceptions.RoleNotFoundException;
import velykyi.vladyslav.task4.model.Receipt;
import velykyi.vladyslav.task4.repository.ReceiptRepository;
import velykyi.vladyslav.task4.service.ReceiptService;
import velykyi.vladyslav.task4.service.mapper.ReceiptMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private ReceiptMapper receiptMapper = Mappers.getMapper(ReceiptMapper.class);

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
    public ReceiptDto createReceipt(ReceiptDto receiptDto) {
        log.info("createReceipt, receiptDto: {}", receiptDto);
        Receipt receipt = receiptRepository.save(map(receiptDto));

        return map(receipt);
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
}
