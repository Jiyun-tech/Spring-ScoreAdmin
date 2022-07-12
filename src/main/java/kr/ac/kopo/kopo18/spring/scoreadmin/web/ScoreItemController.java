package kr.ac.kopo.kopo18.spring.scoreadmin.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreItem;
import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreList;
import kr.ac.kopo.kopo18.spring.scoreadmin.dto.Pagination;
import kr.ac.kopo.kopo18.spring.scoreadmin.repository.ScoreListRepository;
import kr.ac.kopo.kopo18.spring.scoreadmin.service.ScoreItemService;

@Controller
@Transactional
// Controller -> Component : Spring 내에서는 Singleton으로 처리 
// ==> 객체를 하나만 만들어놓고 Spring이 들고 있다가, @Autowired 발견 시 객체 연결해 줌
// 참고) @ResponseBody : json file로 데이터 받는 것. 
// 		-> view.jsp로 화면단 만들려면 사용하면 안 됨! (json data로 받으면 ajax로 화면 구성)
public class ScoreItemController {

	// @Autowired
	// -> Web Application 내에 Singleton으로 존재(하나만 존재)하는 객체를 Spring으로부터 받아옴
	// = DI (Dependency Injection)
	@Autowired
	private ScoreItemService scoreItemService;
	@Autowired
	private ScoreListRepository scoreListRepository;
	
	// 선택 학생 조회 --> POST 방식
	@RequestMapping (value = "/viewOneStudent", method=RequestMethod.POST)
	public String viewSelectedStudent(Model model, @RequestParam(value="listId") Long listId, 
													@RequestParam(value="cPage", defaultValue="1") int currentPage, 
													@RequestParam(value="count", defaultValue="10") int countPerPage,
													@RequestParam(value="pageSize", defaultValue="10") int pageSize,
													@RequestParam(value="target", defaultValue="0") Long target,
													@RequestParam(value="function", defaultValue="") String function) {
		
		int LineCount = (currentPage-1)*countPerPage;
		
		ScoreList scoreList = scoreListRepository.findOneById(listId);
		
		List<ScoreItem> scoreItems = (List<ScoreItem>) scoreListRepository.findOneById(listId).getScoreItems();
		List<ScoreItem> scoreItemList = scoreItemService.findCurrentPageWithList(currentPage, countPerPage, scoreItems);
//		List<ScoreItem> scoreItemList = scoreItemService.findAllByScoreListId(id, PageRequest.of(currentPage-1, countPerPage));
		
		int totalCount = scoreItems.size();
		Pagination p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);
		
		if (target == 0) {
			target = listId;
		}
		
		model.addAttribute("listId",listId);
		model.addAttribute("LineCount", LineCount);
		model.addAttribute("get_list", scoreList);
		model.addAttribute("get_item", scoreItemList);
		model.addAttribute("get_pagination", p);
		model.addAttribute("target", target);
		model.addAttribute("function", function);
		
		return "viewOneStudent";
	}
	
	// 선택 학생 조회 --> GET 방식
	@RequestMapping (value = "/viewOneStudent", method=RequestMethod.GET)
	public String viewSelectedStudent2(Model model, @RequestParam(value="listId") Long listId, 
													@RequestParam(value="cPage", defaultValue="1") int currentPage, 
													@RequestParam(value="count", defaultValue="10") int countPerPage,
													@RequestParam(value="pageSize", defaultValue="10") int pageSize,
													@RequestParam(value="target", defaultValue="0") Long target,
													@RequestParam(value="function", defaultValue="") String function) {
		
		int LineCount = (currentPage-1)*countPerPage;
		
		ScoreList scoreList = scoreListRepository.findOneById(listId);
		
		List<ScoreItem> scoreItems = (List<ScoreItem>) scoreListRepository.findOneById(listId).getScoreItems();
		List<ScoreItem> scoreItemList = scoreItemService.findCurrentPageWithList(currentPage, countPerPage, scoreItems);
//		List<ScoreItem> scoreItemList = scoreItemService.findAllByScoreListId(id, PageRequest.of(currentPage-1, countPerPage));
		
		int totalCount = scoreItems.size();
		Pagination p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);
		
		if (target == 0) {
			target = listId;
		}
		
		
		model.addAttribute("listId",listId);
		model.addAttribute("LineCount", LineCount);
		model.addAttribute("get_list", scoreList);
		model.addAttribute("get_item", scoreItemList);
		model.addAttribute("get_pagination", p);
		model.addAttribute("target", target);
		model.addAttribute("function", function);
		
		return "viewOneStudent";
	}
	
	// 성적 추가 : html 페이지로 정보 넘겨주기
	@RequestMapping (value = "/addScoreItem", method=RequestMethod.POST)
	public String addScoreItem(Model model, @RequestParam(value="listId") long listId,
											@RequestParam(value="name") String name,
											@RequestParam(value="studentid") int studentid) {
		
		model.addAttribute("get_id", listId);
		model.addAttribute("get_name", name);
		model.addAttribute("get_studentid", studentid);
		
		return "addScoreItem";
	}
	
	// 성적 추가 : 성적 추가 쿼리 실행 & 결과 확인
	@RequestMapping (value = "/addScoreItem_result", method=RequestMethod.POST)
	public String addScoreItem_result(Model model,
											@RequestParam(value="cPage", defaultValue="1") int currentPage, 
											@RequestParam(value="count", defaultValue="10") int countPerPage,
											@RequestParam(value="pageSize", defaultValue="10") int pageSize,
											@RequestParam(value="listId") long listId,
											@RequestParam(value="name") String name,
											@RequestParam(value="studentid") int studentid,
											@RequestParam(value="test") String test,
											@RequestParam(value="kor") int kor,
											@RequestParam(value="eng") int eng,
											@RequestParam(value="mat") int mat){
		// Create ScoreItem;
		ScoreList scoreList = scoreListRepository.findOneById(listId);
		ScoreItem scoreItem = new ScoreItem();
		scoreItem.setTest(test);
		scoreItem.setKor(kor);
		scoreItem.setEng(eng);
		scoreItem.setMat(mat);
		scoreItem.setScoreList(scoreList);
		scoreItemService.save(scoreItem);
		
		int LineCount = (currentPage-1)*countPerPage;
		
		List<ScoreItem> scoreItems = (List<ScoreItem>) scoreListRepository.findOneById(listId).getScoreItems();
		
		List<ScoreItem> scoreItemList = scoreItemService.findCurrentPageWithList(currentPage, countPerPage, scoreItems);
		int totalCount = scoreItems.size();
		Pagination p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);
		
		String function = "addScoreItem";
		
		
		model.addAttribute("listId",listId);
		model.addAttribute("LineCount", LineCount);
		model.addAttribute("get_list", scoreList);
		model.addAttribute("get_item", scoreItemList);
		model.addAttribute("get_pagination", p);
		model.addAttribute("target", scoreItem.getId());
		model.addAttribute("function", function);
		
		return "redirect:viewOneStudent?listId="+listId+"&target"+scoreItem.getId();
	}
	
	// 학생 성적 수정 (수정 실행 & 결과 출력)
	@RequestMapping (value = "/updateScoreItem_result", method=RequestMethod.POST)
	public String updateScoreItem(Model model,
									@RequestParam(value="listId") Long listId,
									@RequestParam(value="itemId") Long itemId,
									@RequestParam(value="name") String name,
									@RequestParam(value="studentid") int studentid,
									@RequestParam(value="test") String test,
									@RequestParam(value="kor") int kor,
									@RequestParam(value="eng") int eng,
									@RequestParam(value="mat") int mat,
									@RequestParam(value="cPage", defaultValue="1") int currentPage, 
									@RequestParam(value="count", defaultValue="10") int countPerPage,
									@RequestParam(value="pageSize", defaultValue="10") int pageSize) {
		
		// scoreItem 수정 (학생 성적 수정)
		ScoreItem scoreItem = scoreItemService.findOneById(itemId);
		scoreItem.setTest(test);
		scoreItem.setKor(kor);
		scoreItem.setEng(eng);
		scoreItem.setMat(mat);
		scoreItemService.save(scoreItem);
		
		// 데이터 출력
		int LineCount = (currentPage-1)*countPerPage;
		
		ScoreList scoreList = scoreListRepository.findOneById(listId);
		List<ScoreItem> scoreItems = (List<ScoreItem>) scoreList.getScoreItems();
		
		List<ScoreItem> scoreItemList = scoreItemService.findCurrentPageWithList(currentPage, countPerPage, scoreItems);
		int totalCount = scoreItems.size();
		Pagination p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);	
		
		String function = "updateScoreItem";
		
		model.addAttribute("listId",listId);
		model.addAttribute("LineCount", LineCount);
		model.addAttribute("get_list", scoreList);
		model.addAttribute("get_item", scoreItemList);
		model.addAttribute("get_pagination", p);
		model.addAttribute("target", itemId);
		model.addAttribute("function", function);
		
		return "viewOneStudent";
	}
	
	// 학생 성적 수정 (to 정정 페이지)
	@RequestMapping (value="/updateScoreItem", method=RequestMethod.POST)
	public String updateScoreItem2(Model model,
											@RequestParam(value="listId") long listId,
											@RequestParam(value="itemId") long itemId,
											@RequestParam(value="name") String name,
											@RequestParam(value="studentid") int studentid) {

		ScoreItem scoreItem = scoreItemService.findOneById(itemId);
		
		model.addAttribute("get_listId", listId);
		model.addAttribute("get_itemId", itemId);
		model.addAttribute("get_name", name);
		model.addAttribute("get_studentid", studentid);
		model.addAttribute("get_kor", scoreItem.getKor());
		model.addAttribute("get_eng", scoreItem.getEng());
		model.addAttribute("get_mat", scoreItem.getMat());
		
		return "updateScoreItem";
}
	
	// 성적 삭제 (POST)
	@RequestMapping (value = "/deleteScoreItem", method=RequestMethod.POST)
	public String deleteScoreItem(Model model,
										@RequestParam(value="cPage", defaultValue="1") int currentPage, 
										@RequestParam(value="count", defaultValue="10") int countPerPage,
										@RequestParam(value="pageSize", defaultValue="10") int pageSize,
										@RequestParam(value="listId") long listId,
										@RequestParam(value="itemId") long itemId,
										@RequestParam(value="name") String name,
										@RequestParam(value="studentid") int studentid,
										@RequestParam(value="test") String test) {
		
		// Score Item 삭제
		scoreItemService.deleteById(listId, itemId);
		
		// 목록 출력
		int LineCount = (currentPage-1)*countPerPage;
		
		ScoreList scoreList = scoreListRepository.findOneById(listId);
		List<ScoreItem> scoreItems = (List<ScoreItem>) scoreList.getScoreItems();
		
		List<ScoreItem> scoreItemList = scoreItemService.findCurrentPageWithList(currentPage, countPerPage, scoreItems);
		int totalCount = scoreItems.size();
		Pagination p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);
		
		
		String function = "deleteScoreItem";
		
		model.addAttribute("listId",listId);
		model.addAttribute("itemId", itemId);
		model.addAttribute("LineCount", LineCount);
		model.addAttribute("get_list", scoreList);
		model.addAttribute("get_item", scoreItemList);
		model.addAttribute("get_pagination", p);
		model.addAttribute("target", itemId);
		model.addAttribute("function", function);
		
		return "viewOneStudent";
	}
	

	
	
}
