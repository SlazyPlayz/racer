package bg.softuni.exam_retake_racer.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
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
