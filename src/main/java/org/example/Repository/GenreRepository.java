package org.example.repository;

import org.example.entities.Genre;

public class GenreRepository extends AbstractRepository<Genre, Integer> {

    public GenreRepository(){
        super();
    }
    @Override
    protected Class<Genre> getEntityClass() {
        return Genre.class;
    }

    @Override
    protected String getFindByNameQueryName() {
        return "Genre.findByName";
    }
}
