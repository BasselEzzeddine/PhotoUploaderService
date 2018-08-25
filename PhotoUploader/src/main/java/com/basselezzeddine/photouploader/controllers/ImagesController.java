package com.basselezzeddine.photouploader.controllers;

import com.basselezzeddine.photouploader.models.DownloadImagesResponse;
import com.basselezzeddine.photouploader.utils.RandomStringGenerator;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

@RestController
public class ImagesController {

    @RequestMapping(value = "/images/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = "images/" + RandomStringGenerator.generate() + ".jpg";
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                stream.write(bytes);
                stream.close();
                System.out.println("File saved successfully.");
                return "success";
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "failure";
            }
        } else {
            System.out.println("File is empty.");
            return "failure";
        }
    }

    @RequestMapping(value = "/images/download", method = RequestMethod.GET)
    public DownloadImagesResponse images() {
        ArrayList<String> base64Images = new ArrayList<>();
        File imagesDirectory = new File("images");
        File[] files = imagesDirectory.listFiles();
        if (files != null) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));
            for (File file : files) {
                try {
                    byte[] bytes = org.apache.commons.io.FileUtils.readFileToByteArray(file);
                    Base64 base64 = new Base64();
                    String base64Image = new String(base64.encode(bytes));
                    base64Images.add(base64Image);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return new DownloadImagesResponse(base64Images);
    }
}
