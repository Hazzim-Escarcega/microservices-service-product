package atos.upgrade.serviceproduct.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "tbl_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotEmpty(message = "Name is required")
        private String name;

        @NotEmpty(message = "Description is required")
        private String description;

        private Double stock;
        private Double price;
        private String status;
        @Column(name = "created_at")
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id")
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        private Category category;
}
