package com.freddxant.spring.playground.service;

import com.freddxant.spring.playground.model.dto.ResponseDto;
import com.freddxant.spring.playground.model.entity.Position;
import com.freddxant.spring.playground.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PositionServiceImpl implements PositionService{

    @Autowired
    PositionRepository positionRepository;

    @Override
    public ResponseDto findAllPosition() {
        ResponseDto responseDto = new ResponseDto<>();

        try {
            List<Position> positionList = positionRepository.findAll();

            responseDto.setSuccess(true);
            responseDto.setCode(200);
            responseDto.setData(positionList);

            if (positionList.size() > 0) {
                responseDto.setMessage("Data found");
            } else {
                responseDto.setMessage("No data found");
            }
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setCode(500);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return responseDto;
    }

}
