package Proj.library.service;

import Proj.library.dto.BookRentInfoDTO;
import Proj.library.mapper.BookRentInfoMapper;
import Proj.library.model.BookRentInfo;
import Proj.library.repository.BookRentInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class BookRentInfoService
        extends GenericService<BookRentInfo, BookRentInfoDTO> {
    protected BookRentInfoService(BookRentInfoRepository bookRentInfoRepository,
                                  BookRentInfoMapper bookRentInfoMapper) {
        super(bookRentInfoRepository, bookRentInfoMapper);
    }
}
