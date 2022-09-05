package tk.ora.cache.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.ora.cache.domain.dto.BookDto;
import tk.ora.cache.mapper.model.BookMapper;
import tk.ora.cache.model.Book;
import tk.ora.cache.repo.BookRepository;



@Service
@Slf4j
@CacheConfig(cacheNames = {"book-cache-data"})
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Cacheable
    public BookDto findBookByTitle(@NonNull final String title) {
        return BookMapper.bookDomainToDto(bookRepository.findBookByTitle(title));
    }

    public void saveBook(@NonNull Book book) {
        bookRepository.save(book);
    }
}
