package org.doit.ik;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.PageDTO;
import org.doit.ik.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j // 밑에 주석이랑 똑같다.
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
	
	// private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// @Autowired
	private BoardService boardService;
	
	/*
	 * @GetMapping("/board/list") public String list(Model model) {
	 * 
	 * // 이걸 쓸때 사용한다. log.info("> BoardController.list()");
	 * 
	 * model.addAttribute("list", this.boardService.getList());
	 * 
	 * return "/board/list"; }
	 */
	
	/* [2] 페이징 처리가 안된 컨트롤러 메서드
	 @GetMapping("/list") // void 줄 때 맵핑 패턴이랑 return이 같으면 생략 가능하다. public
	 public void list(Model model) {
		 log.info("> BoardController.list()");
		 model.addAttribute("list", this.boardService.getList());
	 }
	*/
	
	// 페이징 처리가 된 컨트롤러 메서드
	// http://localhost/board/list
	// http://localhost/board/list?pageNum=3&amount=10
	@GetMapping("/list") // void 줄 때 맵핑 패턴이랑 return이 같으면 생략 가능하다. public
	 public void list(Model model, Criteria criteria) { // 디폴트 생성자에 의해서 자동으로 만들어진다.
		 log.info("> BoardController.list()");
		 model.addAttribute("list", this.boardService.getListWithPaging(criteria));
		 // 페이징 블럭 1 2 [3] 4 5 6 7 8 9 10 >
		 int total = this.boardService.getTotal(criteria);
		 model.addAttribute("pageMaker", new PageDTO(criteria, total));
	 }
	
	/*
	@GetMapping("/board/list")
	public ModelAndView list(ModelAndView mav) {
		
		// 이걸 쓸때 사용한다.
		log.info("> BoardController.list()");
		
		mav.addObject("list", this.boardService.getList());
		mav.setViewName("/board/list");
		
		// return "/board/list";
		return mav;
	}
	*/
	 
	/* <a href="/board/register">글쓰기</a> */
	 @GetMapping("/register") // void 줄 때 맵핑 패턴이랑 return이 같으면 생략 가능하다. public
	 public void register(  ) {
		 log.info("> BoardController.list()");
	 }
	 
	 @PostMapping("/register") // void 줄 때 맵핑 패턴이랑 return이 같으면 생략 가능하다. public
	 public String register( BoardVO board, RedirectAttributes rttr ) { // RedirectAttributes : 리다이렉트할 때 값을 가져간다.
		 log.info("> BoardController.list() POST");
		 this.boardService.register(board);
		 rttr.addFlashAttribute("result", board.getBno());
		 return "redirect:/board/list";
		 // return "redirect:/board/list?bno=2";
		 // return "redirect:/board/list/2";
	 }
	
	 @PostMapping(value = {"/modify"})
	 public String modify(BoardVO boardVO, RedirectAttributes rttr, Criteria criteria) {
		 log.info("> BoardController.modify() POST");
		 
		 if ( this.boardService.modify(boardVO) ) {
			rttr.addFlashAttribute("result", "SUCCESS");
		} // if
		 
		 /* [1]
		 rttr.addAttribute("pageNum", criteria.getPageNum());
		 rttr.addAttribute("amount", criteria.getAmount());
		 rttr.addAttribute("type", criteria.getType());
		 rttr.addAttribute("keyword", criteria.getKeyword());
		 // flash : 일회성
		 // return String.format("redirect:/board/get?bno=%d", boardVO.getBno()); 
		 */
		 
		 // [2]
		 String params = criteria.getListLink();
		 // test : ?pageNum=3&amount=10&type=TC&keyword=%EC%9E%90%EB%B0%94
		 // System.out.println("test : " + params);
		 // 리다이렉트 때문에 안넘어간다.
		 return String.format("redirect:/board/get%s&bno=%d", params, boardVO.getBno());
		 
		// return String.format("redirect:/board/get?bno=%d&pageNum=%d&amount=%d", boardVO.getBno(), criteria.getPageNum(), criteria.getAmount()); 
	 }
	 
	 // [3] @ModelAttribute 사용했다.
	 @GetMapping(value = {"/get", "/modify"})
	 public void get(@RequestParam("bno") Long bno, Model model, @ModelAttribute("criteria") Criteria criteria) {
		 log.info("> BoardController.get()");
		 model.addAttribute("boardVO", this.boardService.get(bno));
	 }
	 
	 // [2]
	 /*
	 @GetMapping(value = {"/get", "/modify"})
	 public void get(@RequestParam("bno") Long bno, Model model, Criteria criteria) {
		 log.info("> BoardController.get()");
		 model.addAttribute("boardVO", this.boardService.get(bno));
		 model.addAttribute("criteria", criteria);
	 }
	 */
	 
		/* [1]
		 * @GetMapping(value = {"/get/{bno}"}) public void get(@PathVariable("bno") Long
		 * bno, Model model) { log.info("> BoardController.get()");
		 * model.addAttribute("boardVO", this.boardService.get(bno)); }
		 */
	 
	 @GetMapping(value = {"/delete"})
	 public String delete(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		 log.info("> BoardController.delete()");
		 
		 if ( this.boardService.delete(bno) ) {
				rttr.addFlashAttribute("result", "SUCCESS");
		} // if
		 
		 return "redirect:/board/list";
	 }
	 
} // class
