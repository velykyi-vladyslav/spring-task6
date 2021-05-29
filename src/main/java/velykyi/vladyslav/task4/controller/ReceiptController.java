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
        log.info("ReceiptController: Create receipt");

        Receipt receipt = receiptService.createNewReceipt();
        ReceiptDto receiptDto = receiptMapper.receiptToReceiptDto(receipt);
        return receiptAssembler.toModel(receiptDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ReceiptModel getReceipt(@PathVariable Long id) {
        log.info("Get receipt by id: " + id);

        ReceiptDto receiptDto = receiptService.getReceiptDtoById(id);
        return receiptAssembler.toModel(receiptDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/status/{status}/{page}")
    public List<ReceiptModel> getReceipts(@PathVariable String status, @PathVariable int page) {
        log.info("ReceiptController: Get receipt by status name: " + status);

        List<ReceiptDto> receiptDtos = receiptService.getReceipts(status.toUpperCase(), page);
        return receiptDtos.stream()
                .map(receiptAssembler::toModel)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public ReceiptModel updateReceipt(@PathVariable Long id, @RequestBody ReceiptDto receiptDto) {
        log.info("ReceiptController: Update receipt: {}", receiptDto + " by id: " + id);

        //todo do logic when table receipt-product will be exist

        ReceiptDto receipt = receiptService.updateReceipt(id, receiptDto);
        return receiptAssembler.toModel(receipt);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReceipt(@Valid @PathVariable Long id) {
        log.info("ReceiptController: Delete receipt by id: " + id);

        receiptService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/close/{id}")
    public ReceiptModel closeReceipt(@PathVariable Long id) {
        log.info("ReceiptController: Close receipt by id: " + id);

        Receipt receipt = receiptService.closeReceipt(id);
        ReceiptDto receiptDto = receiptMapper.receiptToReceiptDto(receipt);
        return receiptAssembler.toModel(receiptDto);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
