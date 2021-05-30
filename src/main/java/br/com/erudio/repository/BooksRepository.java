package br.com.erudio.repository;

import br.com.erudio.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository  extends JpaRepository<Book, Long> {

}
