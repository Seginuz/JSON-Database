package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Session implements Runnable
{

    private final Socket socket;
    private final ServerSocket server;
    private final Database database;
    private final Gson gson;

    public Session(Socket socket, ServerSocket server, Database database) {
        this.socket = socket;
        this.server = server;
        this.database = database;
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .disableInnerClassSerialization()
                .create();
    }

    @Override
    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            Request request = gson.fromJson(input.readUTF(), Request.class);
            Response response;
            boolean run = true;

            switch (request.getType()) {
                case "set":
                    response = database.setCell(request);
                    break;
                case "get":
                    response = database.getCell(request);
                    break;
                case "delete":
                    response = database.deleteCell(request);
                    break;
                case "exit":
                    response = new Response.Builder()
                            .setResponse("OK")
                            .build();
                    run = false;
                    break;
                default:
                    response = new Response.Builder()
                            .setResponse("ERROR")
                            .setReason("Action type not supported")
                            .build();
            }

            output.writeUTF(gson.toJson(response));
            socket.close();

            if (!run) {
                server.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
