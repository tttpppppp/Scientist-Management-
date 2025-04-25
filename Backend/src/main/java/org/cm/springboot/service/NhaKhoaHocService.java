package org.cm.springboot.service;


import org.cm.springboot.controller.response.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhaKhoaHocService {
    NhaKhoaHocPage getAllNhaKhoaHoc(String keyword , int page , int size);
    NhaKhoaHocResponse getDetailNhaKhoaHoc(int id);
}
