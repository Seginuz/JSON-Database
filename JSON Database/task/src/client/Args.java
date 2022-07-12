package client;

import com.beust.jcommander.Parameter;
import com.google.gson.annotations.Expose;

public class Args {
    @Expose
    @Parameter(names = "-t", description = "Request type")
    private String type;

    @Expose
    @Parameter(names = "-k", description = "Cell index")
    private String key;

    @Expose
    @Parameter(names = "-v", description = "Value to save")
    private String value;

    @Parameter(names = "-in", description = "File to read request from")
    private String fileLocation;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getFileLocation() {
        return fileLocation;
    }
}
