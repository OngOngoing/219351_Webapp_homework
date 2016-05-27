import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TwoLinkHa {

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, Text> {

        private Text fromNode = new Text();
        private Text toNode = new Text();
        private Text pathNode = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            if (value.toString().charAt(0) != '#') {
                StringTokenizer itr = new StringTokenizer(value.toString());

                String fNode = itr.nextToken();
                String tNode = itr.nextToken();
                fromNode.set(fNode);
                toNode.set(tNode);
                pathNode.set(fNode+ ":" + tNode);

                context.write(fromNode, toNode);
                context.write(toNode, pathNode);
            }
        }
    }

    public static class TokenizerMapper2
            extends Mapper<Object, Text, Text, Text> {

        private Text fromNode = new Text();
        private Text toNode = new Text();
        private Text pathNode = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            if (value.toString().charAt(0) != '#') {
                StringTokenizer itr = new StringTokenizer(value.toString());

                String fNode = itr.nextToken();
                String tNode = itr.nextToken();
                fromNode.set(fNode);
                toNode.set(tNode);

                context.write(fromNode, toNode);

            }
        }
    }


    public static class IntSumReducer2
            extends Reducer<Text, Text, Text, Text> {

        public void reduce(Text key, Iterable<Text> values,
                           Context context
        ) throws IOException, InterruptedException {

            String reuslt = "-- [";
            for (Text value : values) {
                reuslt += value + ", ";
            }
            reuslt += "]";

            context.write(key, new Text(reuslt));
        }
    }

    public static class IntSumReducer
            extends Reducer<Text, Text, Text, Text> {

        public void reduce(Text key, Iterable<Text> values,
                           Context context
        ) throws IOException, InterruptedException {
            List<String> sourceNodes = new ArrayList<String>();
            List<String> singleNodes = new ArrayList<String>();
            for (Text val : values) {
                String _val = val.toString();
                String[] path;
                if (!_val.contains(":")) {
                    singleNodes.add(_val);
                } else {
                    path = _val.split(":");
                    if (path.length == 2) {
                        sourceNodes.add(path[0]);
                    }
                }
            }
            for (String sourceNode : sourceNodes) {
                for (String singleNode : singleNodes) {
                    context.write(new Text(sourceNode), new Text(singleNode));
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf1 = new Configuration();
        Job job1 = Job.getInstance(conf1);
        job1.setJobName("link count");
        job1.setJarByClass(TwoLinkHa.class);
        job1.setMapperClass(TokenizerMapper.class);
        job1.setReducerClass(IntSumReducer.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);

        Path tempOutput = new Path("temp");
        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, tempOutput);
        tempOutput.getFileSystem(conf1).delete(tempOutput, true);
        job1.waitForCompletion(true);

        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2);
        job2.setJobName("link count_2");
        job2.setJarByClass(TwoLinkHa.class);
        job2.setMapperClass(TokenizerMapper2.class);
        job2.setReducerClass(IntSumReducer2.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);

        Path outputPath = new Path("out");
        FileInputFormat.addInputPath(job2, tempOutput);
        FileOutputFormat.setOutputPath(job2, outputPath);
        outputPath.getFileSystem(conf2).delete(outputPath, true);
        System.exit(job2.waitForCompletion(true) ? 0 : 1);
    }
}

