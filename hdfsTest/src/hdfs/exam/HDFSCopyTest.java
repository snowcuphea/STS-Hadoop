package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class HDFSCopyTest {

	public static void main(String[] args) {
		Configuration conf = new Configuration();
		
		FileSystem hdfs = null;

		FSDataOutputStream hdfsout = null; 
		FSDataInputStream hdfsin = null; 
		try {
			hdfs = FileSystem.get(conf);
	
			Path pathin = new Path(args[0]); //"output.txt"대신 args[0]로 변경
			hdfsin = hdfs.open(pathin);
			
			Path pathout = new Path(args[1]); //args[0] 대신 args[1]로 변경
			hdfsout = hdfs.create(pathout);
			
			
			//주석 처리한 방식으로 해도 된다. 
			/*while(true) {
				int data = hdfsin.read();
				System.out.println((char)data);
				if(data == -1) {
					break;
				}
				hdfsout.write((char)data);
				
			}*/

			String str = hdfsin.readUTF();
			hdfsout.writeUTF(str);
			System.out.println(str);
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
