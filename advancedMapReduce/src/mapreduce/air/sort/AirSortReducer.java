package mapreduce.air.sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AirSortReducer extends Reducer<CustomKey, IntWritable, CustomKey, IntWritable> {
	// CustomKey타입으로 받고, 출력하기때문에 CustomKey로 적어준다.
	IntWritable resultVal = new IntWritable();
	CustomKey resultKey = new CustomKey();

	// 위와 마찬가지로 CustomKey로 적어준다.
	@Override
	protected void reduce(CustomKey key, Iterable<IntWritable> values,
			Reducer<CustomKey, IntWritable, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		
		Integer beforeMonth = key.getMonth();// month데이터를 추출 
		for (IntWritable value : values) {
			if (beforeMonth != key.getMonth()) { // 달이 올라가면서 비교
				// 이전것과 같으면 계속 더해주는 작업을 하다가, 달라지면 더하지 않음
				resultKey.setYear(key.getYear());
				resultKey.setMonth(beforeMonth);
				resultVal.set(sum);
				context.write(resultKey, resultVal);	
				sum = 0;
			}
			sum = sum + value.get();
			beforeMonth = key.getMonth();
		}
		//values에 전달된 값들을 반복작업하며 마지막에 같은 키를 갖고있는 값을 한꺼번에 출력
		// - 키 (year, month)가 같은 경우 
		if(key.getMonth() == beforeMonth) {
			resultKey.setYear(key.getYear());
			resultKey.setMonth(key.getMonth());
			resultVal.set(sum);
			context.write(resultKey, resultVal);	
		}

	}
}
