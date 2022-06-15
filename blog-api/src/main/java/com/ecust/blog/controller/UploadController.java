package com.ecust.blog.controller;


import com.ecust.blog.utils.QiniuUtils;
import com.ecust.blog.vo.ErrorCode;
import com.ecust.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @authour shuangliang
 * @date 2022/6/5 - 11:26
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Resource
    private QiniuUtils qiniuUtils;

    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file) {
        // 原始文件名称
        String originalFilename = file.getOriginalFilename();
        // 唯一文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        // 上传文件位置 七牛云 云服务器 按量付费速度快 把图片发送到离用户最近的服务器上,访问速度快
        // 降低自身应用服务器带宽消耗
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload) {
            // 上传成功,返回上传的url
            return Result.success(QiniuUtils.url + fileName);
        }
        return Result.fail(ErrorCode.UPLOAD_ERROR.getCode(), ErrorCode.UPLOAD_ERROR.getMsg());
    }
}
