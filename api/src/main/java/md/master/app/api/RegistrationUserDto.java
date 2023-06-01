package md.master.app.api;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "registration user DTO model")
public class RegistrationUserDto {
    @Schema(description = "User username", required = true, example = "testuser")
    private String username;
    @Schema(description = "User password", required = true, example = "passwd")
    private String password;
    @Schema(description = "User password for verify it", required = true, example = "passwd")
    private String confirmPassword;
    @Schema(description = "User email", required = true, example = "test@example.com")
    private String email;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
