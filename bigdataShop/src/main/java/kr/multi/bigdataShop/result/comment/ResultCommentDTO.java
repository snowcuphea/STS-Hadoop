package kr.multi.bigdataShop.result.comment;


public class ResultCommentDTO {
	public String keyword;
	public String counter;


	public ResultCommentDTO(){
		
	}


	public ResultCommentDTO(String keyword, String counter) {
		super();
		this.keyword = keyword;
		this.counter = counter;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getCounter() {
		return counter;
	}


	public void setCounter(String counter) {
		this.counter = counter;
	}


	@Override
	public String toString() {
		return "ResultCommentDTO [keyword=" + keyword + ", counter=" + counter + "]";
	}
	
	


	
	

}