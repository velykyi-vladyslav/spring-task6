package velykyi.vladyslav.task4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import velykyi.vladyslav.task4.controller.assembler.ReceiptAssembler;
import velykyi.vladyslav.task4.controller.model.ReceiptModel;
import velykyi.vladyslav.task4.dto.ReceiptDto;
import velykyi.vladyslav.task4.model.Receipt;
import velykyi.vladyslav.task4.service.ReceiptService;
import velykyi.vladyslav.task4.service.mapper.ReceiptMapper;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/receipts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReceiptController {
    private final ReceiptService receiptService;
    private final ReceiptAssembler receiptAssembler;
    private final ReceiptMapper receiptMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReceiptModel createReceipt() {
        Receipt receipt = receiptService.createNewReceipt();

        return receiptAssembler.toModel(map(receipt));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ReceiptModel getReceipt(@PathVariable Long id) {
        Receipt receipt = receiptService.getReceiptById(id);

        return receiptAssembler.toModel(map(receipt));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/status/{status}/{page}")
    public List<ReceiptModel> getReceipts(@PathVariable String status, @PathVariable int page) {
        List<Receipt> receipts = receiptService.getReceipts(status.toUpperCase(), page);
        return receipts.stream()
                .map(this::map)
                .map(receiptAssembler::toModel)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public ReceiptModel updateReceipt(@PathVariable Long id, @RequestBody ReceiptDto receiptDto) {
        //todo do logic when table receipt-product will be exist

        Receipt receipt = receiptService.updateReceipt(id, map(receiptDto));
        return receiptAssembler.toModel(map(receipt));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReceipt(@Valid @PathVariable Long id) {
        receiptService.deleteReceipt(id);

        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/close/{id}")
    public ReceiptModel closeReceipt(@PathVariable Long id) {
        Receipt receipt = receiptService.closeReceipt(id);

        return receiptAssembler.toModel(map(receipt));
    }

    private Receipt map(ReceiptDto receiptDto) {
        return receiptMapper.receiptDtoToReceipt(receiptDto);
    }

    private ReceiptDto map(Receipt receipt) {
        return receiptMapper.receiptToReceiptDto(receipt);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
