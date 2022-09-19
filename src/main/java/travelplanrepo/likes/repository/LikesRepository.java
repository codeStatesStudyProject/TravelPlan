package travelplanrepo.likes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import travelplanrepo.likes.entity.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long> {

}
