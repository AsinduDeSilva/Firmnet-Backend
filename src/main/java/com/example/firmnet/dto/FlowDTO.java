package com.example.firmnet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowDTO {
    String src_ip;
    String dst_ip;
    Long dpid;
}
