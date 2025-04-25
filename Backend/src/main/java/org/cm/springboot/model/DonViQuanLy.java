package org.cm.springboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "don_vi_quan_ly")
public class DonViQuanLy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "don_vi_id")
    private Long donViId;

    @Column(name = "ten_don_vi")
    private String tenDonVi;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "donViQuanLy")
    private List<NhaKhoaHoc> nhaKhoaHocList;
}
