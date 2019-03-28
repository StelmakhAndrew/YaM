package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Comment;

import java.util.List;

@Service
public interface CommentService {
    void createComment(Comment comment);

    List<Comment> findAll();
    List<Comment> findAllSortByDate();
    List<Comment> findComentsForThisBookSortByDate(Long book_id);
}
