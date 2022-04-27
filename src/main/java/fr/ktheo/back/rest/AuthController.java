package fr.ktheo.back.rest;


import fr.ktheo.back.model.User;
import fr.ktheo.back.model.payload.*;
import fr.ktheo.back.repository.UserRepository;
import fr.ktheo.back.security.jwt.JwtResponse;
import fr.ktheo.back.security.jwt.JwtUtils;
import fr.ktheo.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController("AuthController")
@RequestMapping("/api/auth")
@CrossOrigin(value = "*")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody SigninRequest dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String tokenJwtGenerated = jwtUtils.generateJwtToken(authentication);
        User user = (User) authentication.getPrincipal();
        List<String>roles = user.getAuthorities().stream().map(item->item.getAuthority()).collect(Collectors.toList());
        return ResponseEntity
                .ok(new JwtResponse(
                        user.getId(),
                        user.getUsername(),
                        tokenJwtGenerated,
                        roles
                ));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?>checkPassword(@Valid @RequestBody String currentPassword, @PathVariable long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("id  " + id + " not found"));
    if(passwordEncoder.matches(currentPassword,user.getPassword())){
        return ResponseEntity.ok(
                true);
    }else{
        return ResponseEntity.ok(
                false
        );
    }
    }


}
