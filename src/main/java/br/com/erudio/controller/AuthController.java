package br.com.erudio.controller;

import br.com.erudio.data.model.User;
import br.com.erudio.data.vo.v1.UserResultVO;
import br.com.erudio.data.vo.v1.UserVO;
import br.com.erudio.exception.UserAlreadyExistException;
import br.com.erudio.repository.UserRepository;
import br.com.erudio.security.AccountCredentialsVO;
import br.com.erudio.security.jwt.JwtTokenProvider;
import br.com.erudio.services.UserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Api(value = "Auth Endpoint", tags = "Authentication")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserServices services;

    @ApiOperation(value = "Authenticate a user by credentials")
    @PostMapping(
            value = "/login",
            produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" }
    )
    public ResponseEntity<Map<?, ?>> login(@RequestBody AccountCredentialsVO data) {
        var username = data.getUsername();
        var password = data.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        var user = services.loadUserByUsername(username);

        var token = tokenProvider.createToken(username, user.getRoles());

        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);

        return ResponseEntity.ok(model);
    }

    @ApiOperation(value = "Authenticate a user by credentials")
    @PostMapping(
            value = "/signin",
            produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" }
    )
    public ResponseEntity<UserResultVO> signin(@Valid @RequestBody UserVO userVO) {
        return ResponseEntity.ok(services.registerNewUserAccount(userVO));
    }

}
