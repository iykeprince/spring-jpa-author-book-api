package com.devtiro.quickstart.repositories;

import com.devtiro.quickstart.TestDataUtil;
import com.devtiro.quickstart.domain.entities.AuthorEntity;
import com.devtiro.quickstart.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {
    private BookRepository underTest;
    private AuthorRepository authorDao;

    @Autowired
    public BookRepositoryIntegrationTest(BookRepository underTest, AuthorRepository authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;

    }

    @Test
    public void testThatBookWasCreatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthorA();
        BookEntity book = TestDataUtil.createTestBookEntity(author);
        underTest.save(book);
        Optional<BookEntity> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthorB();
        BookEntity bookA = TestDataUtil.createTestBookEntity(author);
        underTest.save(bookA);
        Iterable<BookEntity> result = underTest.findAll();

        assertThat(result)
                .hasSize(1)
                .containsExactly(bookA);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorEntity author = TestDataUtil.createTestAuthorA();
        BookEntity book = TestDataUtil.createTestBookEntity(author);
        underTest.save(book);

        book.setTitle("UPdated");
        underTest.save(book);

        Optional<BookEntity> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
//
    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity author = TestDataUtil.createTestAuthorA();
        BookEntity book = TestDataUtil.createTestBookEntity(author);
        underTest.save(book);

        underTest.deleteById(book.getIsbn());
        Optional<BookEntity> result = underTest.findById(book.getIsbn());
        assertThat(result).isEmpty();
    }
}
