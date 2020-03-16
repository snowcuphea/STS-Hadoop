package kr.multi.bigdataShop.result.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class ResultCommentServiceImpl implements ResultCommentService {
	@Autowired
	@Qualifier("resultcommentdao")
	ResultCommentDAO dao;

	@Override
	public List<ResultCommentDTO> resultlist() {
		
		return dao.resultlist();
	}

	



}











