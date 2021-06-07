package ru.geekbrains.chat_server;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement createUserState;
    private static PreparedStatement getUserNicknameStatement;
    private static PreparedStatement changeUserNicknameStatement;
    private static PreparedStatement deleteUserStatement;
    private static final Logger logger = Logger.getLogger(Database.class.getName());


    public static boolean connect(){
        logger.setLevel1(Level.ALL);
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database/chat.db");
            logger.log(Level.INFO, "Connect to the database!");
            statement = connection.createStatement();
            createUserTable();
            prepareAllStatement();
            return true;
        }catch (ClassNotFoundException | SQLException e){
            logger.log(Level.SEVERE, e.getMessage(), e);
            return false;
    }
    }
    public static void disconnect(){
        try{
            statement.close();
        }catch (SQLException e){
           logger.log(Level.SEVERE, e.getMessage(), e);
        }
        try{
    connection.close();
}catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void createUserTable() throws SQLException{
       statement.executeUpdate("CREATE TABLE IF NOT EXIST user ("+" id INTEGER PRIMARY KEY AUTOINCREMENT "+" NOT NULL "+" UNIQUE, "+" login VARCHAR (32) UNIQUE "+" NOT NULL "+" PASSWORD VARCHAR (32) NOT NULL "+" nickname VARCHAR (32) UNIQUE "+" NOT NULL");
        );
      }
      public static void prepareAllStatement() throws SQLException{
        createUserState = connection.prepareStatement("INSERT INTO user(login, password, nickname) VALUES (?, ?, ?);");
        getUserNicknameStatement = connection.prepareStatement("SELECT nickname FROM user WHERE login = ? AND password = ?;");
        changeUserNicknameStatement = connection.prepareStatement("UPDATE user SET nickname = ? WHERE nickname = ?;");
        deleteUserStatement = connection.prepareStatement("DELETE FROM user FROM login = ?;");
      }
      public static boolean createUser(String login, String password, String nickname){
        try{
            createUserState.setString(1, login);
            createUserState.setString(2, password);
            createUserState.setString(3, nickname);
            createUserState.executeUpdate();
            return true;
            }catch (SQLException e){
            return false;
        }
      }
      public static String getUserName(String login, String password){
        String nickname = null;
        try{
            getUserNicknameStatement.setString(1, login);
            getUserNicknameStatement.setString(2, password);
            ResultSet rs = getUserNicknameStatement.executeQuery();
            if (rs.next()){
                nickname = rs.getString(1);
                }
            rs.close();
        }catch (SQLException e){
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return nickname;
        }
        public static boolean changeUserNickname(String currentNickname, String newNickname){
        try {
            changeUserNicknameStatement.setString(1, newNickname);
            changeUserNicknameStatement.setString(2,currentNickname);
            changeUserNicknameStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        }
        }
        public static boolean deleteUser(String login){
        try{
            deleteUserStatement.setString(1, login);
            deleteUserStatement.executeUpdate();
            return true;
        }catch (SQLException e);
        return false;
        }

     }
}
