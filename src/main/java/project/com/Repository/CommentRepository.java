package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Comment;

import java.util.List;

/**
 * A repository is a mechanism for encapsulating storage,
 * retrieval, and search behavior which emulates a collection of objects.
 *
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * method for find all comments
     * @return allComments
     */
    List<Comment> findAll();

    /**
     * method for find all comments and sorted them
     * @return allSortedComments
     */
    List<Comment> findAllByOrderByDateDesc();

    /**
     * method for find all comments by book_id and sorted them
     * @param book_id;
     * @return allCommentsByBook_id
     */
    List<Comment> findAllByBook_IdOrderByDateDesc(Long book_id);
}
