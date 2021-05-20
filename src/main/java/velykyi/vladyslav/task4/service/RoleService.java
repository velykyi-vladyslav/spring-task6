package velykyi.vladyslav.task4.service;

import velykyi.vladyslav.task4.dto.RoleDto;

public interface RoleService {
    RoleDto getRole(String name);

    RoleDto createRole(RoleDto roleDto);

    void deleteRole(String name);

    RoleDto updateRole(String name, RoleDto roleDto);
}
