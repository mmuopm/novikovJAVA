package Proj.library.mapper;

import Proj.library.dto.GenericDTO;
import Proj.library.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {
    
    E toEntity(D dto); //метод, преобразующий из DTO в сущность 
    
    D toDTO(E entity); //метод, преобразущий из сущности в DTO

    List<E> toEntities(List<D> dtos);
    
    List<D> toDTOs(List<E> entites);
}
