package travelplanrepo.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import travelplanrepo.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
