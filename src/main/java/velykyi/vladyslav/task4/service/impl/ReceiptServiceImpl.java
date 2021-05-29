package velykyi.vladyslav.task4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import velykyi.vladyslav.task4.exceptions.ReceiptNotFoundException;
import velykyi.vladyslav.task4.exceptions.RoleNotFoundException;
import velykyi.vladyslav.task4.model.Employee;
import velykyi.vladyslav.task4.model.Receipt;
import velykyi.vladyslav.task4.model.Status;
import velykyi.vladyslav.task4.repository.ReceiptRepository;
import velykyi.vladyslav.task4.service.ReceiptService;
import velykyi.vladyslav.task4.service.StatusService;
import velykyi.vladyslav.task4.model.enums.Statuses;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final StatusService statusService;

    @Override
    public Receipt getReceiptById(Long id) {
        log.info("ReceiptService: getReceiptById by id: {}", id);
        return receiptRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }

    //todo
    @Override
    public Receipt getReceiptByEmployee(Employee employee) {
        return null;
    }

    @Override
    public Receipt getReceipt(Long id) {
        log.info("ReceiptService: getReceipt by id: {}", id);
        return receiptRepository.findById(id).orElseThrow(ReceiptNotFoundException::new);
    }

    @Override
    public List<Receipt> getReceipts(String statusName, int page) {
        log.info("ReceiptService: get pageable receipts for status: {}", statusName);
        Status status = statusService.getStatus(Statuses.valueOf(statusName));
        Pageable firstPageWithTwoElements = PageRequest.of(page, 5, Sort.by("id").descending());

        List<Receipt> receipts = receiptRepository.findAllByParentStatus(status, firstPageWithTwoElements);

        return receipts;
    }

    @Override
    public Receipt createNewReceipt() {
        log.info("ReceiptService: createReceipt");
        Receipt receipt = buildNewReceipt();
        receiptRepository.save(receipt);
        return receipt;
    }

    @Override
    public void deleteReceipt(Long id) {
        log.info("ReceiptService: deleteReceipt by id: {}", id);
        Receipt receipt = receiptRepository.findById(id).orElseThrow(ReceiptNotFoundException::new);

        receiptRepository.delete(receipt);
    }

    @Override
    public Receipt updateReceipt(Long id, Receipt receipt) {
        log.info("ReceiptService: updateReceipt by id: {}", id + " ; receipt for update: " + receipt);

        if (!receiptRepository.existsById(id)) {
            log.error("receipt is not exists with this id: {}", id);
            throw new ReceiptNotFoundException();
        }

        return  receiptRepository.save(receipt);
    }

    @Override
    public Receipt closeReceipt(Long id) {
        log.info("ReceiptService: close receipt by id: {}", id);
        Receipt receipt = receiptRepository
                .findById(id)
                .orElseThrow(ReceiptNotFoundException::new);
        Status status = statusService.getStatus(Statuses.CLOSED);

        receipt.setParentStatus(status);
        receiptRepository.save(receipt);
        return receipt;

    }

    private Receipt buildNewReceipt() {
        Receipt receipt = new Receipt();
        receipt.setBill(BigDecimal.ZERO);
        receipt.setParentStatus(statusService.getStatus(Statuses.CREATED));
        return receipt;
    }

}
