package ru.geekbrains.chat_server.server;

import ru.geekbrains.chat_server.Database;
import ru.geekbrains.chat_server.auth.AuthService;

public class DataBaseAuthService implements AuthService {
@Override
    public String getNickname(String login, String password) {
        return Database.getUserName(login, password);
    }
@Override
    public boolean changeNickName(String currentNickname, String newNickname){
    return Database.changeUserNickname(currentNickname, newNickname);
}

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        return null;
    }

    @Override
    public String changeUsername(String oldName, String newName) {
        return null;
    }

    @Override
    public String changePassword(String username, String oldPassword, String newPassword) {
        return null;
    }
}
