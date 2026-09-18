package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    private static StudentService service = new StudentService();

    public static void main(String[] args) throws IOException {
        service.addStudent(new Student(1, "Alice"));
        service.addStudent(new Student(2, "Bob"));

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        server.createContext("/students", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                StringBuilder response = new StringBuilder("=== Student List ===\n");
                for (Student s : service.getStudents()) {
                    response.append("ID: ").append(s.getId()).append(", Name: ").append(s.getName()).append("\n");
                }
                
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            }
        });

        server.start();
    }
}
