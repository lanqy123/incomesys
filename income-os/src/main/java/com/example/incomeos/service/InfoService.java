package com.example.incomeos.service;

import com.example.incomeos.bean.InfoDto;
import com.example.incomeos.common.QueryParam;
import com.example.incomeos.common.ResultPage;

import java.util.List;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-11-08 16:16:59
 **/
public interface InfoService {

    ResultPage select(QueryParam queryParam);

     List<InfoDto> selectAll();

    int insert(InfoDto infoDto);

    int deleteInfo(Long id);
}
