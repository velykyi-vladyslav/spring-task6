package velykyi.vladyslav.task4.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import velykyi.vladyslav.task4.dto.StatusDto;
import velykyi.vladyslav.task4.model.Status;

@Mapper(componentModel = "spring",  injectionStrategy = InjectionStrategy.FIELD)
public interface StatusMapper {
    StatusDto statusToStatusDto(Status status);
    Status statusDtoToStatus(StatusDto statusDto);
}
