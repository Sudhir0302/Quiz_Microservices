package com.sudhir03.question_service.dao;

import com.sudhir03.question_service.model.Question;
import com.sudhir03.question_service.model.QuestionWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

//    @Query("SELECT q FROM Question q WHERE LOWER(q.category) = LOWER(:category)")
//    List<Question> findByCategory(@Param("category") String category);
    List<Question> findByCategoryIgnoreCase(String category);

    @Query(value = "SELECT id FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);

    @Query(value="select id,question_title AS questionTitle,option1,option2,option3,option4 from question where id=:id",nativeQuery = true)
    QuestionWrapper findByIdWrapper(int id);
}
