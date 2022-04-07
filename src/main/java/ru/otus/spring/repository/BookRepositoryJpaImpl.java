package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-author-genre-entity-graph");

        TypedQuery<Book> query = em.createQuery("select b from " +
                        "Book b left join fetch b.comments", Book.class);

        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Book> findByTitle(String title) {
        TypedQuery<Book> query = em.createQuery("select b " +
                        "from Book b " +
                        "where b.title = :title",
                Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public void updateTitleById(long id, String title) {
        Query query = em.createQuery("update Book b " +
                "set b.title = :title " +
                "where b.id = :id");
        query.setParameter("title", title);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
