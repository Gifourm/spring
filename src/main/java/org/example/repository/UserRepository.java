package org.example.repository;

import org.example.models.UserModel;

import java.sql.*;

public class UserRepository {
    public UserRepository(){}

    public UserModel getUserByLogin(String login) {
        String query = """
                       SELECT *
                       FROM users
                       JOIN emails on emails.login = users.login
                       WHERE users.login=
                       '""" + login  + "'";

        try(ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection()) {
            try {
                Statement statement = connection.createStatement();
                try (ResultSet result = statement.executeQuery(query)) {
                    if (result.next()) {
                        return new UserModel(result.getString("login"),
                                result.getString("password"),
                                result.getDate("date"),
                                result.getString("email"));
                    }
                } finally {
                    statement.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int createUser(UserModel user) {
        String usersQuery = """
                            INSERT INTO users(login, password, date)
                            VALUES(?, ?, ?);
                            INSERT INTO emails(login, email)
                            VALUES(?, ?);
                            """;
        try(ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement usersStatement = connection.prepareStatement(usersQuery)) {
                usersStatement.setString(1, user.getLogin());
                usersStatement.setString(2, user.getPassword());
                usersStatement.setDate(3, user.getDate());
                usersStatement.setString(4, user.getLogin());
                usersStatement.setString(5, user.getEmail());
                return usersStatement.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}


class ConnectionManager implements AutoCloseable {
    private final String URL = "jdbc:postgresql://10.0.2.2:5432/prometheus";
    private final String username = "postgres";
    private final String password = "mizazir";

    Connection connection;
    public ConnectionManager() {}

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            return connection;
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}