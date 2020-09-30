package java.service.impl;

import model.Genre;
import repository.abstracts.EntityRepository;
import repository.api.GenreRepository;
import repository.impl.GenreRepositoryImpl;
import service.api.GenreService;

public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;

    public GenreServiceImpl() {
        this.genreRepository = new GenreRepositoryImpl();
    }

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public EntityRepository<Genre> getRepository() {
        return this.genreRepository;
    }
}
