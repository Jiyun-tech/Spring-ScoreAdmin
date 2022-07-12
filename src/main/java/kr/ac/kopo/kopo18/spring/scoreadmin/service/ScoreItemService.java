package kr.ac.kopo.kopo18.spring.scoreadmin.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreItem;
import kr.ac.kopo.kopo18.spring.scoreadmin.dto.Pagination;

public interface ScoreItemService {
	
	Pagination getPagination(int currentPage, int countPerPage, int pageSize, int totalCount);
//	List<ScoreItem> findCurrentPage(int page, int countPerPage);
	List<ScoreItem> findCurrentPageWithList(int page, int countPerPage, List<ScoreItem> scoreItems);

	List<ScoreItem> findAllByOrderByIdAsc(PageRequest pageRequest);
	List<ScoreItem> findAllById(Long id);
	List<ScoreItem> findAllByScoreListId(Long scoreListId, PageRequest pageRequest);
	List<ScoreItem> findAllByScoreListId(Long scoreListId);
	
	ScoreItem save(ScoreItem scoreItem);
	void deleteById(Long listId, Long id);
	ScoreItem findOneById(Long id);
}
