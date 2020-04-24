package com.zmz121.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import util.CommonReturnType;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ImageController
{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${project.imageslocation}")
    private String imagePath;

    @PostMapping("/upload/banner")
    public void upload(MultipartFile multipartFile, String name)
    {
        String contentType = multipartFile.getContentType();
        logger.info(contentType);
    }

    @GetMapping("/banner/list")
    public CommonReturnType queryImages(HttpServletRequest request)
    {
        String contextPath = request.getContextPath();
        imagePath = imagePath.substring(imagePath.indexOf(':') + 1);
        logger.info(imagePath);
        String direction = imagePath + File.separator + "banner";
        File f = new File(direction);
        if(!f.exists())
            return CommonReturnType.errorWithCode(404, "direction not exist");
        String[] list = f.list();
        List<Object> realPaths = Arrays.stream(list).map(s ->
        {
            return contextPath + "/images/bannar/" + s;
        }).collect(Collectors.toList());
        return CommonReturnType.ok(realPaths);
    }
}
