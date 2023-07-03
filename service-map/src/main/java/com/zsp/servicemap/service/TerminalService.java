package com.zsp.servicemap.service;

import com.zsp.dto.ResponseResult;
import com.zsp.servicemap.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {

    @Autowired
    private TerminalClient terminalClient;

    public ResponseResult add(String name) {
        return terminalClient.add(name);
    }

    public ResponseResult aroundsearch(String center, Integer radius) {
        return terminalClient.aroundsearch(center, radius);
    }
}
