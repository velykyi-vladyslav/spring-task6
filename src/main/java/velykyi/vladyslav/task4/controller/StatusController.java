package velykyi.vladyslav.task4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import velykyi.vladyslav.task4.controller.assembler.RoleAssembler;
import velykyi.vladyslav.task4.controller.assembler.StatusAssembler;
import velykyi.vladyslav.task4.controller.model.RoleModel;
import velykyi.vladyslav.task4.controller.model.StatusModel;
import velykyi.vladyslav.task4.dto.StatusDto;
import velykyi.vladyslav.task4.service.StatusService;
import velykyi.vladyslav.task4.service.mapper.StatusMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("api/v1/statuses")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;
    private final StatusAssembler statusAssembler;
    private StatusMapper mapper = Mappers.getMapper(StatusMapper.class);


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{name}")
    public StatusModel getStatus(@PathVariable String name) {
        log.info("Get status by name: " + name);
        StatusDto statusDto = statusService.getStatusDto(name);

        return statusAssembler.toModel(statusDto);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
