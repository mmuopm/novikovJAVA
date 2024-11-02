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


//@param <E> - сущность, с которой мы работаем
//@param <D> - сущность, которую мы будем отдавать/принимать далее
@Component
public abstract class GenericMapper<E extends GenericModel, D extends GenericDTO>
        implements Mapper<E, D>{

    //внедряем то, с чем будем работать
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

    //описываем логику методов, обозначаеных в интерфейсе Mapper
    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : modelMapper.map(dto, entityClass);
    }

    @Override
    public D toDTO(E entity) {
        return Objects.isNull(entity)
                ? null
                : modelMapper.map(entity, dtoClass);
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

    //маппинг нестандартных полей
    protected abstract void mapSpecificFields(D source, E destination);
    protected abstract void mapSpecificFields(E source, D destination);

    //настройка маппера (что делать и что вызывать в случае несовпадения типов данных сорса/дестинейшена)
    @PostConstruct
    protected abstract void setupMapper();
    protected abstract List<Long> getIds(E entity);

    //protected abstract void mapSpecificFields(Author source, AuthorDTO destination);
}
