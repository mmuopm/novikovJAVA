package Proj.library.service;


import Proj.library.dto.AuthorDTO;
import Proj.library.mapper.AuthorMapper;
import Proj.library.model.Author;
import Proj.library.model.Book;
import Proj.library.repository.AuthorRepository;
import Proj.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.concurrent.atomic.LongAccumulator;

@Service
public class AuthorService
        extends GenericService<Author, AuthorDTO> {

    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository,
                         AuthorMapper authorMapper,
                         BookRepository bookRepository) {
        super(authorRepository, authorMapper);
        this.bookRepository = bookRepository;
    }

    public AuthorDTO addBook(Long bookId,
                             Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Книга не найдена!"));
        AuthorDTO author = getOne(authorId);
        author.getBooksIds().add(book.getId());
        update(author);
        return author;
    }
}
