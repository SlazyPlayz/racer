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
    @NotNull(message = "First name is required")
    @Size(min = 3, max = 24, message = "First name must be between 3 and 24 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name is required")
    @Size(min = 3, max = 24, message = "Last name must be between 3 and 24 characters")
    private String lastName;

    @Column(name = "username")
    @NotNull(message = "Username is required")
    @Size(min = 3, max = 24, message = "Username must be between 3 and 24 characters")
    private String username;

    @Column(name = "email")
    @Email(message = "Email must be valid")
    @NotNull(message = "Email is required")
    @Size(min = 3, max = 48, message = "Email must be between 3 and 48 characters")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password is required")
    private String password;

    @Column(name = "bio")
    @NotNull(message = "Bio is required")
    @Size(min = 3, max = 512, message = "Bio must be between 3 and 512 characters")
    private String bio;

    @Column(name = "image_url")
    @NotNull(message = "Image is required")
    private String imageUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<RoleEntity> roles;

    public UserEntity() {
    }

    public @NotNull(message = "First name is required") @Size(min = 3, max = 24, message = "First name must be between 3 and 24 characters") String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(@NotNull(message = "First name is required") @Size(min = 3, max = 24, message = "First name must be between 3 and 24 characters") String firstName) {
        this.firstName = firstName;
        return this;
    }

    public @NotNull(message = "Last name is required") @Size(min = 3, max = 24, message = "Last name must be between 3 and 24 characters") String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(@NotNull(message = "Last name is required") @Size(min = 3, max = 24, message = "Last name must be between 3 and 24 characters") String lastName) {
        this.lastName = lastName;
        return this;
    }

    public @NotNull(message = "Username is required") @Size(min = 3, max = 24, message = "Username must be between 3 and 24 characters") String getUsername() {
        return username;
    }

    public UserEntity setUsername(@NotNull(message = "Username is required") @Size(min = 3, max = 24, message = "Username must be between 3 and 24 characters") String username) {
        this.username = username;
        return this;
    }

    public @Email(message = "Email must be valid") @NotNull(message = "Email is required") @Size(min = 3, max = 48, message = "Email must be between 3 and 48 characters") String getEmail() {
        return email;
    }

    public UserEntity setEmail(@Email(message = "Email must be valid") @NotNull(message = "Email is required") @Size(min = 3, max = 48, message = "Email must be between 3 and 48 characters") String email) {
        this.email = email;
        return this;
    }

    public @NotNull(message = "Password is required") String getPassword() {
        return password;
    }

    public UserEntity setPassword(@NotNull(message = "Password is required") String password) {
        this.password = password;
        return this;
    }

    public @NotNull(message = "Bio is required") @Size(min = 3, max = 512, message = "Bio must be between 3 and 512 characters") String getBio() {
        return bio;
    }

    public UserEntity setBio(@NotNull(message = "Bio is required") @Size(min = 3, max = 512, message = "Bio must be between 3 and 512 characters") String bio) {
        this.bio = bio;
        return this;
    }

    public @NotNull(message = "Image is required") String getImageUrl() {
        return imageUrl;
    }

    public UserEntity setImageUrl(@NotNull(message = "Image is required") String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}


