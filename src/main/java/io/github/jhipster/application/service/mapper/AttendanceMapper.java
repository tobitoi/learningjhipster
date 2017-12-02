package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.AttendanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Attendance and its DTO AttendanceDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface AttendanceMapper extends EntityMapper<AttendanceDTO, Attendance> {

    @Mapping(source = "employee.id", target = "employeeId")
    AttendanceDTO toDto(Attendance attendance); 

    @Mapping(source = "employeeId", target = "employee")
    Attendance toEntity(AttendanceDTO attendanceDTO);

    default Attendance fromId(Long id) {
        if (id == null) {
            return null;
        }
        Attendance attendance = new Attendance();
        attendance.setId(id);
        return attendance;
    }
}
