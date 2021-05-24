package velykyi.vladyslav.task4.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import velykyi.vladyslav.task4.dto.RoleDto;
import velykyi.vladyslav.task4.model.Role;

@Mapper(componentModel = "spring",  injectionStrategy = InjectionStrategy.FIELD)
public interface RoleMapper {
    RoleDto roleToRoleDto(Role role);
    Role roleDtoToRole(RoleDto roleDto);
}
