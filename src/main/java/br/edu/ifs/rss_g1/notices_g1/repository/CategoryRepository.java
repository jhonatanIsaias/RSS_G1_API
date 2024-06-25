package br.edu.ifs.rss_g1.notices_g1.repository;

import br.edu.ifs.rss_g1.notices_g1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryId(Long categoryId);
}

