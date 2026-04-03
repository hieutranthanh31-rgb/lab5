package com.slide1.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
  


@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "Id", nullable = false, unique = true)
    private String id;
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "FullName", nullable = false)
    private String fullName;
    @Column(name = "Email", nullable = false)
    private String email;
    @Column(name = "Admin", nullable = false)
    private boolean admin;
    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;


    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}