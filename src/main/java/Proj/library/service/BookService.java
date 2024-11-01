package Proj.library.service;

import Proj.library.dto.BookDTO;
import Proj.library.mapper.BookMapper;
import Proj.library.model.Author;
import Proj.library.model.Book;
import Proj.library.repository.AuthorRepository;
import Proj.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class BookService
        extends GenericService<Book, BookDTO> {
    private final AuthorRepository authorRepository;

    protected BookService(BookRepository repository,
                          BookMapper mapper,
                          AuthorRepository authorRepository) {
        super(repository, mapper);
        this.authorRepository = authorRepository;
    }

    public BookDTO addAuthor(final Long bookId,
                             final Long authorId) {
        BookDTO book = getOne(bookId);
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("Автор не найден"));
        book.getAuthorIds().add(author.getId());
        update(book);
        return book;
    }
}
