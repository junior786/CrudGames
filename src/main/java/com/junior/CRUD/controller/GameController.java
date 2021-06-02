package com.junior.CRUD.controller;

import com.junior.CRUD.model.Games;
import com.junior.CRUD.service.GameService;
import com.junior.CRUD.utils.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping(value = "/games")
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping()
    public ResponseEntity<?> getAll(@PageableDefault(size = 6) Pageable pageable) {
        return gameService.getAll(pageable);
    }

    @PostMapping()
    public ResponseEntity<?> post(@Valid @RequestBody Games game) {
        if (Verification.verificationGames(game)) {
            return gameService.save(game);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> edit(@PathVariable long id, @Valid @RequestBody Games games) {
        return gameService.update(id, games);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return gameService.findId(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        return gameService.remove(id);
    }

    @GetMapping(value = "/description={description}")
    public ResponseEntity<?> findByDesctiption(@PathVariable String description, @PageableDefault(size = 6) Pageable pageable) {
        return gameService.findBydesctiption(pageable, description);
    }

    @GetMapping(value = "/name={name}")
    public ResponseEntity<?> findByName(@PathVariable String name, @PageableDefault(size = 6) Pageable pageable) {
        return gameService.findByName(pageable, name);
    }
}
