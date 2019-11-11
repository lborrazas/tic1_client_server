package tic1.commons.transfers;

public class UserDTO {
    private String type;
    private String username;
    private String password;
    private String role;
    private long provider_id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getProvider() {
        return provider_id;
    }

    public void setProvider(long provider) {
        this.provider_id = provider_id;
    }




}
