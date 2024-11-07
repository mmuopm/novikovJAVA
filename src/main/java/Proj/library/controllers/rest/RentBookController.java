package Proj.library.controllers.rest;


import Proj.library.dto.BookRentInfoDTO;
import Proj.library.model.BookRentInfo;
import Proj.library.service.BookRentInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rent/info")
@Tag(name = "Аренда книг",
        description = "Контроллер для работы с арендой/выдачей книг пользователям библиотеки")
public class RentBookController
        extends GenericController<BookRentInfo, BookRentInfoDTO> {

    public RentBookController(BookRentInfoService bookRentInfoService ) {
        super(bookRentInfoService);
    }
}
