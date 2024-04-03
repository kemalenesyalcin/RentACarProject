import business.UserManager;
import core.Helper;
import entity.User;
import view.AdminView;

import view.LoginView;

import javax.swing.*;
public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        view.LoginView loginView = new view.LoginView();
        UserManager userManager = new UserManager();
        view.AdminView adminView = new view.AdminView(userManager.findByLogin("",""));

    }
}