package velykyi.vladyslav.task4.service;

import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.dto.ReceiptDto;
import velykyi.vladyslav.task4.model.Receipt;

public interface ReceiptService {
    ReceiptDto getReceiptDtoById(Long id);

    ReceiptDto getReceiptDtoByEmployee(EmployeeDto employeeDto);

    ReceiptDto getReceiptDtoByStatus(ReceiptDto receiptDto);

    Receipt getReceipt(Long id);

    ReceiptDto createReceipt();

    void deleteReceipt(Long id);

    ReceiptDto updateReceipt(Long id, ReceiptDto receiptDto);
}
