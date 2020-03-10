package mapreduce.air.sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirSortMapper 
	extends Mapper<LongWritable, Text,CustomKey,IntWritable>{
	CustomKey outputKey = new CustomKey();//맵에서 custom처리가 끝나고, 데이터가 나가서
	//셔플단에서 처리가 된다. outputkey를 
	//output Value
	static final IntWritable one = new IntWritable(1);
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//위 타입은 내가 원하는 것에 따라 변경될 수 있다. 
		if(key.get()>0) {
			String[] line = value.toString().split(",");
			if(line!=null & line.length>0) {
				if(!line[15].equals("NA") && 
						Integer.parseInt(line[15])>0) {
					outputKey.setYear(line[0]);
					outputKey.setMonth(new Integer(line[1]));
					context.write(outputKey, one);
				}
			}
		}
	}
}







