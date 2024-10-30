package Proj.library.mapper;

import Proj.library.dto.AuthorDTO;
import Proj.library.dto.GenericDTO;
import Proj.library.model.Author;
import Proj.library.model.GenericModel;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public abstract class GenericMapper<E extends GenericModel, D extends GenericDTO>
        implements Mapper<E, D>{

    private final Class<E> entityClass;
    private final Class<D> dtoClass;
    protected final ModelMapper modelMapper;

    public GenericMapper(Class<E> entityClass,
                         Class<D> dtoClass,
                         ModelMapper modelMapper) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.modelMapper = modelMapper;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : modelMapper.map(dto, entityClass);
    }

    @Override
    public D toEntity(E entity) {
        return Objects.isNull(entity)
                ? null
                : modelMapper.map(entity, dtoClass );
    }

    @Override
    public List<E> toEntities(List<D> dtos) {return dtos.stream().map(this::toEntity).toList();}

    @Override
    public List<D> toDTOs(List<E> entities) {return entities.stream().map(this::toDTO).toList();}

    protected Converter<D,E> toEntityConverter() {
        return context -> {
            D sourse = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(sourse, destination);
            return context.getDestination();
        };
    }

    protected Converter<E, D> toDTOConverter() {
        return context -> {
            E sourse = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(sourse, destination);
            return context.getDestination();
        };
    }

    protected abstract void mapSpecificFields(D sourse, E destinaion);
    protected abstract void mapSpecificFields(E sourse, D destinaion);

    @PostConstruct
    protected abstract void setupMapper();
    protected abstract List<Long> getIds(E entity);

    protected abstract void mapSpecificFIelds(Author sourse, AuthorDTO destination);
}
