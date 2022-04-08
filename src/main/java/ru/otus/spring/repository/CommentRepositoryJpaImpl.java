package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {


    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete " +
                "from Comment a " +
                "where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c ", Comment.class).getResultList();
    }

    @Override
    public List<Comment> findByAuthor(String author) {
        TypedQuery<Comment> query = em.createQuery("select c " +
                        "from Comment c " +
                        "where c.author = :author",
                Comment.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public List<Comment> saveAll(List<Comment> comments) {
        List<Comment> result = new LinkedList<>();
        comments.forEach(comment -> result.add(save(comment)));
        return result;
    }
}
