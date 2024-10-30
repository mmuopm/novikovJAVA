package Proj.library.controller;

import Proj.library.model.Author;
import Proj.library.model.Book;
import Proj.library.repository.AuthorRepository;
import Proj.library.repository.BookRepository;
import Proj.library.repository.GenericRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping("/books")
@Tag(name = "Книги", description = "Контроллер для работы с книгами из библиотеки")
public class BookController extends GenericController<Book>{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    protected BookController(GenericRepository<Book> genericRepository, BookRepository bookRepository,
                             AuthorRepository authorRepository) {
        super(genericRepository);
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Operation(description = "Добавить автора к книге")
    @RequestMapping(value = "/addAuthor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> addAuthor(@RequestParam(value = "bookId") Long bookId,
                                            @RequestParam(value = "authorId") Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Книга не найдена"));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("Автор не найден"));
        book.getAuthors().add(author);
        return ResponseEntity.status(HttpStatus.OK).body(authorRepository.save(author));
    }
}
