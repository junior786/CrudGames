package com.junior.CRUD.service;

import com.junior.CRUD.exceptions.ResourceNotFoundException;
import com.junior.CRUD.model.Games;
import com.junior.CRUD.repository.GameRepository;
import com.junior.CRUD.utils.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    public ResponseEntity<?> getAll(Pageable page) {

        Iterable<Games> all = gameRepository.findAll(page);
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<?> save(Games game) {
            gameRepository.save(game);
            return ResponseEntity.ok(game);
    }

    public ResponseEntity<?> update(long id, Games games) {
        Optional<Games> game = gameRepository.findById(id);
        if (game.isEmpty()) {
            throw new ResourceNotFoundException("game not found ID: " + id);
        }
            games.setId(id);
            gameRepository.save(games);
            return ResponseEntity.ok(game.get());
    }

    public ResponseEntity<?> remove(long id) {
        Optional<Games> game = gameRepository.findById(id);
        if (game.isEmpty()) {
            throw new ResourceNotFoundException("game not found ID: " + id);
        }
        gameRepository.delete(game.get());
        return ResponseEntity.ok(game.get());
    }

    public ResponseEntity<?> findId(long id) {
        Optional<Games> game = gameRepository.findById(id);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        }
        throw new ResourceNotFoundException("game not found ID: " + id);
    }

    public ResponseEntity<?> findBydesctiption(Pageable pageable, String description) {
        return ResponseEntity.ok(gameRepository.searchDescriptionLike(pageable, description));
    }

    public ResponseEntity<?> findByName(Pageable pageable, String name) {
        return ResponseEntity.ok(gameRepository.searchNameLike(pageable, name));
    }
}
