package br.com.erudio.services;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Permission;
import br.com.erudio.data.model.User;
import br.com.erudio.data.vo.v1.UserResultVO;
import br.com.erudio.data.vo.v1.UserVO;
import br.com.erudio.exception.UserAlreadyExistException;
import br.com.erudio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    PermissionServices permissionServices;

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Username " + username + " not found."));
    }


    public UserResultVO registerNewUserAccount(UserVO userVO) {
        if ( emailExist(userVO.getEmail()) ) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userVO.getEmail());
        }

        if ( usernameExist(userVO.getUsername()) ) {
            throw new UserAlreadyExistException("The username " + userVO.getUsername() + " is already in use");
        }

        String password = new BCryptPasswordEncoder(16).encode(userVO.getPassword());

        User user = new User();
        user.setUserName(userVO.getUsername());
        user.setFullName(String.format("%s %s", userVO.getFirstName(), userVO.getLastName()));
        user.setEmail(userVO.getEmail());
        user.setNewAccount();
        user.setPermissions(Collections.singletonList(permissionServices.getPermissionById(3L)));
        user.setPassword(password);

        return DozerConverter.parseObject(repository.save(user), UserResultVO.class);
    }

    private boolean emailExist(String email) {
        return repository.findByEmail(email).isPresent();
    }

    private  boolean usernameExist(String username) {
        return repository.findByUserName(username).isPresent();
    }
}
