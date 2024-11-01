package Proj.library.controller;


import Proj.library.dto.AuthorDTO;
import Proj.library.model.BookRentInfo;
import Proj.library.repository.BookRentInfoRepository;
import Proj.library.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rent/info")
@Tag(name = "Аренда книг",
        description = "Контроллер для работы с арендой/выдачей книг пользователям библиотеки")
public class RentBookController
        extends GenericController<BookRentInfo, BookRentInfoDTO> {
    public RentBookController(BookRentInfoRepository genericRepository) {
        super(genericRepository);
    }


}
