package md.master.app.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "String response DTO model")
public class StringResponse {
    @Schema(description = "String value of message to be displayed when response is given", required = true, example = "Product ID is 1")
    private String value;

    public StringResponse() {
    }

    public StringResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
