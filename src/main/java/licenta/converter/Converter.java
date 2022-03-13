package licenta.converter;


import licenta.dto.BaseDto;
import licenta.model.BaseEntity;

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}
