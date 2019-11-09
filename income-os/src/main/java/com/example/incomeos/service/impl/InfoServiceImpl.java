package com.example.incomeos.service.impl;

import com.example.incomeos.bean.InfoDto;
import com.example.incomeos.common.QueryParam;
import com.example.incomeos.common.ResultPage;
import com.example.incomeos.dao.InfoDao;
import com.example.incomeos.service.InfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-11-08 16:18:14
 **/
@Service
public class InfoServiceImpl implements InfoService {

    @Autowired(required = false)
    private InfoDao infoDao;

    @Override
    public List<InfoDto> selectAll() {
        Map map = new HashMap<String,String>();
        List<InfoDto> list = infoDao.selectAll(map);
        return  list;
    }

    @Override
    public ResultPage select(QueryParam queryParam) {
        Page<?> page = PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());
        List<InfoDto> list = infoDao.selectAll(queryParam);
        return  new ResultPage(list, (int) page.getTotal(), queryParam.getLimit(), queryParam.getPage());
    }

    @Override
    public int insert(InfoDto infoDto) {
        return infoDao.insert(infoDto);
    }
}
