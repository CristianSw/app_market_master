package md.master.app.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWT response model")
public class JwtResponse {
    @Schema(description = "token from auth service, to be transferred between services",required = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse() {
    }
}
