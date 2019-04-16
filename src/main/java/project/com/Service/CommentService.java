package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Comment;

import java.util.List;

/**
 *
 */
@Service
public interface CommentService {
    /**
     * @param comment
     */
    void createComment(Comment comment);

    /**
     * @return
     */
    List<Comment> findAll();

    /**
     * @return
     */
    List<Comment> findAllSortByDate();

    /**
     * @param book_id
     * @return
     */
    List<Comment> findComentsForThisBookSortByDate(Long book_id);
}
