package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("productcommentdao")
public class ProductComentDAOImpl implements ProductCommentDAO {
	@Autowired
	SqlSession sqlSession;

	@Override
	public int commentinsert(ProductCommentDTO comment) {
		// TODO Auto-generated method stub
		int result = sqlSession.insert("kr.multi.bigdataShop.product.comment.commentinsert",comment);
		return result;
	}
	@Override
	public List<ProductCommentDTO> commentlist(String prdno) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("kr.multi.bigdataShop.product.comment.commentlist",prdno);
	}



}













