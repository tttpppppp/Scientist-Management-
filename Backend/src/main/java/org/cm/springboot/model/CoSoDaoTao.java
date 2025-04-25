package org.cm.springboot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "co_so_dao_tao")
class CoSoDaoTao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_so_id")
    private Long id;

    @Column(name = "ten_co_so")
    private String ten;

    @OneToMany(mappedBy = "coSoDaoTao")
    private List<QuaTrinhDaoTao> quaTrinhDaoTaoList;
}
