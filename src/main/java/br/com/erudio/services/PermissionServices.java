package br.com.erudio.services;

import br.com.erudio.data.model.Permission;
import br.com.erudio.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServices {

    @Autowired
    PermissionRepository repository;

    public Permission getPermissionById( Long id ) {
        return repository.getById(id);
    }

}
