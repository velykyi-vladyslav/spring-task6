package velykyi.vladyslav.task4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.dto.ReceiptDto;
import velykyi.vladyslav.task4.exceptions.ReceiptNotFoundException;
import velykyi.vladyslav.task4.exceptions.RoleNotFoundException;
import velykyi.vladyslav.task4.model.Receipt;
import velykyi.vladyslav.task4.model.Status;
import velykyi.vladyslav.task4.repository.ReceiptRepository;
import velykyi.vladyslav.task4.service.ReceiptService;
import velykyi.vladyslav.task4.service.StatusService;
import velykyi.vladyslav.task4.service.mapper.ReceiptMapper;
import velykyi.vladyslav.task4.model.enums.Statuses;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;
    private final StatusService statusService;


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
    public List<ReceiptDto> getReceipts(String statusName, int page) {
        Status status = statusService.getStatus(Statuses.valueOf(statusName));
        Pageable firstPageWithTwoElements = PageRequest.of(page, 5, Sort.by("id").descending());

        List<Receipt> receipts = receiptRepository.findAllByParentStatus(status, firstPageWithTwoElements);
        List<ReceiptDto> receiptDtos = receipts.stream()
                .map(this::map)
                .collect(Collectors.toList());


        return receiptDtos;
    }

    @Override
    public Receipt createNewReceipt() {
        log.info("Service: createReceipt");
        Receipt receipt = buildNewReceipt();
        receiptRepository.save(receipt);
        return receipt;
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

    private Receipt buildNewReceipt() {
        Receipt receipt = new Receipt();
        receipt.setBill(BigDecimal.ZERO);
        receipt.setParentStatus(statusService.getStatus(Statuses.CREATED));
        return receipt;
    }
}
