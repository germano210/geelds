package br.com.pokemon.pokedex_api.controller;

import br.com.pokemon.pokedex_api.dto.AuthenticationDTO;
import br.com.pokemon.pokedex_api.dto.LoginResponseDTO;
import br.com.pokemon.pokedex_api.dto.RegisterDTO;
import br.com.pokemon.pokedex_api.model.User;
import br.com.pokemon.pokedex_api.repository.UserRepository;
import br.com.pokemon.pokedex_api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        // cria o cookie sem acesso ao js e com s desligado
        org.springframework.http.ResponseCookie cookie = org.springframework.http.ResponseCookie.from("jwtToken", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(2 * 60 * 60)
                .sameSite("Strict")
                .build();
        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Login realizado com sucesso!");
    }

    // REGISTRO
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
        // Verifica se o e-mail já existe
        if (this.repository.findByEmail(data.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Erro: Este e-mail já está em uso.");
        }

        // Criptografa e salva o novo usuário
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        User newUser = new User();
        newUser.setUsername(data.getUsername());
        newUser.setEmail(data.getEmail());
        newUser.setPassword(encryptedPassword);

        this.repository.save(newUser);

        // Gera o token para o usuário que acabou de ser criado pra logica do autologin ao registrar
        var token = tokenService.generateToken(newUser);

        // Coloca o token no cookie do navegador
        org.springframework.http.ResponseCookie cookie = org.springframework.http.ResponseCookie.from("jwtToken", token)
                .httpOnly(true)
                .secure(false) // setta true quando por https
                .path("/")
                .maxAge(2 * 60 * 60)
                .sameSite("Strict")
                .build();

        //Devolve a resposta já mandando o Cookie embutido
        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Usuário criado e logado com sucesso!");
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        // Pega o usuário que foi validado
        var authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        // Verifica se realmente tem alguém logado
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(401).build();
        }

        User user = (User) authentication.getPrincipal();

        // Devolve apenas as informações pro react
        return ResponseEntity.ok(java.util.Map.of(
                "username", user.getUsername(),
                "email", user.getEmail(),
                "darkMode", user.getDarkMode(),
                "realMode", user.getRealMode()
        ));
    }
}