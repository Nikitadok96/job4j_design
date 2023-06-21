package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.startsWith("GET")) {
                            String string = str.split(" ")[1].split("=")[1];
                            if ("Bye".equals(string)) {
                                server.close();
                            } else if ("Hello".equals(string)) {
                                out.write("Hello\r\n\r\n".getBytes());
                            } else {
                                out.write(String.format("%s\r\n\r\n", string).getBytes());
                            }
                        }
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}
