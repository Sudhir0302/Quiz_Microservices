package com.sudhir03.question_service.service;

import com.sudhir03.question_service.dao.QuestionDao;
import com.sudhir03.question_service.model.Question;
import com.sudhir03.question_service.model.QuestionWrapper;
import com.sudhir03.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategoryIgnoreCase(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numqns)
    {
        List<Integer> questions=questionDao.findRandomQuestionsByCategory(categoryName,numqns);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionsIds)
    {
        List<QuestionWrapper>wqns=new ArrayList<>();
        List<Question> question=new ArrayList<>();

        for(Integer id : questionsIds)
        {
            question.add(questionDao.findById(id).get());
        }

        for(Question qn : question)
        {
            QuestionWrapper wr=new QuestionWrapper();
            wr.setId(qn.getId());
            wr.setQuestionTitle(qn.getQuestionTitle());
            wr.setOption1(qn.getOption1());
            wr.setOption2(qn.getOption2());
            wr.setOption3(qn.getOption3());
            wr.setOption4(qn.getOption4());
            wqns.add(wr);
        }
//        for(int id : questionsIds)
//        {
//            QuestionWrapper obj=new QuestionWrapper();
//            obj=questionDao.findByIdWrapper(id);
//            System.out.println(obj.getQuestionTitle());
//        }
        return new ResponseEntity<>(wqns,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses)
    {
        int right = 0;
        for(Response response : responses){
            Question question=questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
                right++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
