package mapred.basic;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.transform.OutputKeys;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//Mapper
/*
 * 1. Mapper클래스를 상속한다.
 * => mapper에 전달될 input테이터의 key, value타입과
 *    mapper의 실행결과로 출력되는 output데이터의 key, value타입을 정의
 */

/*
 * 2. map메소드를 오버라이딩해서 map작업을수행하면서 처리할 내용을 구현
 * => 입력된 값을 분석하기 위한 메소드 : 입력된 데이터에 조건을 적용해서 
 * 							       원하는 데이터를 추출하기 위한 반복작업 수행 
 *    map메소드의 매개변수 - 입력데이터 키, 입력값, context
 *									      -------?   									 
 *	  Context : 맵리듀스 작업을 수행하며 맵메소드의 실행결과
 *				즉, 출력데이터를 기록하고, shuffle하고, 리듀서로 내보내는 작업을 수행하는 객체
 *				프레임워크 내부에서 기본작업을 처리하는 객체  (기본 객체)
 *				내부에서 머신들끼리 통신할 때 필요한 여러가지 정보를 갖고 있다.
 *				(내부와 프레임워크 연결)
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	//output데이터를 mapper의 실행결과로 내보낼 수 있도록 key와 value를 저장하는 변수 선언
	//output데이터의 value는 무조건 1이므로 상수로 정의
	static final IntWritable outputVal = new IntWritable(1);
	//output데이터의 key는 문자열이므로 Text타입으로 정의
	Text outputKey = new Text();
	
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//key는 linenumber, value는 입력데이터의 한 라인에 해당하는 문장 
		//value는 입력데이터의 한 라인에 해당하는 문장 ex)read a book
		StringTokenizer st = new StringTokenizer(value.toString());//타입맞추려고 toString()
		
		while(st.hasMoreTokens()) { //잘라낸 토큰이 존재할때까지 반복
			//[read a book]에서 read가 토큰1, a가 토큰2, book이 토큰3
			String token = st.nextToken();
			outputKey.set(token); // output데이터의 키를 세팅
			//Context객체의 write메소드를 통해서 output으로 내보낼 데이터의 key와 value를 정의
			context.write(outputKey, outputVal);
			
		}
		
		super.map(key, value, context);
	}
	//Int형데이터로는 모자라서 Long타입으로 LongWritable
	//LongWritable과 Text가 mapper로 들어가는 input data타입. 
	//3번째 변수 key는(output) Text타입
	//4번째 변수 Value는(output) intWritable

}
