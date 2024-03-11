package com.devtiro.quickstart;

import com.devtiro.quickstart.domain.dto.AuthorDto;
import com.devtiro.quickstart.domain.dto.BookDto;
import com.devtiro.quickstart.domain.entities.AuthorEntity;
import com.devtiro.quickstart.domain.entities.BookEntity;

public final class TestDataUtil {
    private TestDataUtil(){}


    public static AuthorDto createTestAuthorDto() {
        return AuthorDto.builder()
                .id(1L)
                .name("Abigail Rose T")
                .age(60)
                .build();
    }
    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(90)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .id(1L)
                .name("James Cook")
                .age(44)
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .id(1L)
                .name("David Knight")
                .age(25)
                .build();
    }

    public static BookEntity createTestBookEntity(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("9839-2392-2389")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto author) {
        return BookDto.builder()
                .isbn("9839-2392-2389")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static BookDto createTestBookDtoB(final AuthorDto author) {
        return BookDto.builder()
                .isbn("9839-2392-2389")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }
}
