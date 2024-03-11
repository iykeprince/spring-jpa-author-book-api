package com.devtiro.quickstart.repositories;

import com.devtiro.quickstart.TestDataUtil;
import com.devtiro.quickstart.domain.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {


    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository authorDaoImpl){
        this.underTest = authorDaoImpl;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorEntity author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        Optional<AuthorEntity> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {

        AuthorEntity authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);

        Iterable<AuthorEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize( 1)
                .containsExactly( authorB);
    }
//
    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        authorA.setName("Updated");
        underTest.save(authorA);
        Optional<AuthorEntity> result = underTest.findById(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
    }
//
    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        underTest.deleteById(authorA.getId());
        Optional<AuthorEntity> result = underTest.findById(authorA.getId());

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorWithAgeLessThan() {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity testAuthorB = TestDataUtil.createTestAuthorB();
        AuthorEntity testAuthorC = TestDataUtil.createTestAuthorC();

        Iterable<AuthorEntity>  result = underTest.ageLessThan(50);
        assertThat(result).containsExactly(testAuthorB, testAuthorC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity testAuthorB = TestDataUtil.createTestAuthorB();
        AuthorEntity testAuthorC = TestDataUtil.createTestAuthorC();

        Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreaterThan(50);
        assertThat(result).containsExactly(testAuthorA);
    }

}
