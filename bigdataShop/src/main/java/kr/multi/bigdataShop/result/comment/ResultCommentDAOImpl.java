package kr.multi.bigdataShop.result.comment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("resultcommentdao")
public class ResultCommentDAOImpl implements ResultCommentDAO {
	@Autowired
	SqlSession sqlSession;


	@Override
	public List<ResultCommentDTO> resultlist() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("kr.multi.bigdataShop.result.comment.resultlist");
	}
	

}













