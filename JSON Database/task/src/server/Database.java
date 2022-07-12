package server;

import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {

    private static final String fileLocation = "JSON Database/task/src/server/data/db.json";
    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();
    Gson gson = new Gson();
    public JsonObject db;

    public Database() {
        try {
            readLock.lock();
            String json = new String(Files.readAllBytes(Paths.get(fileLocation)));
            this.db = gson.fromJson(json, JsonObject.class);
            if (this.db == null) {
                this.db = new JsonObject();
            }
            readLock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
            this.db = new JsonObject();
            readLock.unlock();
        }
    }

    public Response getCell(Request request) {
        JsonElement json = this.db;

        if (request.getKey().isJsonArray()) {
            JsonArray keys = request.getKey().getAsJsonArray();

            for (int i = 0; i < keys.size(); i++) {
                if (json != null) {
                    if (json.isJsonObject()) {
                        json = json.getAsJsonObject().get(keys.get(i).getAsString());
                    } else {
                        json = json.getAsJsonPrimitive();
                        break;
                    }
                }
            }
        } else {
            json.getAsJsonObject().get(request.getKey().getAsString());
        }

        if (json != null) {
            return new Response.Builder()
                    .setResponse("OK")
                    .setValue(json)
                    .build();
        } else {
            return new Response.Builder()
                    .setResponse("ERROR")
                    .setReason("No such key")
                    .build();
        }
    }

    public synchronized Response setCell(Request request) {
        JsonObject editJson = this.db;

        if (request.getKey().isJsonArray()) {
            JsonArray keys = request.getKey().getAsJsonArray();

            for (int i = 0; i < keys.size() - 1; i++) {
                if (editJson.get(keys.get(i).getAsString()) == null) {
                    editJson.add(keys.get(i).getAsString(), new JsonObject());
                }
                editJson = editJson.getAsJsonObject(keys.get(i).getAsString());
            }

            editJson.add(keys.get(keys.size() - 1).getAsString(), request.getValue());
        } else {
            editJson.add(request.getKey().getAsString(), request.getValue());
        }

        writeLock.lock();
        this.writeDatabase();
        writeLock.unlock();

        return new Response.Builder()
                .setResponse("OK")
                .build();
    }

    public Response deleteCell(Request request) {
        JsonElement json = this.db;
        JsonArray keys = gson.fromJson(request.getKey(), JsonArray.class);

        for (int i = 0; i < keys.size() - 1; i++) {
            if (json != null) {
                if (json.isJsonObject()) {
                    json = json.getAsJsonObject().get(keys.get(i).getAsString());
                } else {
                    json = null;
                    break;
                }
            }
        }

        if (json != null) {
            System.out.println(json);
            json.getAsJsonObject().remove(keys.get(keys.size() - 1).getAsString());

            writeLock.lock();
            this.writeDatabase();
            writeLock.unlock();

            return new Response.Builder()
                    .setResponse("OK")
                    .build();
        } else {
            return new Response.Builder()
                    .setResponse("ERROR")
                    .setReason("No such key")
                    .build();
        }
    }

    public void writeDatabase() {
        try (FileWriter writer = new FileWriter(fileLocation)) {
            writer.write(gson.toJson(this.db));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
