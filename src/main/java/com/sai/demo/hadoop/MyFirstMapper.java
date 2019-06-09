package com.sai.demo.hadoop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

@Slf4j
public class MyFirstMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        System.out.println("Key:" + key + "   Val:" + line);
        String[] vals = line.split(",");

        System.out.println("Vals:" + vals.length+":"+vals[0]);
        long groupId = Long.parseLong(vals[0]);
        long userCount = Long.parseLong(vals[1]);
        context.write(new Text(groupId + ""), new LongWritable(userCount));
    }
}

