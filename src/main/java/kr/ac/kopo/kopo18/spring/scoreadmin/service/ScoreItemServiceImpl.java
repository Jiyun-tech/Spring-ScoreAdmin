package kr.ac.kopo.kopo18.spring.scoreadmin.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreItem;
import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreList;
import kr.ac.kopo.kopo18.spring.scoreadmin.dto.Pagination;
import kr.ac.kopo.kopo18.spring.scoreadmin.repository.ScoreItemRepository;
import kr.ac.kopo.kopo18.spring.scoreadmin.repository.ScoreListRepository;

@Service // Component -> Singleton으로 처리
public class ScoreItemServiceImpl implements ScoreItemService {
	
	@Autowired
	ScoreItemRepository scoreItemRepository;
	
	@Autowired
	ScoreListRepository scoreListRepository;
	

	@Override
	public Pagination getPagination(int currentPage, int countPerPage, int pageSize, int totalCount) {
//		PageRequest pageable = PageRequest.of(currentPage, countPerPage);
//		Page<ScoreItem> page = scoreItemRepository.findAll(pageable);
		// (1) ppPage (fistPage)
		int ppPage = 1;
		
		// (2) nnPage (lastPage)
		int nnPage = 1;
		if (totalCount > 0 && countPerPage > 0) {
			if (totalCount%countPerPage == 0) {
				nnPage = totalCount/countPerPage;
			} else {
				nnPage = totalCount/countPerPage + 1;
			}
		} 
		
		// (3) CurrentPage 예외 처리
		int cPage = 1;
		if (currentPage < 1) {
			cPage = 1;
		} else if (currentPage > nnPage) {
			cPage = nnPage;
		} else {
			cPage = currentPage;
		}
		
		// (4) pPage
		int pPage = 1;
		if (pageSize > 0) {
			if (cPage <= pageSize) {
				pPage = 1;
			} else {
				pPage = (cPage / pageSize) * pageSize - (pageSize-1);
			}
		} else { // pageSize가 0보다 작은 경우
			pPage = 1;
		}
		
		// (5) nPage
		int nPage = 1;
		if (pageSize > 0) {
			if ( cPage >= (((nnPage- 1)/pageSize) * pageSize + 1 ) ) {
				nPage = nnPage;
			} else {
				nPage = ((cPage - 1)/pageSize + 1) * pageSize + 1;
			}
		} else { // pageSize가 0보다 작거나 같은 경우, nPage 1로 설정.
			nPage = 1;
		}
				
		// (6) sList (목록번호 인쇄 시작)
		int sList = 1;
		if (pageSize > 0) {
			sList = ((cPage-1)/pageSize)*pageSize + 1;
		} else {
			sList = 1;
		}
				
		// (7) eList (목록번호 인쇄 종료)
		int eList = 1;
		if (pageSize > 0) {
			eList = sList + pageSize - 1;
		}
		if (eList > nnPage) {
			eList = nnPage;
		}
		
//		page.getSize();							// dataPerPage
//		page.getTotalPages();					// nnPage (Last Page Number)
//		page.getTotalElements();				// total data number
//		page.nextPageable();					// next Page information
//		page.nextPageable().getPageNumber();	// next Page Number
//		page.getNumber(); 						// current Page Number (begin from 0)
//		page.getNumberOfElements(); 			// data count of currentPage
		
		Pagination p = new Pagination();
		p.setPpPage(ppPage);
		p.setpPage(pPage);
		p.setcPage(cPage);
		p.setnPage(nPage);
		p.setNnPage(nnPage);
		p.setsList(sList);
		p.seteList(eList);
		p.setCountPerPage(countPerPage);
		p.setPageSize(pageSize);
		
		return p;	
	}



	@Override
	public List<ScoreItem> findAllByOrderByIdAsc(PageRequest pageRequest) {
		return scoreItemRepository.findAllByOrderByIdAsc(pageRequest);
	}



	@Override
	public List<ScoreItem> findAllByScoreListId(Long id, PageRequest pageRequest) {
		return scoreItemRepository.findAllByScoreListId(id, pageRequest);
	}



	@Override
	public List<ScoreItem> findAllById(Long id) {
		return scoreItemRepository.findAllById(id);
	}


//	@Override
//	public List<ScoreItem> findCurrentPage(int currentPage, int countPerPage) {
//		return scoreItemRepository.findCurrentPage(currentPage, countPerPage);
//	}
//
//
//
	@Override
	public List<ScoreItem> findCurrentPageWithList(int currentPage, int countPerPage, List<ScoreItem> listAll) {
		return scoreItemRepository.findCurrentPageWithList(currentPage, countPerPage, listAll);
	}



	@Override
	public ScoreItem save(ScoreItem scoreItem) {
		return scoreItemRepository.save(scoreItem);
	}



	@Override
	public void deleteById(Long listId, Long id) {
		
		ScoreList scoreList = scoreListRepository.findOneById(listId);
		ScoreItem scoreItem = scoreItemRepository.findOneById(id);

		// 자식을 삭제하기 위해 부모와의 관계를 끊어줌 
		scoreItem.setScoreList(null);
		// 부모와의 관계 끊은 후, 해당 자식 삭제
		scoreList.getScoreItems().remove(scoreItem);

		// 변경된 부모 저장 (update)
		scoreListRepository.save(scoreList);
	}



	@Override
	public List<ScoreItem> findAllByScoreListId(Long scoreListId) {
		return scoreItemRepository.findAllByScoreListId(scoreListId);
	}


	@Override
	public ScoreItem findOneById(Long id) {
		return scoreItemRepository.findOneById(id);
	}


	
}
