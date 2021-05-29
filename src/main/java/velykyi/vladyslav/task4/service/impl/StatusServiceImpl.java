package velykyi.vladyslav.task4.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import velykyi.vladyslav.task4.dto.StatusDto;
import velykyi.vladyslav.task4.exceptions.StatusNotFoundException;
import velykyi.vladyslav.task4.model.Status;
import velykyi.vladyslav.task4.model.enums.Statuses;
import velykyi.vladyslav.task4.repository.StatusRepository;
import velykyi.vladyslav.task4.service.StatusService;
import velykyi.vladyslav.task4.service.mapper.StatusMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;
    private StatusMapper mapper = Mappers.getMapper(StatusMapper.class);

    @Override
    public StatusDto getStatusDto(String name) {
        log.info("getStatusDto by name: {}", name);

        Status status = statusRepository.findByName(name).orElseThrow(StatusNotFoundException::new);
        return map(status);
    }

    @Override
    public Status getStatus(Statuses status) {
        log.info("getStatus by name: {}", status.get());

        return statusRepository.findByName(status.get())
                .orElseThrow(StatusNotFoundException::new);
    }


    private StatusDto map(Status status) {
        log.info("Mapping [Status] to [StatusDTO]");
        return mapper.statusToStatusDto(status);
    }

    private Status map(StatusDto statusDto) {
        log.info("Mapping [RoleDTO] to [Role]");
        return mapper.statusDtoToStatus(statusDto);
    }
}
