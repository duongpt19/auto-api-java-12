package model.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginError {
    private String message;

    @Override
    public String toString() {
        return "LoginError{" +
                "message='" + message + '\'' +
                '}';
    }
}
