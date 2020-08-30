package com.lucas.gowatch.service;

import com.lucas.gowatch.config.StorageConfiguration;
import com.lucas.gowatch.service.exception.StoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class StorageService {

    private final Path uploadPath;

    @Autowired
    public StorageService(StorageConfiguration configuration){
        this.uploadPath = Paths.get(configuration.getLocation());
    }

    // Create directory
    @PostConstruct
    void init(){
        try{
            Files.createDirectories(uploadPath);
        } catch (IOException e){
            throw new StoreException("Could not create upload directory" ,e);
        }
    }

    // Store a file
    public String store(MultipartFile file){
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try{
            if(file.isEmpty()) throw new StoreException("The " + fileName + " is empty!");

            // Name and save the file in /public
            InputStream inputStream = file.getInputStream();
            do
                fileName = File.createTempFile("vdi", "").getName();
            while (new File( this.uploadPath.toString() + '\\' + fileName).exists());
            Files.copy(inputStream, this.uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new StoreException("Filed to store file" + fileName, e);
        }
        return fileName;
    }

    // Delete file
    public boolean delete(String fileName){
        String filePath = this.uploadPath.toString() + '\\' + fileName;
        File toDelete = new File(filePath);
        System.out.println("Removed: " + filePath);
        return toDelete.delete();
    }
}
