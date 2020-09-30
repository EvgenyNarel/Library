package java.service.impl;

import model.Book;
import repository.abstracts.EntityRepository;
import repository.api.BookRepository;
import repository.impl.BookRepositoryImpl;
import service.api.BookService;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl() {
        this.bookRepository = new BookRepositoryImpl();
    }

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public EntityRepository<Book> getRepository() {
        return this.bookRepository;
    }
}
