package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 文件管理
 * 上传
 * 删除
 * 下载
 * 文件信息获取
 * storage信息
 * tracker信息
 */
public class FastDFSUtil {
    /**
     * 加载tracker连接信息
     */
    //只在类加载的时候被执行，且只执行一次
    static{
        try{
            String filename = new ClassPathResource("fdfs_client.conf").getPath();
            ClientGlobal.init(filename);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception{
        //附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", fastDFSFile.getAuthor());
        //访问tracker,创建tracker访问对象trackerClient
        TrackerClient trackerClient = new TrackerClient();
        
        //通过trackerClient访问trackerServer服务获取连接信息
        TrackerServer trackServer = trackerClient.getConnection();
        
        //通过trackerServer的连接信息获取Storage的连接信息,创建对象存储storage的连接信息
        StorageClient storageClient = new StorageClient(trackServer, null);

        //通过storagclient访问storage实现文件上传
        /**
         * 1.上传文件字节数组
         * 2.扩展名
         * 3.附加参数
         */

        /**
         * upload返回储存的上传信息
         * uploads[0]:文件上传所储存的storage组名字 group1
         * uploads[1]:文件储存到storage上的文件名字 M00/02/33/ xxx.jpg
         *
         */
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
        //返回文件上传后存储信息
        return uploads;
        
    }

    /**
     * 获取文件信息
     * @param groupName 文件组名
     * @param remoteFileName 文件存储路径
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws Exception{
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //获取文件信息
        return storageClient.get_file_info(groupName, remoteFileName);

    }

    /**
     * 文件下载
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) throws Exception{
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //下载
        // 返回字节输入流
        byte[] buffer = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(buffer);
    }

    /**
     * 删除文件
     */
    public static void deleteFile(String groupName, String remoteFileName)throws Exception{
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //下载
        // 返回字节输入流
        storageClient.delete_file(groupName, remoteFileName);
        //删完之后浏览器缓存中仍旧存在数据，因此通过配置nginx来删除
    }

    /**
     * 获取storage信息
     * @throws Exception
     */
    public static StorageServer[] getStorages() throws Exception{
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorages(trackerServer);
    }

    /**
     * 获取storage的ip和端口信息
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws Exception{
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();

        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    /**
     * 获取tracker信息
     * @throws Exception
     */
    public static String getTrackerInfo() throws Exception{
        TrackerServer trackerServer = getTrackerServer();
        // ip http端口
        int tracker_http_port = ClientGlobal.getG_tracker_http_port();
        String ip = trackerServer.getInetSocketAddress().getHostString();
        String url = "http://" + ip + ":" + tracker_http_port;
        return url;

    }

    public static TrackerServer getTrackerServer() throws Exception{
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return  trackerServer;
    }

    public static void main(String[] args) throws Exception{
//        //文件下载
//        InputStream is = downloadFile("group1","M00/00/00/wKjTg16e6LOAGC6XAAAcLlj9M2U757.png");
//        //将文件写入本地磁盘
//        FileOutputStream os = new FileOutputStream("D:/1.jpg");
//        //定义一个缓冲区
//        byte[] buffer = new byte[1024];
//        while(is.read(buffer)!=-1){
//            os.write(buffer);
//        }
//        os.flush();
//        os.close();
//        is.close();

        //获取storage信息
//        StorageServer storageServer = getStorages();
//        System.out.println(storageServer.getInetSocketAddress().getHostString());
//        System.out.println(storageServer.getStorePathIndex());

        System.out.println(getTrackerInfo());

    }

}
