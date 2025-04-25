package org.cm.springboot.service.impl;

import org.cm.springboot.controller.response.*;
import org.cm.springboot.exception.ResourceNotFoundException;
import org.cm.springboot.model.DonViQuanLy;
import org.cm.springboot.model.NhaKhoaHoc;
import org.cm.springboot.model.Users;
import org.cm.springboot.repository.NhaKhoaHocRepository;
import org.cm.springboot.service.NhaKhoaHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NhakHoaHocServiceImpl implements NhaKhoaHocService {
    @Autowired
    NhaKhoaHocRepository nhaKhoaHocRepository;

    public NhaKhoaHoc getNhakHoaHocId(int id){
        return nhaKhoaHocRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nhà khoa học không tồn tại"));
    }

    @Override
    public NhaKhoaHocPage getAllNhaKhoaHoc(String keyword, int page, int size) {
        int pageNo = 0;
        if(page > 0) {
            pageNo = page - 1;
        }
        Pageable listNhakHoaHocEntity = PageRequest.of(pageNo , size);
        Page<NhaKhoaHoc> nhaKhoaHocs = nhaKhoaHocRepository.findAll(listNhakHoaHocEntity);
        return getHomeNhaKhoaHocPageResponse(page , size , nhaKhoaHocs);
    }

    public NhaKhoaHocPage getHomeNhaKhoaHocPageResponse(int page, int size , Page<NhaKhoaHoc> nhaKhoaHocs) {
        List<NhaKhoaHocHomeResponse> nhaKhoaHocResponses = new ArrayList<>();
        for (var item : nhaKhoaHocs) {
            NhaKhoaHocHomeResponse nhaKhoaHocResponse = new NhaKhoaHocHomeResponse();
            nhaKhoaHocResponse.setId(item.getNhaKhoaHocId());
            nhaKhoaHocResponse.setFullname(item.getFullName());
            nhaKhoaHocResponse.setChucdanh(item.getChucDanhKhoaHoc().getTenChucDanh());
            nhaKhoaHocResponse.setChuyenNganh(item.getChuyenNganh().getTenChuyenNganh());
            nhaKhoaHocResponse.setNganhDaoTao(item.getChuyenNganh().getTenChuyenNganh());
            nhaKhoaHocResponse.setTenDonVi(item.getDonViQuanLy().getTenDonVi());
            nhaKhoaHocResponse.setImage(item.getImage());
            nhaKhoaHocResponses.add(nhaKhoaHocResponse);
        }
        return new NhaKhoaHocPage(page , size , nhaKhoaHocs.getTotalPages() , nhaKhoaHocs.getTotalElements() , nhaKhoaHocResponses);
    }
    @Override
    public NhaKhoaHocResponse getDetailNhaKhoaHoc(int id) {
        NhaKhoaHoc nhaKhoaHoc = getNhakHoaHocId(id);
        DonViQuanLy donViQuanLy = nhaKhoaHoc.getDonViQuanLy();
        NhaKhoaHocResponse nhaKhoaHocResponse = new NhaKhoaHocResponse();
        if(nhaKhoaHoc != null){
            nhaKhoaHocResponse.setFullname(nhaKhoaHoc.getFullName());
            nhaKhoaHocResponse.setChucdanh(nhaKhoaHoc.getChucDanhKhoaHoc().getTenChucDanh());
            nhaKhoaHocResponse.setGioiTinh(nhaKhoaHoc.getGioiTinh());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            String formattedDate = sdf.format(nhaKhoaHoc.getNamSinh());
            nhaKhoaHocResponse.setNamSinh(formattedDate);
            nhaKhoaHocResponse.setDienThoai(nhaKhoaHoc.getDienThoai());
            nhaKhoaHocResponse.setEmail(nhaKhoaHoc.getEmail());
            nhaKhoaHocResponse.setAddress(nhaKhoaHoc.getDiaChi());
            nhaKhoaHocResponse.setNganhDaoTao(nhaKhoaHoc.getChuyenNganh().getTenChuyenNganh());
            nhaKhoaHocResponse.setChuyenNganh(nhaKhoaHoc.getChuyenNganh().getTenChuyenNganh());
            nhaKhoaHocResponse.setChuyenMonGiangDay(nhaKhoaHoc.getChuyenMonGiangDay());
            nhaKhoaHocResponse.setNgoaiNgu(nhaKhoaHoc.getTrinhDoNgoaiNgu());
            DonViResponse donViResponse = new DonViResponse();
            donViResponse.setDonViId(donViQuanLy.getDonViId());
            donViResponse.setTenDonVi(donViQuanLy.getTenDonVi());
            nhaKhoaHocResponse.setDonViResponse(donViResponse);
            List<QuaTrinhCongTacResponse> quaTrinhCongTacResponses = new ArrayList<>();
            for(var item : nhaKhoaHoc.getQuaTrinhCongTacList()){
                QuaTrinhCongTacResponse quaTrinhCongTacResponse = new QuaTrinhCongTacResponse();
                quaTrinhCongTacResponse.setId(item.getId());
                SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
                String formatTuNam = s.format(item.getTuNam());
                String formatdenNam = s.format(item.getDenNam());
                quaTrinhCongTacResponse.setTuNam(formatTuNam);
                quaTrinhCongTacResponse.setDenNam(formatdenNam);
                quaTrinhCongTacResponse.setChucDanhCongTac(item.getChucDanhCongTac().getTen());
                quaTrinhCongTacResponse.setCoQuanCongTac(item.getCoQuanCongTac().getTen());
                quaTrinhCongTacResponse.setChucVu(item.getChucVu().getTenChucVu());
                quaTrinhCongTacResponses.add(quaTrinhCongTacResponse);
            }
            List<LinhVucNghienCuuResponse> linhVucNghienCuuResponses = new ArrayList<>();
            for(var item : nhaKhoaHoc.getNhaKhoaHocLinhVucList()){
                LinhVucNghienCuuResponse linhVucNghienCuuResponse = new LinhVucNghienCuuResponse();
                linhVucNghienCuuResponse.setId(item.getLinhVucNghienCuu().getId());
                linhVucNghienCuuResponse.setTenLinhVuc(item.getLinhVucNghienCuu().getTenLinhVuc());
                linhVucNghienCuuResponses.add(linhVucNghienCuuResponse);
            }
            nhaKhoaHocResponse.setLinhVucNghienCuuResponses(linhVucNghienCuuResponses);
            nhaKhoaHocResponse.setQuaTrinhCongTacResponses(quaTrinhCongTacResponses);
        }
        return nhaKhoaHocResponse;
    }

}
