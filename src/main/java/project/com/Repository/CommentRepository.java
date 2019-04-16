package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Comment;

import java.util.List;

/**
 *
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * @return
     */
    List<Comment> findAll();

    /**
     * @return
     */
    List<Comment> findAllByOrderByDateDesc();

    /**
     * @param book_id
     * @return
     */
    List<Comment> findAllByBook_IdOrderByDateDesc(Long book_id);
}
