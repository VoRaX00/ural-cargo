package ural.ru.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ural.ru.dto.PageDto;
import ural.ru.dto.PaginatedParamsDto;
import ural.ru.models.PageModel;
import ural.ru.models.PaginatedParamsModel;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaginatedMapper {

    PaginatedParamsModel toModel(PaginatedParamsDto paramsDto);

    default <T, R> PageDto<R> toPageDto(PageModel<T> source, Function<T, R> itemsMapper) {
        if (source == null) {
            return null;
        }

        List<R> mappedItems = Optional.ofNullable(source.getItems())
                .orElse(List.of())
                .stream()
                .map(itemsMapper)
                .toList();

        return PageDto.<R>builder()
                .currentPageNumber(source.getCurrentPageNumber())
                .itemsOnPage(source.getItemsOnPage())
                .totalPageCount(source.getTotalPageCount())
                .totalResultCount(source.getTotalResultCount())
                .items(mappedItems)
                .build();
    }

}
