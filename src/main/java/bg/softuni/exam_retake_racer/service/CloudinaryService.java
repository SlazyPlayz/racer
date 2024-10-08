package bg.softuni.exam_retake_racer.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface CloudinaryService {

    String uploadFile(MultipartFile multipartFile, String folder) throws IOException;

    void renameFolder(String oldName, String newName);

    void deleteFiles(Set<String> publicIds);
}
