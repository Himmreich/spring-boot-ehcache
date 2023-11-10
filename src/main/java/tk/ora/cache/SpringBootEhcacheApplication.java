package tk.ora.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.ora.cache.domain.dto.BookDto;
import tk.ora.cache.mapper.model.BookMapper;
import tk.ora.cache.model.Book;
import tk.ora.cache.repo.BookRepository;
import tk.ora.cache.service.BookService;

import java.time.Duration;

@Slf4j
@SpringBootApplication
public class SpringBootEhcacheApplication implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEhcacheApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BookDto newBookDto = new BookDto();
        newBookDto.setAuthor("Marat Gabidullin");
        newBookDto.setTitle("Moi, Marat, ex-commandant de l\'armée Wagner - Au coeur de l\'armée de Vladimir Poutine");
        newBookDto.setYear(2002);
        Book detachedBook = BookMapper.bookDtoToDomain(newBookDto);
        bookService.saveBook(detachedBook);

        //Try cache
        log.info("Load object from database...");
        log.info(bookService.findBookByTitle("Mémoires vives").toString());
        log.info("Wait 3 seconds....");
        Thread.sleep(Duration.ofSeconds(3).toMillis());
        log.info("Reload object from database...(from cache in fact...)");
        log.info(bookService.findBookByTitle("Mémoires vives").toString());
        log.info("Done");
    }
}
