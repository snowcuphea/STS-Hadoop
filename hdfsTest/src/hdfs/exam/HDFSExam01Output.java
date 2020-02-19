package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/*
 * hadoop의 hdfs를 api로 제어
 * 	- hadoop hdfs에 api를 이용해서 파일을 생성 
 * 	- 사용자가 지정하는 경로에 파일 생성
 * 
 * output 기능
 * 
 */

public class HDFSExam01Output {

	public static void main(String[] args) {
		// 1.hdfs를 제어하기 위해서 설정 파일을 읽어서 사용해야 하므로 
		//   hadoop 설치 폴더의 설정 파일을 접근하기 위해 제공되는 클래스
		Configuration conf = new Configuration();
		
		// 2. HDFS를 접근하기 위해서 제공되는 객체를 생성 
		FileSystem hdfs = null;
		
		// 3. HDFS에 input하기 위한 스트림객체
		FSDataOutputStream hdfsout = null; //FileWriter fw = null; 과 같다.
		try {
			hdfs = FileSystem.get(conf); //일반경로 아니고 HDFS의 경로
			
			// 4. HDFS의 경로를 표현할 수 있는 객체
			//   => HDFS에 출력할 파일의 경로를 명령행 매개변수로 받아서 적용하겠다는 의미
			Path path = new Path(args[0]);
			//그 경로를 Path객체를 통해 접근할수 있게
			
			// 5. HDFS에 저장된 파일을 읽어야 하므로 input스트림 생성하기
			hdfsout = hdfs.create(path);//output할 수 있는 출력객체가 생김 
			
			// 6. 입력스트림에 데이터를 출력하여 HDFS에 저장
			
		hdfsout.writeUTF(args[1]);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
