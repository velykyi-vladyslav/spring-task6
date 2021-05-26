package velykyi.vladyslav.task4.service;

import velykyi.vladyslav.task4.dto.StatusDto;
import velykyi.vladyslav.task4.model.Status;
import velykyi.vladyslav.task4.model.enums.Statuses;

public interface StatusService {

    StatusDto getStatusDto(String name);

    Status getStatus(Statuses status);
}
