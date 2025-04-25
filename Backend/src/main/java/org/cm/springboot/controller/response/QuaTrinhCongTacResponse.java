package org.cm.springboot.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class QuaTrinhCongTacResponse {
    private long id;
    private String tuNam;
    private String denNam;
    private String chucDanhCongTac;
    private String coQuanCongTac;
    private String chucVu;
}
