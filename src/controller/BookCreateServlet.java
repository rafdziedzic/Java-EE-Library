package controller;

import model.Book;
import reop.BookRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "controller.BookCreateServlet")
public class BookCreateServlet extends HttpServlet {

    BookRepository bookRepository = new BookRepository();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getServletPath();

        if ("/new".equals(action)) {
            showNewForm(request, response);

        } else if ("/insert".equals(action)) {
            try {
                insertBook(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if ("/delete".equals(action)) {
            try {
                deleteBook(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if ("/edit".equals(action)) {
            showEditForm(request, response);

        } else if ("/update".equals(action)) {
            try {
                updateBook(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            listBook(request, response);

        }

    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        List<Book> listBook = null;
        try {
            listBook = bookRepository.getAllBook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        dispatcher.forward(request, response);

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Book existingBook = null;
        try {
            existingBook = bookRepository.findBook(request.getParameter("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);


    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        String id = request.getParameter("Id");

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setPrice(price);
        newBook.setId(id);
        try {
            bookRepository.addNewBook2(newBook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("BookList.jsp");

    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");

        Book book = new Book(id, title, author, price);
        try {
            bookRepository.updateBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("BookList.jsp");

    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ClassNotFoundException {

        String id = (request.getParameter("id"));

        try {
            bookRepository.removeBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("BookList.jsp");

    }
}
