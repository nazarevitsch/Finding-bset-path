package com.bida.testtask.service;

import com.bida.testtask.domain.Point;
import com.bida.testtask.reposiory.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    public List<Point> getAllPoints(){
        return pointRepository.findAll();
    }
}
