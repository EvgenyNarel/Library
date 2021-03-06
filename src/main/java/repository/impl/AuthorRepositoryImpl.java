package repository.impl;

import model.Author;
import repository.api.AuthorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    @Override
    public long add(Author entity) {
        String sql = "INSERT INTO author(name, surname) VALUES (?, ?)";
        long id;
        ResultSet generatedKeys = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            if (preparedStatement.executeUpdate() == 0) {
                throw new RuntimeException("Creating the author is failed, no rows is affected");
            }
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
                entity.setId(id);
            } else {
                throw new RuntimeException("The author's key isn't fetched from database");
            }
        } catch (SQLException e) {
            throw new RuntimeException("The author wasn't added");
        } finally {
            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return id;
    }

    @Override
    public Author getById(long id) {
        String sql = "SELECT id, name, surname FROM author WHERE id = " + id;
        Author author = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                author = Author.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return author;
    }

    @Override
    public List<Author> getAll() {
        String sql = "SELECT id, name, surname FROM author";
        List<Author> authors = new ArrayList<>();
        Author author;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                author = Author.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .build();
                authors.add(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }

    @Override
    public boolean edit(Author entity) {
        String sql = "UPDATE author SET name = ?,  surname = ? WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            if (preparedStatement.executeUpdate() == 0) {
                throw new RuntimeException("Updating the author is failed, no rows is affected");
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(Author entity) {
        String sql = "DELETE FROM author WHERE id = " + entity.getId();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (preparedStatement.executeUpdate() == 0) {
                throw new RuntimeException("Deleting the author is failed, no rows is affected");
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
