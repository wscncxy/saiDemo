package com.sai.demo.hadoop;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

@Slf4j
public class MyFirstReducer extends Reducer<Text, LongWritable, Text, LongWritable> {


    protected void reduce(Text key, Iterable<LongWritable> values, Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {    long maxValue = Long.MIN_VALUE;
        Iterator<LongWritable> iterator = values.iterator();
        while (iterator.hasNext()) {
            LongWritable longWritable =iterator.next();
            System.out.println(longWritable.get());
            maxValue = Math.max(maxValue, longWritable.get());
        }
        context.write(key, new LongWritable(maxValue));
    }

}