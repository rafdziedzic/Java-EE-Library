package controller;

import model.Person;
import reop.PersonRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "controller.PersonCreateServlet")
public class PersonCreateServlet extends HttpServlet {

    PersonRepository personRepository = new PersonRepository();


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
                insertPerson(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if ("/delete".equals(action)) {
            try {
                deletePerson(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if ("/edit".equals(action)) {
            showEditForm(request, response);

        } else if ("/update".equals(action)) {
            try {
                updatePerson(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            listPerson(request, response);
        }

    }

    private void listPerson(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        List<Person> listPerson = null;
        try {
            listPerson = personRepository.getAllPerson();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("listPerson", listPerson);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/PersonList.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("PersonForm.jsp");
        dispatcher.forward(request, response);

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Person existingPerson = null;
        try {
            existingPerson = personRepository.findPerson(request.getParameter("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("PersonForm.jsp");
        request.setAttribute("person", existingPerson);
        dispatcher.forward(request, response);

    }

    private void insertPerson(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setSurname(surname);
        try {
            personRepository.addNewPerson(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("PersonList.jsp");

    }

    private void updatePerson(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        Person person = new Person(id, name, surname);
        try {
            personRepository.updatePerson(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("PersonList.jsp");

    }

    private void deletePerson(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ClassNotFoundException {

        String id = (request.getParameter("id"));

        try {
            personRepository.deletePerson(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("PersonList.jsp");
    }
}
