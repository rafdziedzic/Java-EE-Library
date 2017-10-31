package dao;

import model.Book;

import java.sql.SQLException;
import java.util.List;

public interface DaoBook {

    void addNewBook2(Book book) throws SQLException, ClassNotFoundException;

    Book findBook(String id) throws Exception;

    Book updateBook(Book book) throws SQLException, ClassNotFoundException;

    void removeBook(String id) throws SQLException, ClassNotFoundException;

    List getAllBook() throws Exception;

}
