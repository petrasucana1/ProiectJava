package org.exemple.repository;

import org.example.LoggerConfig;
import org.example.repository.AuthorRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import org.example.entities.Author;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuthorRepositoryTest {

    private EntityManager entityManager;
    private AuthorRepository authorRepository;

    @Before
    public void setUp() {
        authorRepository = new AuthorRepository();
        entityManager=authorRepository.getEntityManager();
    }

    @After
    public void tearDown() {
        entityManager.close();
    }

    @Test
    public void create() {
        LoggerConfig.getLogger().info("Test started");

        Author author = new Author("Test Author");
        authorRepository.create(author);

        Author retrievedAuthor = entityManager.find(Author.class, author.getId());

        assertNotNull(retrievedAuthor);

        assertEquals("Test Author", retrievedAuthor.getName());
    }

    @Test
    public void findById() {
        LoggerConfig.getLogger().info("Test started");

        Author author = new Author("Test Author");
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();

        Author foundAuthor = authorRepository.findById(author.getId());

        assertNotNull(foundAuthor);

        assertEquals("Test Author", foundAuthor.getName());
    }

    @Test
    public void findByName() {
        LoggerConfig.getLogger().info("Test started");

        Author author1 = new Author("Test Author 1");
        Author author2 = new Author("Test Author 2");
        entityManager.getTransaction().begin();
        entityManager.persist(author1);
        entityManager.persist(author2);
        entityManager.getTransaction().commit();

        List<Author> authors = authorRepository.findByName("Test");

        assertNotNull(authors);
    }
}

