package com.seyun.CodeStudy.common;

import com.seyun.CodeStudy.model.lecture.Video;
import com.seyun.CodeStudy.model.lecture.Thumbnail;
import com.seyun.CodeStudy.model.member.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

@Component
public class FileUtils {

    private final String savePath = Paths.get("C:", "CodeStudy").toString();

    public HashMap<String, String> uploadFile(final MultipartFile multipartFile, String type){
        if(multipartFile.isEmpty()){
            return null;
        }
        String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String path = type + File.separator + today;
        String uploadPath = getUploadPath(path);
        File uploadFile = new File(uploadPath + File.separator + saveName);

        try{
            multipartFile.transferTo(uploadFile);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("uploadPath", path);
        map.put("originalName", multipartFile.getOriginalFilename());
        map.put("saveName", saveName);
        map.put("size", String.valueOf(multipartFile.getSize()));
        return map;
    }

    public Thumbnail uploadThumbnail(final MultipartFile multipartFile){
        if(multipartFile.isEmpty()){
            return null;
        }
        String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String path = "Thumbnail" + File.separator + today;
        String uploadPath = getUploadPath(path);
        File uploadFile = new File(uploadPath + File.separator + saveName);


        try{
            multipartFile.transferTo(uploadFile);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setUploadPath(path);
        thumbnail.setOriginalName(multipartFile.getOriginalFilename());
        thumbnail.setSaveName(saveName);
        thumbnail.setSize(multipartFile.getSize());

        return thumbnail;
    }

    public Video uploadVideo(final MultipartFile multipartFile){
        if(multipartFile.isEmpty()){
            return null;
        }
        String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String path = "Video" + File.separator + today;
        String uploadPath = getUploadPath(path);
        File uploadFile = new File(uploadPath + File.separator + saveName);


        try{
            multipartFile.transferTo(uploadFile);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        Video video = new Video();
        video.setUploadPath(path);
        video.setOriginalName(multipartFile.getOriginalFilename());
        video.setSaveName(saveName);
        video.setSize(multipartFile.getSize());

        return video;
    }

    public Profile uploadProfile(final MultipartFile multipartFile){
        if(multipartFile.isEmpty()){
            return null;
        }
        String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String path = "Profile" + File.separator + today;
        String uploadPath = getUploadPath(path);
        File uploadFile = new File(uploadPath + File.separator + saveName);


        try{
            multipartFile.transferTo(uploadFile);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        Profile profile = new Profile();
        profile.setUploadPath(path);
        profile.setOriginalName(multipartFile.getOriginalFilename());
        profile.setSaveName(saveName);
        profile.setSize(multipartFile.getSize());

        return profile;
    }



    public void deleteFile(final String addPath, final String filename){
        String filePath = Paths.get(savePath, addPath, filename).toString();
        deleteFile(filePath);
    }

    private void deleteFile(final String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }

    private String generateSaveFilename(final String filename){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(filename);
        return uuid + "." + extension;
    }

    private String getUploadPath(final String addPath){
        return makeDirectories(savePath + File.separator + addPath);
    }

    private String makeDirectories(final String path){
        File dir = new File(path);
        if(dir.exists() == false){
            dir.mkdirs();
        }
        return dir.getPath();
    }

}
