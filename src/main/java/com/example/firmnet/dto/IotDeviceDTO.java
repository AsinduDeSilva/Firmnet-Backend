package com.example.firmnet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IotDeviceDTO {
    private long id;
    private String name;
    private String username;
    private String password;
    private String ip;
    private String firmwareUpdateURL;
    private String firmwareVersion;
}
