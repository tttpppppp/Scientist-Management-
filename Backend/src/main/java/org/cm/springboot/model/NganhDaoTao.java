package org.cm.springboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "nganh_dao_tao")
class NganhDaoTao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nganh_id")
    private Long id;

    @Column(name = "ten_nganh")
    private String ten;

    @OneToMany(mappedBy = "nganhDaoTao")
    private List<QuaTrinhDaoTao> quaTrinhDaoTaoList;
}
