package dbexamples.dao;

import dbexamples.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAOBeen {
    private final Connection connection;

    private final String BOOK_SELECT_BY_ID_QUERY = "SELECT * FROM book WHERE id = ?";

    public BookDAOBeen(Connection connection) {
        this.connection = connection;
    }
    public Book findBookById(Integer bookId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(BOOK_SELECT_BY_ID_QUERY);
        statement.setInt(1, bookId);
        ResultSet resultSet = statement.executeQuery();
        Book book = new Book();
        while (resultSet.next()) {
            book.setId(resultSet.getInt("id"));
            book.setAuthor(resultSet.getString("author"));
            book.setTitle(resultSet.getString("title"));
            book.setDateAdded(resultSet.getDate("date_added"));
        }
        return book;
    }
}
