package com.example.mysite.service;

import com.example.mysite.domain.Answer;
import com.example.mysite.domain.Member;
import com.example.mysite.domain.Question;
import com.example.mysite.exception.DataNotFoundException;
import com.example.mysite.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer create(Question question, String content, Member author) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setContent((content));
        answer.setCreateDate(LocalDateTime.now());
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }

    public Answer getAnswer(Long id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    public void vote(Answer answer, Member member) {
        answer.getVoter().add(member);
        this.answerRepository.save(answer);
    }
}
