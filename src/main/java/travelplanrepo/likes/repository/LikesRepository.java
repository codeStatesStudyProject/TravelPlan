package travelplanrepo.likes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import travelplanrepo.likes.entity.Likes;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Query("select likes from Likes as likes " +
            "where likes.account.id = :accountId and likes.board.id = :boardId")
    Optional<Likes> findLikesBy(@Param("accountId") Long accountId, @Param("boardId") Long boardId);

    Optional<Likes> findLikesByAccountIdAndBoardId(Long accountId, Long boardId);
}