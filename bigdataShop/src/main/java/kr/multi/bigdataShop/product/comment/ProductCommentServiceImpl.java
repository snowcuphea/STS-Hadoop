package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class ProductCommentServiceImpl implements ProductCommentService {
	@Autowired
	@Qualifier("productcommentdao")
	ProductCommentDAO dao;

	
	@Override
	public int commentinsert(ProductCommentDTO comment) {
		// TODO Auto-generated method stub
		return dao.commentinsert(comment);
	}


	@Override
	public List<ProductCommentDTO> commentlist(String prdno) {
		// TODO Auto-generated method stub
		return dao.commentlist(prdno);
	}


}











