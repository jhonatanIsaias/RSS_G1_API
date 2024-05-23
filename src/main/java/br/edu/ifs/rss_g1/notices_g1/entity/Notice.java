package br.edu.ifs.rss_g1.notices_g1.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Notice {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID notice_id;
    private String title;
    private String description;
    private String url_image;
    private Date pub_date;
    @ManyToOne
    @JoinColumn(name = "category_fk")
    private Category category;
}
