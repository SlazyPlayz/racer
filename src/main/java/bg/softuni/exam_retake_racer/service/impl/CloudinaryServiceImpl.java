package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.CloudinaryFolderRenameException;
import bg.softuni.exam_retake_racer.exceptions.ImageUploadException;
import bg.softuni.exam_retake_racer.service.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile, String folder) {
        try {
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", folder);
            Map uploadedFile = cloudinary.uploader().upload(multipartFile.getBytes(), options);
            String publicId = (String) uploadedFile.get("public_id");
            return cloudinary.url().generate(publicId);
        } catch (IOException e) {
            throw new ImageUploadException();
        }
    }

    @Override
    public void renameFolder(String oldName, String newName) {
        try {
            cloudinary.api().renameFolder(oldName, newName, null);
        } catch (Exception e) {
            throw new CloudinaryFolderRenameException();
        }
    }


}
