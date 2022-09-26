package travelplanrepo.domain.likes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import travelplanrepo.domain.likes.entity.Likes;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Query("select likes from Likes as likes " +
            "where likes.account.id = :accountId and likes.board.id = :boardId")
    Optional<Likes> findLikesBy(@Param("accountId") Long accountId, @Param("boardId") Long boardId);
}
