package org.cm.springboot.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuaTrinhDaoTao {
    private String bacDaoTao;
    private String coSoDaoTao;
    private String nganhDaoTao;
    private int namTotNghiep;
}
