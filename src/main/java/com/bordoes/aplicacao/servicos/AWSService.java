package com.bordoes.aplicacao.servicos;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.core.sync.RequestBody;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class AWSService {
    private static final String BUCKET_NAME = "bordoes";

    public void uploadFileBucketS3(MultipartFile multipartFile, String fileName) throws URISyntaxException, IOException {
        String endpoint = "http://localhost:4566";
        Path tempFile = Files.createTempFile("temp-", multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        S3Client s3Client = S3Client.builder()
                .region(Region.SA_EAST_1)
                .endpointOverride(new URI(endpoint))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        try {
            var resp = s3Client.putObject(PutObjectRequest.builder()
                            .bucket(BUCKET_NAME)
                            .key(fileName)
                            .build(),
                    RequestBody.fromFile(tempFile));
            System.out.println("estouparado");
        } catch (S3Exception e) {
            System.err.println("Erro ao fazer upload do arquivo: " + e.getMessage());
        }
    }
}
