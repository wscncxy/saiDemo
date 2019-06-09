package com.sai.demo.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;

public class HadoopTestCase {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("args error");
            System.exit(-1);
            return;
        }
        try {
            Job job = Job.getInstance();
            job.setJarByClass(HadoopTestCase.class);
            job.setJobName("HadoopTestCase");

            JobConf jobConf = (JobConf) job.getConfiguration();
            FileInputFormat.addInputPath(jobConf, new Path(args[0]));
            FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));

            job.setMapperClass(MyFirstMapper.class);
            job.setReducerClass(MyFirstReducer.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(LongWritable.class);

            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
