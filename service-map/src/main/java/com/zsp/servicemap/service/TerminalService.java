package com.zsp.servicemap.service;

import com.zsp.dto.ResponseResult;
import com.zsp.response.TerminalResponse;
import com.zsp.servicemap.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {

    @Autowired
    private TerminalClient terminalClient;

    public ResponseResult<TerminalResponse> add(String name, String desc) {
        return terminalClient.add(name, desc);
    }

    public ResponseResult aroundsearch(String center, Integer radius) {
        return terminalClient.aroundsearch(center, radius);
    }
}
