package al.goxhaj.kafkaexample.repository.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import al.goxhaj.kafkaexample.repository.entity.CustomersEntity;
import al.goxhaj.kafkaexample.service.dto.CustomersDto;


@Mapper(componentModel = "spring")
public interface CustomersBidirectionalMapper
        extends DtoToEntityBidirectionalMapper<CustomersDto, CustomersEntity> {

    @Override
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
    })
    CustomersDto toDto(CustomersEntity entity);

    @Override
    @InheritInverseConfiguration
    CustomersEntity toEntity(CustomersDto dto);
}
