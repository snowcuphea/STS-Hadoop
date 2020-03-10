package mapreduce.air.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

//Partitioner클래스에서는 Mapper의 key와 value타입을 generic으로 명시
//year를 기준으로 해시코드를 구해서 같은 year를갖고 있는 데이터를
//같은리듀서에서 작업할 수 있도록 분배
//==> 같은 것끼리 메모리버퍼에 쌓았다가 한꺼번에 전송

public class AirSortPartitioner //파티셔너 : 어디로 보낼건지 계산하는 것
	extends Partitioner<CustomKey, IntWritable>{
	///몇번 태스크를 보내야하는지 계산하는것.계산하는 기준이 같은 키를 갖고있는 것은 같은 곳으롭 보낸다.
	//일단 year를 기준으로 해서 모은다. 
	//numPartitions는 파티션의 갯수 = 리듀스태스크의 갯수
	
	//우리 기준은 year기때문에 year를 기준으로 같은 것끼리 모으는 작업을 하기 위해 커스터마이징 한 것이다.
	@Override
	public int getPartition(CustomKey key, IntWritable value, int numPartitions) {
		
		
		return key.getYear().hashCode() % numPartitions;//리듀스태스크의 번호 리턴(%하고난 나머지 번호)
		//해쉬코드가 같으면 문자열이 같다는 뜻이다. 
	}
	
	
	

}
