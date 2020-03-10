package mapreduce.air.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupKeyComparator extends WritableComparator {


	protected GroupKeyComparator() {
		super(CustomKey.class, true);//내부에서 처리될 수 있게 설정 
	}
	
	//WritableComparable의 타입이 정확하지 않기때문에 warning이 발생하므로, 
	//타입에 대한 부분을 무시해 체크하지 않고 처리하겠다는 의미 (SuppressWarnings)
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable obj1, WritableComparable obj2) {
		//obj1,2는 CustomKey보다 상위타입 이므로 obj1,2에 캐스팅해줘야 한다.
		CustomKey key1 = (CustomKey)obj1;
		CustomKey key2 = (CustomKey)obj2;
		
		return key1.getYear().compareTo(key2.getYear());
	}

}
