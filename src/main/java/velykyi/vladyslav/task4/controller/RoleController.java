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
import velykyi.vladyslav.task4.controller.model.RoleModel;
import velykyi.vladyslav.task4.dto.RoleDto;
import velykyi.vladyslav.task4.service.RoleService;
import velykyi.vladyslav.task4.service.mapper.RoleMapper;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final RoleAssembler roleAssembler;
    private RoleMapper mapper = Mappers.getMapper(RoleMapper.class);


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{name}")
    public RoleModel getRole(@PathVariable String name) {
        log.info("Get role by login: " + name);
        RoleDto roleDto = roleService.getRoleDto(name);

        return roleAssembler.toModel(roleDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RoleModel createRole(@Valid @RequestBody RoleDto roleDto) {
        log.info("Create role: {}", roleDto);
        RoleDto role = roleService.createRole(roleDto);

        return roleAssembler.toModel(role);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{name}")
    public RoleModel updateRole(@PathVariable String name, @RequestBody RoleDto roleDto) {
        log.info("Update role: {}", roleDto + " by name: " + name);
        RoleDto role = roleService.updateRole(name, roleDto);
        return roleAssembler.toModel(role);
    }


    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteRole(@Valid @PathVariable String name) {
        log.info("Delete role by login: " + name);
        roleService.deleteRole(name);
        return ResponseEntity.noContent().build();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
