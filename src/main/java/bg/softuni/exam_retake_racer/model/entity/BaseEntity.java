package bg.softuni.exam_retake_racer.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public BaseEntity setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public BaseEntity() {
        this.uuid = UUID.randomUUID();
    }
}
