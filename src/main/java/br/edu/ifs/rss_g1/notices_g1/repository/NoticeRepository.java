package br.edu.ifs.rss_g1.notices_g1.repository;

import br.edu.ifs.rss_g1.notices_g1.entity.Category;
import br.edu.ifs.rss_g1.notices_g1.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoticeRepository extends JpaRepository<Notice,UUID> {
    List<Notice> findAllByCategory(Category category);
    Optional<Notice> findByTitleAndPubDate(String title, Date pubDate);
}
