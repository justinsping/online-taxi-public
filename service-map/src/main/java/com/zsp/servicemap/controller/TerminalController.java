package com.zsp.servicemap.controller;

import com.zsp.dto.ResponseResult;
import com.zsp.response.TerminalResponse;
import com.zsp.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    /**
     * 添加终端
     * @param name
     * @return
     */
    @PostMapping("/add")
    public ResponseResult<TerminalResponse> add(String name, String desc) {
        return terminalService.add(name, desc);
    }

    /**
     * 终端周边搜索
     * @param center
     * @param radius
     * @return
     */
    @PostMapping("/aroundsearch")
    public ResponseResult aroundsearch(String center, Integer radius) {
        return terminalService.aroundsearch(center, radius);
    }
}
