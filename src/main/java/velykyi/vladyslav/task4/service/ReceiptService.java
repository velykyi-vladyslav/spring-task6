package velykyi.vladyslav.task4.service;

import velykyi.vladyslav.task4.model.Employee;
import velykyi.vladyslav.task4.model.Receipt;

import java.util.List;

public interface ReceiptService {
    Receipt getReceiptById(Long id);

    Receipt getReceiptByEmployee(Employee employee);


    Receipt getReceipt(Long id);

    List<Receipt> getReceipts(String statusName, int page);

    Receipt createNewReceipt();

    void deleteReceipt(Long id);

    Receipt updateReceipt(Long id, Receipt receipt);


    Receipt closeReceipt(Long id);
}
