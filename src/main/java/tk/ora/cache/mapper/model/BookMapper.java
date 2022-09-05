package tk.ora.cache.mapper.model;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import tk.ora.cache.domain.dto.BookDto;
import tk.ora.cache.model.Book;

public class BookMapper {

    private static final ModelMapper MAPPER = new ModelMapper();

    static {
        MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static Book bookDtoToDomain(final BookDto dto) {
        return MAPPER.map(dto, Book.class);
    }

    public static BookDto bookDomainToDto(final Book book) {
        return MAPPER.map(book, BookDto.class);
    }
}
