package kr.ac.kopo.kopo18.spring.scoreadmin.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreItem;
import kr.ac.kopo.kopo18.spring.scoreadmin.domain.ScoreList;
import kr.ac.kopo.kopo18.spring.scoreadmin.dto.Pagination;

@Repository
public interface ScoreListRepository extends JpaRepository<ScoreList, Long>, JpaSpecificationExecutor<ScoreList> {
	
	default ScoreList saveNew(String name) {
		ScoreList scoreList = new ScoreList();
		
		int studentid = 209901;
		int min_studentid = 209901;
		List<ScoreList> studentid_list = findByOrderByStudentidAsc();
		for (int i = 0; i < studentid_list.size(); i++) {
			if (studentid_list.get(i).getStudentid() == min_studentid) {
				min_studentid++;
			} else {
				break;
			}
		}
		studentid = min_studentid;
		
		scoreList.setStudentid(studentid);
		scoreList.setName(name);
		
		save(scoreList);
		
		return scoreList;
	}
	
	// R
	ScoreList findOneById(Long id);
	Optional<ScoreList> findById(Long id);
	List<ScoreList> findAllByOrderByStudentidAsc(PageRequest pageRequest); // --> Page 지정 출력 위한 method
	List<ScoreList> findByOrderByStudentidAsc();
	List<ScoreList> findByStudentidOrderByStudentidAsc(int studentid);
	List<ScoreList> findByNameOrderByStudentidAsc(String name);
	List<ScoreList> findByStudentidAndNameOrderByStudentidAsc(int studentid, String name);
	
	default List<ScoreList> findCurrentPageWithList(int currentPage, int countPerPage, List<ScoreList> listAll) {
		List<ScoreList> list = new ArrayList<ScoreList>();
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
	

	// D
	void deleteOneById(Long id);
	ScoreList save(ScoreList scoreList);
//	List<ScoreItem> findByNameAndStudentidLessThan(String name, int studentid);
//	
//	@Query("select t from score_item t where name = :name and studentid < :studentid")
//	List<ScoreItem> findByNameAndStudentidLessThanSQL(@Param("name") String name, @Param("studentid") int studentid);
//	
//	List<ScoreItem> findByNameAndStudentidLessThanOrderByStudentidDesc(String name, int studentid);
	
}
