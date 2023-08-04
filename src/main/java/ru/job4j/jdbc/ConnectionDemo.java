package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.StringJoiner;

public class ConnectionDemo {
    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                   "CREATE TABLE IF NOT EXISTS demo_table(%s, %s)",
                        "id SERIAL PRIMARY KEY",
                        "name TEXT"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Config config = new Config("data/app.properties");
        config.load();
        Class.forName(config.value("class"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        return DriverManager.getConnection(url, login, password);
    }
}
