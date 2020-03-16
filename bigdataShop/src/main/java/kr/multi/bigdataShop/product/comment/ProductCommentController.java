package kr.multi.bigdataShop.product.comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.multi.bigdataShop.board.BoardDTO;
import kr.multi.bigdataShop.product.ProductDTO;
import kr.multi.bigdataShop.result.comment.ResultCommentDTO;
import kr.multi.bigdataShop.result.comment.ResultCommentService;

@Controller
public class ProductCommentController {
	@Autowired
	ProductCommentService service;
	
	@Autowired
	ResultCommentService resultservice;

	@RequestMapping(value = "/comment/write.do", method = RequestMethod.POST)
	public String productWrite(ProductCommentDTO comment) {
		System.out.println("comment:" + comment);
		service.commentinsert(comment);
		return "redirect:/product/read.do?prd_no=" + comment.getPrd_no();
	}

	@RequestMapping("/comment/result.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		List<ResultCommentDTO> resultlist = resultservice.resultlist();
		mav.addObject("resultlist", resultlist);
		mav.setViewName("comment/result");
		return mav;
	}

}
