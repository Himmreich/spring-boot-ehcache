package tk.ora.cache.repo;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import tk.ora.cache.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitle(@NonNull String title);
}
