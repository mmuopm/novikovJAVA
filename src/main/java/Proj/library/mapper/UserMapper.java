package Proj.library.mapper;

import Proj.library.dto.UserDTO;
import Proj.library.model.BookRentInfo;
import Proj.library.model.GenericModel;
import Proj.library.model.User;
import Proj.library.repository.BookRentInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public abstract class UserMapper //без abstract не работает
        extends GenericMapper<User, UserDTO> {

    private final BookRentInfoRepository bookRentInfoRepository;

    protected UserMapper(ModelMapper modelMapper,
                         BookRentInfoRepository bookRentInfoRepository) {
        super(User.class, UserDTO.class, modelMapper);
        this.bookRentInfoRepository = bookRentInfoRepository;
    }

    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setUserBooksRent)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(m -> m.skip(User::setBookRentInfos)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(UserDTO source, User destination) {
        if (!Objects.isNull(source.getUserBooksRent())) {
            destination.setBookRentInfos(bookRentInfoRepository.findAllById(source.getUserBooksRent()));
        } else {
            destination.setBookRentInfos(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(User source, UserDTO destination) {
        destination.setUserBooksRent(getIds(source));
    }

    @Override
    protected List<Long> getIds(User entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getBookRentInfos())
                ? null
                : entity.getBookRentInfos().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
