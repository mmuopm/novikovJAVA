package Proj.dbexamples.dao;

import Proj.dbexamples.db.DBConnection;
import Proj.dbexamples.model.Book;

import java.sql.*;

public class BookDaoJDBC {
    public void findBookById(Integer bookId) {
        try (Connection connection = DBConnection.INSTANCE.getConnection()){
            if (connection != null) {
                System.out.println("Ура мы внутри");
            }
            else {
                System.out.println("бд нэт");
            }

            String select = "select * from books where id= ?";
            PreparedStatement selectQuery = connection.prepareStatement(select);
            selectQuery.setInt(1, bookId);
            ResultSet resultSet = selectQuery.executeQuery(); // Результат запроса

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setAuthor(resultSet.getString("author"));
                book.setTitle(resultSet.getString("title"));
                book.setDateAdded(resultSet.getDate("date_added"));
                System.out.println(book);
            }
        }




            // Запрос к базе данных




         catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}