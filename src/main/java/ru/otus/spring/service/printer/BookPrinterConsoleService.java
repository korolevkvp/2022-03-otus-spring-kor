package ru.otus.spring.service.printer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.service.BookService;

@Service
@RequiredArgsConstructor
public class BookPrinterConsoleService implements BookPrinterService {

    private final BookService bookService;

    @Override
    @Transactional(readOnly = true)
    public void findAll() {
        System.out.println(bookService.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public void findById(Long id) {
        try {
            System.out.println(bookService.findById(id));
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    @Transactional
    public void updateById(Long id, Book book) {
        System.out.println(bookService.updateById(id, book));
    }

    @Override
    @Transactional
    public void create(Book book) {
        System.out.println(bookService.create(book));
    }
}
