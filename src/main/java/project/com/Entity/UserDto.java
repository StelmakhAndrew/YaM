package project.com.Entity;

import project.com.Security.PasswordMatches;

import javax.validation.constraints.*;

/**
 *class UserDto to transfer date between model and view
 *
 */
@PasswordMatches(message = "Passwords do not match")
public class UserDto {

    @NotNull(message = "must be not empty")
    @NotEmpty(message = "must be not null")
    private String username;

    @NotNull(message = "must be not empty")
    @NotEmpty(message = "must be not empty")
    private String email;

    @NotNull(message = "must be not empty")
    @NotEmpty(message = "must be not empty")
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }


}
