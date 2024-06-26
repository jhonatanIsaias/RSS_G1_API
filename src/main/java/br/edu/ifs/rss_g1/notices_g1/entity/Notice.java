package br.edu.ifs.rss_g1.notices_g1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID notice_id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Description cannot be null")
    @Column(length = 10000)
    private String description;

    @NotNull(message = "Url image cannot be null")
    private String url_image;

    @NotNull(message = "Publication date cannot be null")
    private Date pub_date;

    @ManyToOne
    @JoinColumn(name = "category_fk")
    @NotNull(message = "Category cannot be null")
    private Category category;
}
