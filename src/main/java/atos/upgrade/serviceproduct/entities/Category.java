package atos.upgrade.serviceproduct.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_categories")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
