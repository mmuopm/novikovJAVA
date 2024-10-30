package Proj.library.mapper;

import Proj.library.dto.GenericDTO;
import Proj.library.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO {
    E toEntity(D dto);
    D toDTO(E entity);

    D toEntity(E entity);

    List<E> toEntities(List<D> dtos);
    List<D> toDTOs(List<E> entites);
}
