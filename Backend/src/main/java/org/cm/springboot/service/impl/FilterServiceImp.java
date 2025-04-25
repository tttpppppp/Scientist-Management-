package org.cm.springboot.service.impl;

import org.cm.springboot.controller.response.*;
import org.cm.springboot.model.*;
import org.cm.springboot.repository.*;
import org.cm.springboot.service.FilterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterServiceImp implements FilterService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ChucDanhRepository chucDanhRepository;
    @Autowired
    DonViRepository DonViRepository;
    @Autowired
    HocViRepository hocViRepository;
    @Autowired
    LinhVucNghienCuuRepository linhVucNghienCuuRepository;
    @Autowired
    NgachRepository ngachRepository;
    @Override
    public FilterResponse getFilter() {
        FilterResponse filterResponse = new FilterResponse();
        filterResponse.setChucDanhResponses(mapList(chucDanhRepository.findAll(), ChucDanhResponse.class));
        filterResponse.setDonViResponses(mapList(DonViRepository.findAll(), DonViResponse.class));
        filterResponse.setHocViResponses(mapList(hocViRepository.findAll(), HocViResponse.class));
        filterResponse.setLinhVucNghienCuuResponses(mapList(linhVucNghienCuuRepository.findAll(), LinhVucNghienCuuResponse.class));
        filterResponse.setNgachRespionses(mapList(ngachRepository.findAll(), NgachResponse.class));
        return filterResponse;
    }
    private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }
}
