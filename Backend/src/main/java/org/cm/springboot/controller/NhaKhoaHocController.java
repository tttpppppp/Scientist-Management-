package org.cm.springboot.controller;


import org.bouncycastle.asn1.ocsp.ResponseData;
import org.cm.springboot.controller.response.NhaKhoaHocHomeResponse;
import org.cm.springboot.controller.response.NhaKhoaHocPage;
import org.cm.springboot.controller.response.NhaKhoaHocResponse;
import org.cm.springboot.payload.DataResponse;
import org.cm.springboot.service.FilterService;
import org.cm.springboot.service.NhaKhoaHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/scientist")
public class NhaKhoaHocController {
    @Autowired
    NhaKhoaHocService nhaKhoaHocService;
    @Autowired
    FilterService filterService;
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER' , 'ADMIN' , 'MANAGER')")
    public ResponseEntity<?> get(@PathVariable("id") int id) {
        NhaKhoaHocResponse nhaKhoaHocResponse= nhaKhoaHocService.getDetailNhaKhoaHoc(id);
        if(nhaKhoaHocResponse != null) {
            return ResponseEntity.ok(new DataResponse(HttpStatus.OK.value() , "Lấy dữ liệu thành công" , nhaKhoaHocResponse));
        }
        return ResponseEntity.ok(new DataResponse(HttpStatus.INTERNAL_SERVER_ERROR.value() , "Lấy dữ liệu thất bại"));
    }
    @GetMapping("/list")
    public ResponseEntity<?> getHomeNhaKhoaHoc(@RequestParam(required = false) String keyword,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "12") int size ) {
        NhaKhoaHocPage  nhaKhoaHocPage = nhaKhoaHocService.getAllNhaKhoaHoc(keyword, page, size);
        if(nhaKhoaHocPage.getTotalElements() != 0) {
             return ResponseEntity.ok(new DataResponse(HttpStatus.OK.value() , "Lấy dữ liệu thành công" , nhaKhoaHocPage));
        }
        return ResponseEntity.ok(new DataResponse(HttpStatus.NOT_FOUND.value() , "Lấy dữ liệu thất bại"));
    }
    @GetMapping("/filter")
    public ResponseEntity<?> getFilter() {
        return ResponseEntity.ok(new DataResponse(HttpStatus.OK.value() , "Lấy dữ liệu thành công" , filterService.getFilter()));

    }
}
