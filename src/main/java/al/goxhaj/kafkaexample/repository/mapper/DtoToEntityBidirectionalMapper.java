package al.goxhaj.kafkaexample.repository.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contract for a generic dto-to-entity/entity-to-dto mapper.
 *
 * @param <DTO>    - Dto type parameter
 * @param <ENTITY> - Entity type parameter
 */
public interface DtoToEntityBidirectionalMapper<DTO, ENTITY> {
    // Override only if strictly necessary.
    default List<DTO> toDtos(List<ENTITY> entityList) {
        return entityList != null
                ? entityList.stream().map(this::toDto).collect(Collectors.toList())
                : new ArrayList<>();
    }

    /**
     * Maps an entity to a dto.
     */
    DTO toDto(ENTITY entity);

    // Override only if strictly necessary.
    default List<ENTITY> toEntities(List<DTO> dtoList) {
        return dtoList != null
                ? dtoList.stream().map(this::toEntity).collect(Collectors.toList())
                : new ArrayList<>();
    }

    /**
     * Maps a dto to an entity.
     */
    ENTITY toEntity(DTO dto);
}
