package velykyi.vladyslav.task4.service;

import velykyi.vladyslav.task4.dto.RoleDto;
import velykyi.vladyslav.task4.model.Role;

public interface RoleService {
    RoleDto getRoleDto(String name);

    Role getRole(String name);

    RoleDto createRole(RoleDto roleDto);

    void deleteRole(String name);

    RoleDto updateRole(String name, RoleDto roleDto);
}
