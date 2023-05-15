package com.usc.service;

import cn.dev33.satoken.util.SaResult;
import com.usc.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Baisong Li
 * @since 2023-04-17
 */
public interface FileService extends IService<File> {

   public SaResult uploadFile(MultipartFile file, String intro, String filename,String storageSource) throws IOException;

   public SaResult allFile();

   public SaResult delFile(int fileId);
}
