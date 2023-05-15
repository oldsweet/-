package com.usc.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.model.CreateBucketRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class COSUtils {
    // https://cloud.tencent.com/document/product/436/10199 腾讯对象存储文档
    static String secretId = "AKID**************UJKNMSVTXdeaIm"; // 腾讯对象存储的secretId
    static String secretKey = "KenjV6ZonfP2TzG******jMpFVYYQ";// 腾讯对象存储的secretKey
    static String bucket = "idc-13045****08"; // 腾讯对象存储的buketName
    static String position = "ap-nanjing";// region 代号

    /**
     * 上次文件到 腾讯云
     * @param file
     * @param filePath
     * @return 文件存储的url
     */
    public static String uploadFile(MultipartFile file, String filePath) {
        String url = "";
        Region region = new Region(position);
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
        createBucketRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        try {
            // 指定要上传的文件
            File localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            String key = filePath;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        url = "https://" + bucket + ".cos." + position + ".myqcloud.com/" + filePath;
        return url;
    }
}
