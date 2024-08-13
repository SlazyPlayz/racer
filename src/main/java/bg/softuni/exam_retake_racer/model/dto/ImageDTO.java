package bg.softuni.exam_retake_racer.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageDTO {
    private String name;
    private MultipartFile file;

    public ImageDTO() {
    }

    public String getName() {
        return name;
    }

    public ImageDTO setName(String name) {
        this.name = name;
        return this;
    }

    public MultipartFile getFile() {
        return file;
    }

    public ImageDTO setFile(MultipartFile file) {
        this.file = file;
        return this;
    }
}
