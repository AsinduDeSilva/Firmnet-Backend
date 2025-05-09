package com.example.firmnet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IotMetricsDTO {
    private long uptime;
    private int freeHeapBytes;
    private int totalHeapBytes;
    private double heapUsagePercent;
    private double chipTemperatureCelcius;
    private int cpuFreqMHz;
    private int rssi;
    private String sdkVersion;
}
