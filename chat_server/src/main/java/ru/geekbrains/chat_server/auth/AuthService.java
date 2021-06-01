package ru.geekbrains.chat_server.auth;

public interface AuthService {
    String getNickname(String login, String password);

    boolean changeNickName(String currentNickname, String newNickname);

    void start();
    void stop();
    String getUsernameByLoginAndPassword(String login, String password);
    String changeUsername(String oldName, String newName);
    String changePassword(String username, String oldPassword, String newPassword);
    String getNickName(String login, String password);
    String changeNickname(String currentNickname, String newNickname);
}
