package com.example.incomeos.controller;

import com.example.incomeos.bean.InfoDto;
import com.example.incomeos.common.QueryParam;
import com.example.incomeos.common.Result;
import com.example.incomeos.common.ResultPage;
import com.example.incomeos.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-11-08 16:20:28
 **/
@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    /**
     * 查询收款信息
     * @param request
     * @return
     */
    @RequestMapping("/querylist.do_")
    public Result queryList(HttpServletRequest request){
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String name = request.getParameter("name");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", page);
        params.put("limit", limit);
        params.put("name", name);

        ResultPage resultPage = infoService.select(new QueryParam(params));
        List<InfoDto> list = infoService.selectAll();
        return new Result().ok().put("page", resultPage).put("list",list);
    }

    /**
     * 添加收款信息
     * @param infoDto
     * @return
     */
    @RequestMapping("/insert.do_")
    public Result insert(InfoDto infoDto){
        infoService.insert(infoDto);
        return new Result().ok().put("msg", "success");
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteInfo.do_")
    public Result deleteInfo(@RequestParam("id") Long id){
        infoService.deleteInfo(id);
        return new Result().ok().put("msg", "success");
    }

    /**
     * 修改信息
     * @param infoDto
     * @return
     */
    @RequestMapping("/updateInfo.do_")
    public Result updateInfo(InfoDto infoDto){
        infoService.updateInfo(infoDto);
        return new Result().ok().put("msg", "success");
    }

}
