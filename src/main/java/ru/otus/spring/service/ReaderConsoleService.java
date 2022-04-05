package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Service
public class ReaderConsoleService implements ReaderService{

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Book readBook() {
        Book book = new Book();
        System.out.println("\nВведите идентификатор книги\n$ ");
        book.setId(scanner.nextLong());
        System.out.println("\nВведите название книги (от 1 до 10)\n$ ");
        book.setTitle(scanner.nextLine());
        System.out.println("\nВведите рейтинг книги (от 1 до 10)\n$ ");
        book.setRating(scanner.nextInt());
        book.setAuthor(readAuthor());
        book.setGenre(readGenre());
        System.out.println("\nВведите количество комментариев\n$ ");
        int commentCount = scanner.nextInt();
        List<Comment> comments = new LinkedList<>();
        for (int i = 0; i < commentCount; ++i) {
            comments.add(readComment());
        }
        book.setComments(comments);
        return book;
    }

    @Override
    public Author readAuthor() {
        Author author = new Author();
        System.out.println("\nВведите идентификатор автора\n$ ");
        author.setId(scanner.nextLong());
        System.out.println("\nВведите имя автора\n$ ");
        author.setName(scanner.nextLine());
        System.out.println("\nВведите фамилию автора\n$ ");
        author.setSurname(scanner.nextLine());
        return author;
    }

    @Override
    public Genre readGenre() {
        Genre genre = new Genre();
        System.out.println("\nВведите идентификатор жанра\n$ ");
        genre.setId(scanner.nextLong());
        System.out.println("\nВведите название жанра\n$ ");
        genre.setName(scanner.nextLine());
        return genre;
    }

    @Override
    public Comment readComment() {
        Comment сomment = new Comment();
        System.out.println("\nВведите идентификатор комментария\n$ ");
        сomment.setId(scanner.nextLong());
        System.out.println("\nВведите автора комментария\n$ ");
        сomment.setAuthor(scanner.nextLine());
        System.out.println("\nВведите содержимое комментария\n$ ");
        сomment.setContent(scanner.nextLine());
        return сomment;
    }
}
