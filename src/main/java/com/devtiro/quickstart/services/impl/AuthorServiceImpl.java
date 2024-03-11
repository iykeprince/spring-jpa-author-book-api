package com.devtiro.quickstart.services.impl;

import com.devtiro.quickstart.domain.entities.AuthorEntity;
import com.devtiro.quickstart.repositories.AuthorRepository;
import com.devtiro.quickstart.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity save(AuthorEntity author) {
       return authorRepository.save(author);
    }

    @Override
    public List<AuthorEntity> findAll() {
      return StreamSupport.stream(authorRepository
              .findAll()
              .spliterator(),
                      false)
              .collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
       return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity partialUpdat(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);

        return authorRepository.findById(id).map(existingAuthor -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author does not exits"));
    }

    @Override
    public void delete(Long id) {
       authorRepository.deleteById(id);
    }
}
