package org.example.catalogueservice.mapper;

import org.example.catalogueservice.entity.Task;
import org.example.catalogueservice.payload.response.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    @Mapping(target = "status", source = "status.name")
    TaskResponse toTaskResponse(Task task);

}