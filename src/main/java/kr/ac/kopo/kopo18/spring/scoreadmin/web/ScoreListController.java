package kr.ac.kopo.kopo18.spring.scoreadmin.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreList;
import kr.ac.kopo.kopo18.spring.scoreadmin.dto.Pagination;
import kr.ac.kopo.kopo18.spring.scoreadmin.repository.ScoreListRepository;
import kr.ac.kopo.kopo18.spring.scoreadmin.service.ScoreItemService;

@Controller
@Transactional
public class ScoreListController {
	
	@Autowired
	private ScoreListRepository scoreListRepository;
	@Autowired
	private ScoreItemService scoreItemService;
	
	@RequestMapping (value = "/viewScoreList", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value="cPage", defaultValue="1") int currentPage, 
									@RequestParam(value="count", defaultValue="10") int countPerPage,
									@RequestParam(value="pageSize", defaultValue="10") int pageSize,
									@RequestParam(value="function", defaultValue="selectAll") String function,
									@RequestParam(value="name", defaultValue="") String name, 
									@RequestParam(value="studentid", defaultValue="0") int studentid,
									@RequestParam(value="target", defaultValue="0") long target) {
		
		List <ScoreList> finalList = new ArrayList<>();
		Pagination p;
		
		// 학생 정보 수정한 경우 (이름 수정)
		if (target > 0 && function.equals("updateOne")) {
			finalList.add(scoreListRepository.findOneById(target));
			int totalCount = finalList.size();
			p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);
			model.addAttribute("target", target);
		} 
		// 학생 정보 삭제한 경우
		else if (target > 0 && function.equals("deleteOne")) {
			finalList = scoreListRepository.findAllByOrderByStudentidAsc(PageRequest.of(currentPage-1, countPerPage));
			int totalCount = (int)scoreListRepository.count();
			p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);
			model.addAttribute("target", target);
			model.addAttribute("get_name", name);
			model.addAttribute("get_studentid", studentid);
		}
		else {
			// 전체 조회인 경우
			if (name.equals("") && studentid == 0 ){
				
				finalList = scoreListRepository.findAllByOrderByStudentidAsc(PageRequest.of(currentPage-1, countPerPage));
				int totalCount = (int)scoreListRepository.count();
				p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);
				function = "selectAll";
				
			} 
			// 선택 조회인 경우 (이름 or 학번 or 이름 + 학번)
			else {
				List<ScoreList> selectedList;
				if (studentid == 0 && !name.equals("") ) {
					selectedList = scoreListRepository.findByNameOrderByStudentidAsc(name);
				} else if (studentid != 0 && name.equals("")) {
					selectedList = scoreListRepository.findByStudentidOrderByStudentidAsc(studentid);
				} else {
					selectedList = scoreListRepository.findByStudentidAndNameOrderByStudentidAsc(studentid,name);
				}
				
				finalList = scoreListRepository.findCurrentPageWithList(currentPage, countPerPage, selectedList);
				
				int totalCount = selectedList.size();
				p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);
				function = "update";
			}
		}
		
		int LineCount = (currentPage-1)*countPerPage;
		model.addAttribute("LineCount", LineCount);
		model.addAttribute("get_list", finalList);
		model.addAttribute("get_pagination", p);
		model.addAttribute("function", function);
		model.addAttribute("get_name", name);
		model.addAttribute("get_studentid", studentid);
		
		return "viewScoreList";
	}
	
	// !!!!!!!!!!!!선택 학생 정보 수정 및 삭제 후 조회
	@RequestMapping (value = "/viewScoreList", method = RequestMethod.POST)
	public String updateList(Model model, @RequestParam(value="cPage", defaultValue="1") int currentPage, 
									@RequestParam(value="count", defaultValue="10") int countPerPage,
									@RequestParam(value="pageSize", defaultValue="10") int pageSize,
									@RequestParam(value="function", defaultValue="selectAll") String function,
									@RequestParam(value="name", defaultValue="") String name, 
									@RequestParam(value="studentid", defaultValue="0") int studentid,
									@RequestParam(value="id", defaultValue="0") long id) {
		
		ScoreList scoreList = scoreListRepository.findOneById(id);
		scoreList.setName(name);
		scoreListRepository.save(scoreList);
		
		ScoreList finalList = scoreListRepository.findOneById(id);
		
		List<ScoreList> list = new ArrayList<>();
		list.add(finalList);
		
		int totalCount = list.size();
		Pagination p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);
		function = "updateOne";
		int LineCount = (currentPage-1)*countPerPage;
		
		model.addAttribute("get_list", list);
		model.addAttribute("LineCount", LineCount);
		model.addAttribute("function", function);
		model.addAttribute("get_pagination", p);
		model.addAttribute("target", id);

		return "viewScoreList";
	}
	
	// 선택 학생 삭제
	@RequestMapping (value = "/deleteScoreList", method = RequestMethod.POST)
	public String deleteList(Model model, @RequestParam(value="cPage", defaultValue="1") int currentPage, 
										@RequestParam(value="count", defaultValue="10") int countPerPage,
										@RequestParam(value="pageSize", defaultValue="10") int pageSize,
										@RequestParam(value="function", defaultValue="selectAll") String function,
										@RequestParam(value="name", defaultValue="") String name, 
										@RequestParam(value="studentid", defaultValue="0") int studentid,
										@RequestParam(value="id", defaultValue="0") long id) {
		
			// 삭제 쿼리
			scoreListRepository.deleteOneById(id);
			
			// 페이지네이션
			List<ScoreList> finalList = scoreListRepository.findAllByOrderByStudentidAsc(PageRequest.of(currentPage-1, countPerPage));
			int totalCount = (int)scoreListRepository.count();
			Pagination p = scoreItemService.getPagination(currentPage, countPerPage, pageSize, totalCount);
			
			function = "deleteOne";
			
			model.addAttribute("function", function);
			model.addAttribute("get_name", name);
			model.addAttribute("get_studentid", studentid);
			model.addAttribute("get_pagination", p);
			model.addAttribute("target", id);
			model.addAttribute("get_list", finalList);
			
			return "viewScoreList";
	}
	
	// 학생 추가
	@RequestMapping (value = "/addScoreList_result", method = RequestMethod.POST)
	public String addList(Model model, @RequestParam(value="name1", defaultValue="이름 입력") String name) {
		
		ScoreList scoreList = scoreListRepository.saveNew(name);
		
		model.addAttribute("get_name", scoreList.getName());
		model.addAttribute("get_studentid", scoreList.getStudentid());
		
		return "addScoreList";
		
	}
	
	// 학생 정보 수정
	@RequestMapping (value = "/updateScoreList", method = RequestMethod.POST)
	public String updateList(Model model, @RequestParam(value="id", defaultValue="0") long id) {
		
		ScoreList scoreList = scoreListRepository.findOneById(id);
		
		model.addAttribute("get_scoreList", scoreList);
		
		return "updateScoreList";
	}
	

}
