package client;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 23456;
    public static final Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static void main(String[] args) {
        Args arguments = new Args();
        String message;
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        if (arguments.getFileLocation() != null) {
            try {
                message = new String(Files.readAllBytes(
                        Paths.get("C:\\Users\\Stefan Koelewijn\\IdeaProjects\\JSON Database\\JSON Database\\task\\src\\client\\data\\", arguments.getFileLocation()))
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            message = gson.toJson(arguments);
        }

        try (Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Client started!");

            output.writeUTF(message);
            System.out.println("Sent: " + message);

            String response = input.readUTF();
            System.out.println("Received: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
