package bg.softuni.exam_retake_racer.model.entity;

import bg.softuni.exam_retake_racer.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    public RoleEntity(Role role) {
        this.role = role;
    }

    public RoleEntity() {}

    public @NotNull Role getRole() {
        return role;
    }

    public RoleEntity setRole(@NotNull Role role) {
        this.role = role;
        return this;
    }
}
