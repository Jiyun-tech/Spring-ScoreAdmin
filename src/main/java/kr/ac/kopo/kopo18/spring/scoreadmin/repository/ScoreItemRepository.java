package kr.ac.kopo.kopo18.spring.scoreadmin.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreItem;

@Repository
public interface ScoreItemRepository extends JpaRepository<ScoreItem, Long>, JpaSpecificationExecutor<ScoreItem> {
												//JpaRepsitory에 기본적으로 DB와 수행할 수 있는 기능 모두 있음 (조회, 삭제 등)

	// CRUD 기능 추가
	
	// 1. Create
	// *참고) 기본 기능 -> ScoreItem save(ScoreItem scoreItem);
	
//	// 2-1. Select All (order by id)
	List<ScoreItem> findAllById(Long id);
	List<ScoreItem> findAllByOrderByIdAsc(PageRequest pageRequest); // 페이지 지정 출력 위한 메서드
	
	default List<ScoreItem> findCurrentPageWithList(int currentPage, int countPerPage, List<ScoreItem> listAll) {
		List<ScoreItem> list = new ArrayList<ScoreItem>();
		int count = currentPage * countPerPage;
		int last = listAll.size();
		if (count > last) {
			count = last;
		}
		for (int i = (currentPage -1) * countPerPage; i < count; i++) {
			list.add(listAll.get(i));
		}
		return list;
	}
	
	// 2-3. Select One
	ScoreItem findOneById(Long id);
	List<ScoreItem> findAllByScoreListId(Long scoreListId, PageRequest pageRequest);
	List<ScoreItem> findAllByScoreListId(Long scoreListId);
	
	// 3. Update
	default ScoreItem updateOne(ScoreItem scoreItem) {
		
		ScoreItem scoreItem2 = findOneById(scoreItem.getId());
		
//		scoreItem2.setName(scoreItem.getName());
		scoreItem2.setKor(scoreItem.getKor());
		scoreItem2.setEng(scoreItem.getEng());
		scoreItem2.setMat(scoreItem.getMat());
		scoreItem2.setTest(scoreItem.getTest());
		scoreItem2.setScoreList(scoreItem.getScoreList());
		
		save(scoreItem2);
		
		return scoreItem2;
	}
	
	// 4-1. DeleteOne
	// @Transactional //(-> Test 페이지로 이동)
	// 기본 기능 -> deleteById(Long id);
//	void deleteOneById(Long id);
	@Transactional
	void deleteOneById(Long id);
	// 4-2. DeleteAll 
	// 기본 기능 -> deleteAll();
	
	// 5. Pagination
//	Page<ScoreItem> findAll(Pageable pageable);
//	Page<ScoreItem> findAllByOrderByIdDesc(Pageable pageable);
//	Page<ScoreItem> findAllByAuthor(String author, Pageable pageable);
//	
//	@Query("select t from ScoreItem t where content like concat('%', :searchString, '%')")
//	Page<ScoreItem> findAllSearch(@Param("searchString") String searchString, Pageable pageable);
}
