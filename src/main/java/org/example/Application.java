package org.example;

import org.example.WindowsFrames.MainWindow;

public class Application {
    private int x;
    private int y;
    private int size;
    String title;
    MainWindow mainWindow;

    public Application(int size, String title,int x, int y) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.title = title;
        mainWindow = new MainWindow(size,title,x,y);
    }
}
