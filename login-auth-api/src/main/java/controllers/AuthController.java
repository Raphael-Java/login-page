package controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import domain.User;
import dto.LoginRequestDTO;
import dto.RegisterRequestDTO;
import dto.ResponseDTO;
import infra.TokenService;
import repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UserRepository repository,
                          PasswordEncoder passwordEncoder,
                          TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        User user = repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            return ResponseEntity.badRequest().build();
        }

        String token = tokenService.generatedToken(user);

        return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = repository.findByEmail(body.email());

        if (user.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User newUser = new User();
        
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setPassword(passwordEncoder.encode(body.password()));

        repository.save(newUser);

        String token = tokenService.generatedToken(newUser);

        return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
    }
}