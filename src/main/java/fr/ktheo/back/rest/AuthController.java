package fr.ktheo.back.rest;


import fr.ktheo.back.model.User;
import fr.ktheo.back.rest.payload.MessageResponse;
import fr.ktheo.back.rest.payload.SignupRequest;
import fr.ktheo.back.security.jwt.JwtUtils;
import fr.ktheo.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("AuthController")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest dto) {

        boolean userAlreadyExist = userService.checkUsernameExist(dto.getUsername());

        if (userAlreadyExist) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("User already exist"));
        }
        User createdUser = userService.signup(dto.getUsername(),dto.getMail(), dto.getPassword());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("User registered succesfully"));
    }


}
