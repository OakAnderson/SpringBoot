package br.com.erudio.controller;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.data.vo.PersonVOV2;
import br.com.erudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping
    public List<PersonVO> findAll() {

        return services.findAll();
    }

    @GetMapping("/{id}")
    public PersonVO findById(@PathVariable Long id) {
        var entity = services.findById(id);
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person) {
        return services.create(person);
    }

    @PostMapping("/v2")
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
        return services.createV2(person);
    }


    @PutMapping
    public PersonVO update(@RequestBody PersonVO person) {
        return services.update(person);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id) {
        services.delete(id);
    }

}
