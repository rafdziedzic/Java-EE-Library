package reop;

import dao.DaoBook;
import model.Book;
import model.DbConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class BookRepository implements DaoBook {
    DbConfiguration dbConfiguration = new DbConfiguration();
    ResultSet resultSet;


    // adding a book to the library
    public void addNewBook2(Book book) throws SQLException, ClassNotFoundException {
        dbConfiguration.dbExecuteUpdateQuery("INSERT Into `library`.`book` (`id`, `title`, `author`,`price`) Values " + "('" + book.getId() + "'," + "'" + book.getTitle() + "'," + "'" + book.getAuthor() + "'," + "'" + book.getPrice() + "')");
    }

    public Book findBook(String id) throws Exception {
        resultSet = dbConfiguration.dbExecuteQuery("Select * From `library`.`book` Where id= " + "'" + id + "'");
        Book book = new Book();
        while (resultSet.next()) {
            book.setId(resultSet.getString(1));
            book.setAuthor(resultSet.getString(2));
            book.setTitle(resultSet.getString(3));
            book.setPrice(resultSet.getString(4));
        }
        return book;
    }

    // Update book
    public Book updateBook(Book book) throws SQLException, ClassNotFoundException {
        dbConfiguration.dbExecuteUpdateQuery("UPDATE `library`.`book` SET `id`='" + book.getId() + "', `title`='" + book.getTitle() + "'," +
                "`author`='" + book.getAuthor() + "'," + "`price`='" + book.getPrice() + "' WHERE `id`='" + book.getId() + "'");
        return book;
    }

    @Override
    public void removeBook(String id) throws SQLException, ClassNotFoundException {
        dbConfiguration.dbExecuteUpdateQuery("DELETE FROM `library`.`book` WHERE `id`='" + id + "'");
    }

    @Override
    public List getAllBook() throws Exception {
        resultSet = dbConfiguration.dbExecuteQuery("Select * From book");

        List<Book> list = new LinkedList<>();
       // Book book = new Book();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getString(1));
            book.setAuthor(resultSet.getString(2));
            book.setTitle(resultSet.getString(3));
            book.setPrice(resultSet.getString(4));

            list.add(book);
        }
        return list;
    }
}