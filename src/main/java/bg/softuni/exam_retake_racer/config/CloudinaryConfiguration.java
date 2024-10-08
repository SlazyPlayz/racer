package bg.softuni.exam_retake_racer.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {

    private final String cloudName;

    private final String apiKey;

    private final String apiSecret;

    public CloudinaryConfiguration(@Value("${cloudinary.cloud.name}") String cloudName,@Value("${cloudinary.api.key}") String apiKey,@Value("${cloudinary.api.secret}") String apiSecret) {
        this.cloudName = cloudName;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }
}
