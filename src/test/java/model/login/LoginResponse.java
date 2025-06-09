package model.login;

import java.util.Objects;

public class LoginResponse {
    private String token;
    private int timeout;

    public LoginResponse(){}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoginResponse that = (LoginResponse) o;
        return timeout == that.timeout && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, timeout);
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
