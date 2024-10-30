package Proj.library.mapper;

import Proj.library.dto.AuthorDTO;
import Proj.library.model.Author;
import Proj.library.model.GenericModel;
import Proj.library.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public abstract class AuthorMapper
        extends GenericMapper<Author, AuthorDTO>{

    private final BookRepository bookRepository;

    public AuthorMapper(ModelMapper modelMapper,
                        BookRepository bookRepository) {
        super(Author.class, AuthorDTO.class, modelMapper);
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Author.class, AuthorDTO.class)
                .addMappings(mapping -> mapping.skip(AuthorDTO::setBooksIds)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(AuthorDTO.class, Author.class)
                .addMappings(mapping -> mapping.skip(Author::setBooks)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(AuthorDTO sourse, Author destination) {
        if (!Objects.isNull(sourse.getBooksIds())) {
            destination.setBooks(bookRepository.findAllById(sourse.getBooksIds()));
        } else {
            destination.setBooks(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFIelds(Author sourse, AuthorDTO destination) {
        destination.setBooksIds(getIds(sourse));
    }

    @Override
    protected List<Long> getIds(Author sourse) {
        return Objects.isNull(sourse) || Objects.isNull(sourse.getBooks())
                ? Collections.emptyList()
                : sourse.getBooks().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
