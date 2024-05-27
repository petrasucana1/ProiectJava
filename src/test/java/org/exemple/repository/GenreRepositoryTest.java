package org.exemple.repository;

import org.example.LoggerConfig;
import org.example.repository.GenreRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.example.entities.Genre;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GenreRepositoryTest {

    private EntityManager entityManager;
    private GenreRepository genreRepository;

    @Before
    public void setUp() {
        genreRepository = new GenreRepository();
        entityManager=genreRepository.getEntityManager();
    }

    @After
    public void tearDown() {
        entityManager.close();
    }

    @Test
    public void create() {
        LoggerConfig.getLogger().info("Test started");

        Genre genre = new Genre("Tst1 Genre");
        genreRepository.create(genre);


        Genre retrievedGenre = entityManager.find(Genre.class, genre.getId());

        assertNotNull(retrievedGenre);

        assertEquals("Tst1 Genre", retrievedGenre.getName());
    }

    @Test
    public void findById() {
        LoggerConfig.getLogger().info("Test started");

        Genre genre = new Genre("Tst2 Genre");
        entityManager.getTransaction().begin();
        entityManager.persist(genre);
        entityManager.getTransaction().commit();

        Genre foundGenre = genreRepository.findById(genre.getId());

        assertNotNull(foundGenre);

        assertEquals("Tst2 Genre", foundGenre.getName());
    }

    @Test
    public void findByName() {
        LoggerConfig.getLogger().info("Test started");

        Genre genre1 = new Genre("Test Genre 1");
        Genre genre2 = new Genre("Test Genre 2");
        entityManager.getTransaction().begin();
        entityManager.persist(genre1);
        entityManager.persist(genre2);
        entityManager.getTransaction().commit();

        List<Genre> genres = genreRepository.findByName("Test");

        assertNotNull(genres);
        assertEquals(2, genres.size());
    }
}
