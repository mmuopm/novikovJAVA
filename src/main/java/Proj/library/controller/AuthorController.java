package Proj.library.controller;

import Proj.library.model.Author;
import Proj.library.repository.GenericRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@Tag(name = "Авторы", description = "Контроллер для работы с авторами из библиотеки")
public class AuthorController extends GenericController<Author> {
    public AuthorController(GenericRepository<Author> genericRepository) {
        super(genericRepository);
    }
}
