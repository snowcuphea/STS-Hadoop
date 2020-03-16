package shop.bigdata.comment;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper 
	extends Mapper<LongWritable, Text, Text, IntWritable> {	
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();
	StringBuffer sb = new StringBuffer();
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		Pattern p = Pattern.compile("은$|는$|이$|가$|요$|서$|과$|에$");
		StringTokenizer st =
				   new StringTokenizer(value.toString()," ");
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			Matcher m = p.matcher(token);
			if(m.find()) {
				m.appendReplacement(sb, "");
				outputKey.set(sb.toString());
			}else {
				outputKey.set(token);
			}
			context.write(outputKey, outputVal);
			sb.setLength(0);
		}
	}
}

/*		
Pattern p = Pattern.compile("은$|는$|이$|가$|요$|서$|과$|에$");
Matcher m = p.matcher(value.toString());

StringBuffer sb = new StringBuffer();
while (m.find()) {
	String data = m.group();//패턴과 일치하는 단어가 있는지 골라내는 작업
	//System.out.println(data);
	m.appendReplacement(sb, ""); //sb에서 패턴에 맞는 단어가 있으면, 공백으로 치환한다.
	//패턴에 만족하는 문자열을 ""로 치환한 후 전체 문자열을 StringBuffer에 저장
	// '\', '$' 는 치환 문자로 사용할 수 없다. 	
}

StringTokenizer st =
		   new StringTokenizer(sb.toString()," ");
while(st.hasMoreTokens()) {
	String token = st.nextToken();
	outputKey.set(token);
	context.write(outputKey, outputVal);
}*/






