package br.com.erudio.controller;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    private BookServices services;

    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public List<BookVO> findAll() {
        List<BookVO> books = services.findAll();
        books.forEach(
                book -> book.add(linkTo(methodOn(BookController.class)
                    .findById(book.getKey()))
                    .withSelfRel()
                )
        );

        return books;
    }

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO findById( @PathVariable Long id ) {
        BookVO bookVO = services.findById(id);
        bookVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return bookVO;
    }

    @PostMapping(
            produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" }
    )
    public BookVO create( @RequestBody BookVO book ) {
        BookVO bookVO = services.create(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());

        return bookVO;
    }

    @PutMapping(
            produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" }
    )
    public BookVO update(@RequestBody BookVO book) {
        BookVO bookVO = services.update(book);
        bookVO.add(linkTo(methodOn(PersonController.class).findById(bookVO.getKey())).withSelfRel());

        return bookVO;
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id) {
        services.delete(id);
    }
}
