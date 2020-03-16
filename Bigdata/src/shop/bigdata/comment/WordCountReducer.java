package shop.bigdata.comment;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	IntWritable resultVal = new IntWritable();
	Text resultKey = new Text();
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		//reduce의 input타입은 map의 output타입과 동일하다.
		
		int sum = 0;

		for (IntWritable value : values) {
			sum = sum + value.get();
		}
		resultVal.set(sum);
		resultKey.set(key+" "); //리듀스에서 나가는 output
		context.write(resultKey, resultVal);
	}
}
