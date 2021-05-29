package velykyi.vladyslav.task4.service;

import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.dto.ReceiptDto;
import velykyi.vladyslav.task4.model.Receipt;

import java.util.List;

public interface ReceiptService {
    ReceiptDto getReceiptDtoById(Long id);

    ReceiptDto getReceiptDtoByEmployee(EmployeeDto employeeDto);

    ReceiptDto getReceiptDtoByStatus(ReceiptDto receiptDto);

    Receipt getReceipt(Long id);

    List<ReceiptDto> getReceipts(String statusName);

    Receipt createNewReceipt();

    void deleteReceipt(Long id);

    ReceiptDto updateReceipt(Long id, ReceiptDto receiptDto);
}
