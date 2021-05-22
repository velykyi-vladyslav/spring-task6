package velykyi.vladyslav.task4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import velykyi.vladyslav.task4.dto.RoleDto;
import velykyi.vladyslav.task4.exceptions.RoleNotFoundException;
import velykyi.vladyslav.task4.model.Role;
import velykyi.vladyslav.task4.repository.RoleRepository;
import velykyi.vladyslav.task4.service.RoleService;
import velykyi.vladyslav.task4.service.mapper.RoleMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private RoleMapper mapper = Mappers.getMapper(RoleMapper.class);

    @Override
    public RoleDto getRoleDto(String name) {
        Role role = roleRepository.findByName(name).orElseThrow(RoleNotFoundException::new);
        log.info("getRole by login: {}", role);

        return mapRoleToRoleDto(role);
    }

    @Override
    public Role getRole(String name) {
        Role role = roleRepository.findByName(name).orElseThrow(RoleNotFoundException::new);
        log.info("getRole by login: {}", role);

        return role;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        log.info("createRole, roleDto: {}", roleDto);
        Role role = mapRoleDtoToRole(roleDto);
        role = roleRepository.save(role);

        return mapRoleToRoleDto(role);
    }

    @Override
    public void deleteRole(String name) {
        log.info("deleteRole by name: {}", name);
        Role role = roleRepository.findByName(name)
                .orElseThrow(RoleNotFoundException::new);
        roleRepository.delete(role);
    }

    @Override
    public RoleDto updateRole(String name, RoleDto roleDto) {
        Role role = mapRoleDtoToRole(roleDto);
        log.info("updateRole by name: {}", name + " ; updated role: " + role);

        if (!roleRepository.existsByName(name)) {
            log.error("role is not exists with this name: {}", name);
            throw new RoleNotFoundException();
        }

        role = roleRepository.save(role);
        return mapRoleToRoleDto(role);
    }

    private RoleDto mapRoleToRoleDto(Role role) {
        log.info("Mapping [Role] to [RoleDTO]");
        return mapper.roleToRoleDto(role);

    }

    private Role mapRoleDtoToRole(RoleDto roleDto) {
        log.info("Mapping [RoleDTO] to [Role]");
        return mapper.roleDtoToRole(roleDto);

    }
}
