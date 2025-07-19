package maytinh.entity;

public class User1 extends User{
    private String fullname;
    private String email;
    private String role;
    private boolean manager;
    private String username;

    public User1() {}

    public User1(String fullname, String email, String role) {
        this.fullname = fullname;
        this.email = email;
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(role);
    }
    
    public boolean isManager(){
        return manager;
    }
    
    public void setManager(boolean manager){
        this.manager=manager;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
    private boolean enabled;

public boolean isEnabled() {
    return enabled;
}

public void setEnabled(boolean enabled) {
    this.enabled = enabled;
}
}