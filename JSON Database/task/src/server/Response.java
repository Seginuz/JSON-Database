package server;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public class Response {
    @Expose
    private String response;
    @Expose
    public JsonElement value;
    @Expose
    private String reason;

    public static class Builder {
        Response response = new Response();

        public Response.Builder setResponse(String response) {
            this.response.response = response;
            return this;
        }

        public Response.Builder setValue(JsonElement value) {
            this.response.value = value;
            return this;
        }

        public Response.Builder setReason(String reason) {
            this.response.reason = reason;
            return this;
        }

        public Response build() {
            return this.response;
        }
    }
}
