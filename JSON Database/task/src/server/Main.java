package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;
    private static final ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );
    private static final Database database = new Database();

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS))) {
            System.out.println("Server started!");

            while (true) {
                executor.submit(new Session(server.accept(), server, database));
            }
        } catch (IOException ignored) {

        }

        executor.shutdown();
    }
}
