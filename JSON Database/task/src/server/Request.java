package server;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public class Request {
    @Expose
    private String type;
    @Expose
    private JsonElement key;
    @Expose
    private JsonElement value;

    public String getType() {
        return type;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }
}
