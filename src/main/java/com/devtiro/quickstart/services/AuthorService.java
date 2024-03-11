package com.devtiro.quickstart.services;

import com.devtiro.quickstart.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public AuthorEntity save(AuthorEntity author);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity partialUpdat(Long id, AuthorEntity authorEntity);

    void delete(Long id);
}
