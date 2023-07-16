package com.peaksoft;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        addCity("Moscow", 2000000, 2511.2);
        addCity("New York", 3000000, 2342.5);
        addCity("Paris", 4000000, 2775.2);
        addCity("Milan", 2000000, 3958.2);
        addCity("Bishkek", 2000000, 127.3);

    }


    public static void createCity() {
        String SQL = "CREATE TABLE IF NOT EXISTS cities(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR (50) NOT NULL," +
                "population INTEGER," +
                "area REAL);";
        try (Connection connection = Db.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
            System.out.println("Successfully created table cities");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCity(String name, int population, Double area) {
        String SQL = "INSERT INTO cities (name,population,area)" +
                "VALUES(?,?)";

        try (Connection con = Db.connection();
             PreparedStatement statement = con.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setDouble(2, population);
            statement.setDouble(3, area);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getCityById(int id) {
        String SQL = "SELECT * FROM cities WHERE id = ?";
        try (Connection connection = Db.connection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery(SQL);
            ResultSet.next();
            System.out.print("id: " + resultSet.getInt("id"));
            System.out.print("name: " + resultSet.getString("name"));
            System.out.println("population: " + resultSet.getInt("population"));
        }catch (SQLException e){
        e.printStackTrace();
        }
    }


}
