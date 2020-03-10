package mapreduce.air.combiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class AirCombinerDriver extends Configured implements Tool {

	@Override
	public int run(String[] optionlist) throws Exception {
		
		GenericOptionsParser parser = new GenericOptionsParser(getConf(), optionlist);
		
		String[] otherArgs = parser.getRemainingArgs();
		
		Job job = new Job(getConf(), "aircombiner");
		//"aircombiner"는 그냥 표시되는 이름. 이걸 보고 job을 구분한다.
		
		job.setMapperClass(AirCombinerMapper.class);
		job.setCombinerClass(AirCombinerReducer.class);//컴바이너는 미니리듀서
		job.setReducerClass(AirCombinerReducer.class);
		job.setJarByClass(AirCombinerDriver.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		job.waitForCompletion(true);

		return 0;
	}

	public static void main(String[] args) throws Exception {
		
		ToolRunner.run(new Configuration(), new AirCombinerDriver(), args);
		//tool이  AirOptionDriver()

	
	}

}
