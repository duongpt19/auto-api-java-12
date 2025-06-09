package model.login;

public class LoginError {
    private String message;

    public LoginError(){}

    public LoginError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoginError{" +
                "message='" + message + '\'' +
                '}';
    }
}
