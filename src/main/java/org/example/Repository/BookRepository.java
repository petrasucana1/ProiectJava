package org.example.repository;

import org.example.entities.Book;

import javax.transaction.Transactional;
import java.util.List;

public class BookRepository extends AbstractRepository<Book, Integer> {

    public BookRepository(){
        super();
    }
    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    protected String getFindByNameQueryName() {
        return "Book.findByName";
    }

    @Transactional
    public List<Book> selectAllBooks() {
        String query = "SELECT b FROM Book b";
        return entityManager.createQuery(query, Book.class).getResultList();
    }

}
