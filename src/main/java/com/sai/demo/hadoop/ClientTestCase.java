package com.sai.demo.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class ClientTestCase {
    public static void main(String[] args) throws Exception {
        FSDataInputStream inputStream = null;
        FileSystem fs = null;
        try {

            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://47.52.27.206:9000");
            conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");

            fs = FileSystem.get(conf);
            String filePath = "/home/sai/test.txt";
//            System.out.println(fs.isFile(new Path("hdfs://47.52.27.206:9000/home/sai")));
//            System.out.println(fs.isDirectory(new Path("hdfs://47.52.27.206:9000/home/sai")));
            inputStream= fs.open(new Path(filePath));
            byte[] b = new byte[1024];
            IOUtils.copyBytes(inputStream, System.out, 4096, false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                fs.close();
            }
        }
    }
}
