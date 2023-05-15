package com.usc.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.util.SaResult;
import com.usc.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Baisong Li
 * @since 2023-04-17
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @SaCheckLogin
    @PostMapping("upload_file")
    public SaResult uploadFile(MultipartFile file, String intro, String filename,String storageSource) throws IOException {
        return fileService.uploadFile(file, intro, filename,storageSource);
    }

    @SaCheckLogin
    @PostMapping("all_file")
    public SaResult allFile() throws IOException {
        return fileService.allFile();
    }

    @SaCheckLogin
    @PostMapping("del_file")
    public SaResult delFile(int fileId) throws IOException {
        return fileService.delFile(fileId);
    }
}
