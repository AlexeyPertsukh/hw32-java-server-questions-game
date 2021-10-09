package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Connect implements Runnable{
    private final int port;

    public Connect(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Local host: " + InetAddress.getLocalHost().getHostAddress());
            String messageStart = String.format("port: %d ", port);
            System.out.println(messageStart);
            System.out.println("server started");
            System.out.println();
            while (true) {
                Socket socket = serverSocket.accept();      //ждать, пока не появится новое подключение
                System.out.println("new client connected");
                ClientListener clientListener = new ClientListener(socket);
                new Thread(clientListener).start();
            }
        } catch (IOException e) {
            throw new ConnectException("failed connect to port " + port);
        }
    }

}
