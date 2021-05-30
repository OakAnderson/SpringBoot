package br.com.erudio.converter.mocks;

import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockBook {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<>();

        for( int i = 0; i < 14; i++ ) {
            var book = mockEntity(i);
            books.add(book);
        }

        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();

        for( int i = 0; i < 14; i++ ) {
            var book = mockVO(i);
            books.add(book);
        }

        return books;
    }

    private Book mockEntity(Integer number) {
        Book book = new Book();

        book.setAuthor("Author test" + number);
        book.setTitle("Title test" + number);
        book.setPrice(50.5 + number);
        book.setLaunchDate(OffsetDateTime.now());
        book.setId(number.longValue());

        return book;
    }

    private BookVO mockVO(Integer number) {
        BookVO book = new BookVO();

        book.setAuthor("Author test" + number);
        book.setTitle("Title test" + number);
        book.setPrice(50.5 + number);
        book.setLaunchDate(OffsetDateTime.now());
        book.setKey(number.longValue());

        return book;
    }

}
