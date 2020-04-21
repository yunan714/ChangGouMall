package com.changgou.file;

import java.io.Serializable;

/**
 * 封装文件上传信息
 * 时间
 * 上传人
 * 类型
 * 大小
 * 后缀
 * 内容->字节数组
 *
 */
public class FastDFSFile implements Serializable {//序列化以保存到本地
    private String name;
    private byte[] content;
    private String ext;//扩展名 jpg png gif docx txt
    private String md5;
    private String author;

    public FastDFSFile(String name, byte[] content, String ext, String md5,
                        String author){
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.md5 = md5;
        this.author = author;
    }
    public FastDFSFile(String name, byte[] content, String ext){
        this.name = name;
        this.content = content;
        this.ext = ext;
    }
    public FastDFSFile(){
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
