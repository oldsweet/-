package com.usc.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.usc.entity.File;
import com.usc.mapper.FileMapper;
import com.usc.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usc.utils.COSUtils;
import com.usc.utils.OSSUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Baisong Li
 * @since 2023-04-17
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Override
    public SaResult uploadFile(MultipartFile file, String intro, String filename, String storageSource) throws IOException {
        try {
            File fileEntity = new File();
            fileEntity.setIntro(intro);
            fileEntity.setFilename(filename == null ? file.getOriginalFilename() : filename);
            String url = "";
            if (storageSource.equals("tecentyunoss")) {
                fileEntity.setStorage("tecentyunoss");
                url = COSUtils.uploadFile(file, "/" + StpUtil.getLoginIdAsInt() + "_" + filename);
            }
            if (storageSource.equals("aliyunoss")) {
                fileEntity.setStorage("aliyunoss");
                url = OSSUtils.uploadFile(file, StpUtil.getLoginIdAsInt() + "_" + filename);
            }
            fileEntity.setUrl(url);
            fileEntity.setIsDir(false);
            fileEntity.setCreateTime(new Date());
            fileEntity.setUpdateTime(new Date());
            fileEntity.setUserId(StpUtil.getLoginIdAsInt());
            this.save(fileEntity);
            return SaResult.ok("上传成功").setData(fileEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return SaResult.error("上传失败");
        }

    }

    @Override
    public SaResult allFile() {
        int userId = StpUtil.getLoginIdAsInt();
        List<File> fileList = this.list(new QueryWrapper<File>().eq("user_id", userId));
        return SaResult.ok("获取文件列表成功")
                .setData(fileList);
    }

    @Override
    public SaResult delFile(int fileId) {
        int userId = StpUtil.getLoginIdAsInt();
        File file = this.getById(fileId);
        if (file == null) {
            return SaResult.error("file id 错误");
        } else {
            if (file.getUserId() == userId) {
                try {
                    this.removeById(fileId);
                    // 删除远程对象存储
                    OSSUtils.delFile(OSSUtils.urlToObjectName(file.getUrl()));
                    return SaResult.ok("文件删除成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    return SaResult.error("文件删除失败");
                }
            } else {
                return SaResult.error("你没有权限删除该文件");
            }
        }

    }

}
