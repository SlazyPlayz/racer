package bg.softuni.exam_retake_racer.model.entity.user;

import bg.softuni.exam_retake_racer.model.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "first_name")
    @NotNull
    @Size(min = 3, max = 24)
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(min = 3, max = 24)
    private String lastName;

    @Column(name = "username")
    @NotNull
    @Size(min = 3, max = 24)
    private String username;

    @Column(name = "email")
    @Email
    @NotNull
    @Size(min = 3, max = 48)
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "bio")
    @NotNull
    private String bio;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<RoleEntity> roles;

    public UserEntity() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public @NotNull String getBio() {
        return bio;
    }

    public UserEntity setBio(@NotNull String bio) {
        this.bio = bio;
        return this;
    }
}


