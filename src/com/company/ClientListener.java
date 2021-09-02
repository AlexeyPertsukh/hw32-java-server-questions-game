
package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClientListener implements Runnable, IConst{
    private final Socket clientSocket;
    private PrintWriter printWriter;
    private List<Question> questions;


    public ClientListener(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            printWriter = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner scanner;
        try {
            scanner = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String query;
        while (scanner.hasNextLine()) {
            query = scanner.nextLine();
            System.out.println("input: " + query);

            if (query.equals(QUERY)) {

                try {
                    questions = Csv.readQuestions("*");
                } catch (CsvException ex) {
                    System.out.println(ex.getMessage());
                    break;
                }

                sendQuestionsToClient();
                System.out.println("output: questions");
            } else {
                System.out.println("<unknown command>");
            }
        }

        System.out.println("client disconnected");
    }

    private void sendQuestionsToClient() {
        for (Question question : questions) {
            printWriter.println(question.toJson());
        }
        printWriter.flush();
    }

}
