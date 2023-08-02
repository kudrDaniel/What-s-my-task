package ru.duckcoder;

public class Main {
    //Entry point
    public static int main(String[] args) {
        Integer exitCode;
        //Transfer control to manager
        exitCode = ApplicationManager.run();
        //Get exit code
        return exitCode;
    }
}