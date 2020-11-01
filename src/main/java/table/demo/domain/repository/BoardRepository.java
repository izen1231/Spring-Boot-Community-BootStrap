package table.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import table.demo.domain.entity.BoardEntity;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByTitleContaining(String keyword);
}