package br.com.erudio.controller;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(produces = { "application/json", "application/xml" })
    public List<PersonVO> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
    public PersonVO findById(@PathVariable Long id) {
        return services.findById(id);
    }

    @PostMapping(
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" }
    )
    public PersonVO create(@RequestBody PersonVO person) {
        return services.create(person);
    }

    @PutMapping(
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" }
    )
    public PersonVO update(@RequestBody PersonVO person) {
        return services.update(person);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id) {
        services.delete(id);
    }

}
