package Proj.library.mapper;

import Proj.library.dto.BookDTO;
import Proj.library.model.Book;
import Proj.library.model.GenericModel;
import Proj.library.repository.AuthorRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public abstract class BookMapper
        extends GenericMapper<Book, BookDTO> {
    private final AuthorRepository authorRepository;

    protected BookMapper(ModelMapper mapper, AuthorRepository authorRepository) {
        super(Book.class, BookDTO.class, mapper);
        this.authorRepository=authorRepository;
    }

    @PostConstruct
    @Override
    public void setupMapper() {
        modelMapper.createTypeMap(Book.class, BookDTO.class)
                .addMapping(m -> m.skip(BookDTO::setAuthorIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(BookDTO.class, Book.class)
                .addMapping(m -> m.skip(Book::setAuthors)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(BookDTO source, Book destination) {
        if (!Objects.isNull(source.getAuthorIds())) {
            destination.setAuthors(authorRepository.findAllById(source.getAuthorIds()));
        } else {
            destination.setAuthors(Collections.emptyList());
        }
    }

    @Override
    protected void  mapSpecificFields(Book source, BookDTO destination) {
        destination.setAuthorIds(getIds(source));
    }

    @Override
    protected List<Long> getIds(Book book) {
        return Objects.isNull(book) || Objects.isNull(book.getAuthors())
                ? null
                : book.getAuthors().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
