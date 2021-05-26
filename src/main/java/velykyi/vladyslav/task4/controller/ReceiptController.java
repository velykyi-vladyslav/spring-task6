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
import velykyi.vladyslav.task4.service.StatusService;
import velykyi.vladyslav.task4.service.mapper.ReceiptMapper;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/receipts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReceiptController {
    private final ReceiptService receiptService;
    private final ReceiptAssembler receiptAssembler;
    private final ReceiptMapper receiptMapper;
    private final StatusService statusService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ReceiptModel getReceipt(@PathVariable Long id) {
        log.info("Get receipt by id: " + id);
        ReceiptDto receiptDto = receiptService.getReceiptDtoById(id);

        return receiptAssembler.toModel(receiptDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{status}")
    public List<ReceiptModel> getReceipts(@PathVariable String status) {
        log.info("Get status by name: " + status);
        //StatusDto statusDto = statusService.getStatusDto(name);
        //todo for each link
        return null;//statusService.getStatus(Statuses.valueOf(status));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReceiptDto createReceipt() {
        log.info("ReceiptController: Create receipt");
        Receipt receipt = receiptService.createNewReceipt();
        //TODO CHANGE TO MODEL
        return receiptMapper.receiptToReceiptDto(receipt);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public ReceiptModel updateReceipt(@PathVariable Long id, @RequestBody ReceiptDto receiptDto) {
        log.info("Update receipt: {}", receiptDto + " by id: " + id);
        ReceiptDto receipt = receiptService.updateReceipt(id, receiptDto);

        return receiptAssembler.toModel(receipt);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReceipt(@Valid @PathVariable Long id) {
        log.info("Delete receipt by id: " + id);
        receiptService.deleteReceipt(id);

        return ResponseEntity.noContent().build();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
