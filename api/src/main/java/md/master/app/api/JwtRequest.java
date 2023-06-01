package md.master.app.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWT request model")
public class JwtRequest {
    @Schema(description = "User username", required = true, example = "bob")
    private String username;
    @Schema(description = "User username", required = true, example = "100")
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
