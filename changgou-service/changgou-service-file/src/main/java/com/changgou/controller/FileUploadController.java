package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="/file")
@CrossOrigin
public class FileUploadController {

    @PostMapping("/upload")
    public Result upolad (@RequestParam(value="file") MultipartFile file) throws Exception{
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename())
        );

        //调用工具类
        String[] uploads = FastDFSUtil.upload(fastDFSFile);
        //需要将其返回到页面中
        //拼接访问地址 url = http://192.168.211.131:8080/..访问地址..
        String url = FastDFSUtil.getTrackerInfo()+ "/" + uploads[0] + "/" + uploads[1];
        return new Result(true, StatusCode.OK,"上传成功", url);
    }
}
