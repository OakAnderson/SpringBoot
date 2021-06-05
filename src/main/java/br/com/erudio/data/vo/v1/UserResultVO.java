package br.com.erudio.data.vo.v1;

import java.time.OffsetDateTime;
import java.util.Objects;

public class UserResultVO {

    private String username;
    private String fullName;
    private String email;
    private final OffsetDateTime createdAt = OffsetDateTime.now();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResultVO that = (UserResultVO) o;
        return Objects.equals(username, that.username) && Objects.equals(fullName, that.fullName) && Objects.equals(email, that.email) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, fullName, email, createdAt);
    }
}
