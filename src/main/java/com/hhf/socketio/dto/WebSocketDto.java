package com.hhf.socketio.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WebSocketDto implements Serializable {

    private String type;

    private List<String> agentCodes;

    private String empCode;

}
