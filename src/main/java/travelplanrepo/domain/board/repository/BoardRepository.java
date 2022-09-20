package travelplanrepo.domain.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import travelplanrepo.domain.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
