package com.example.incomeos.dao;

import com.example.incomeos.bean.InfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-11-08 16:01:09
 **/
@Mapper
public interface InfoDao {

   List<InfoDto> selectAll(Map<String, Object> map);

    int insert(InfoDto infoDto);

    int deleteInfo(Long id);

}
