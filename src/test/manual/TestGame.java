package test.manual;

import gui.MenuGUI;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class TestGame {
    public static void main(String[] args) {
        MenuGUI menu = new MenuGUI();
        Thread menuThread = new Thread(menu);
        menuThread.start();
    }
}