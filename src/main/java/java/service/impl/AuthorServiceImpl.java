package java.service.impl;

import model.Author;
import repository.abstracts.EntityRepository;
import repository.api.AuthorRepository;
import repository.impl.AuthorRepositoryImpl;
import service.api.AuthorService;

public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl() {
        this.authorRepository = new AuthorRepositoryImpl();
    }

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public EntityRepository<Author> getRepository() {
        return this.authorRepository;
    }
}