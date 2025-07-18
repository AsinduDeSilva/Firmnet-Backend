package com.example.firmnet.service;

import com.example.firmnet.dto.ControllerResponseDTO;
import com.example.firmnet.dto.FlowDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FlowService {

    private final WebClient webClient;
    private final String sdnControllerUrl;

    public FlowService(WebClient webClient,
                       @Value("${sdn.controller.ip}") String sdnControllerIp,
                       @Value("${sdn.controller.port}") String sdnControllerPort) {

        this.webClient = webClient;
        this.sdnControllerUrl = String.format("http://%s:%s", sdnControllerIp, sdnControllerPort);
    }

    public ControllerResponseDTO addFlow(FlowDTO flowDTO) {
        return webClient.post().uri(sdnControllerUrl + "/flow/add")
                .bodyValue(flowDTO)
                .retrieve()
                .bodyToMono(ControllerResponseDTO.class)
                .block();
    }

    public ControllerResponseDTO deleteFlow(FlowDTO flowDTO) {
        return webClient.post().uri(sdnControllerUrl + "/flow/delete")
                .bodyValue(flowDTO)
                .retrieve()
                .bodyToMono(ControllerResponseDTO.class)
                .block();
    }

    public ControllerResponseDTO enableFlooding() {
        return webClient.post().uri(sdnControllerUrl + "/flood/enable")
                .retrieve()
                .bodyToMono(ControllerResponseDTO.class)
                .block();
    }

    public ControllerResponseDTO disableFlooding() {
        return webClient.post().uri(sdnControllerUrl + "/flood/disable")
                .retrieve()
                .bodyToMono(ControllerResponseDTO.class)
                .block();
    }
}
