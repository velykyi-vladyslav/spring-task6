package velykyi.vladyslav.task4.service.mapper;

import org.mapstruct.Mapper;
import velykyi.vladyslav.task4.dto.StatusDto;
import velykyi.vladyslav.task4.model.Status;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    StatusDto statusToStatusDto(Status status);
    Status statusDtoToStatus(StatusDto statusDto);
}
