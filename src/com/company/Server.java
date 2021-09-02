package com.company;

public class Server implements IConst {

    private boolean exit;

    public void start() {
        printOnStart();
        try{
            Connect connect = new Connect(PORT);
            Thread threadConnect = new Thread(connect);
            threadConnect.start();
        }catch (ConnectException ex) {
            System.out.println(ex.getMessage());
            exit = true;
        }

        while (!exit) {

        }
    }

    private void printOnStart() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("questions server for the game ");
        System.out.println("\"Who Wants to Be a Millionaire?\"");
        System.out.println("ver." + VERSION);
        System.out.println(".......");
        System.out.println("Query to server:    select *");
        System.out.println("Answer from server: game questions and answers in json format");
        System.out.println(".......");
        System.out.println("JAVA A01 2020/21 IT-STEP, Zaporozhye");
        System.out.println("homework #32, Pertsukh Alexey");
        System.out.println("--------------------------------------------------------------");
    }

}
