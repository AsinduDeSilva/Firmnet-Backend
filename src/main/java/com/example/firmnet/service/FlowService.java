package com.example.firmnet.service;

import com.example.firmnet.dto.ControllerResponseDTO;
import com.example.firmnet.dto.FlowDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FlowService {

    private final WebClient webClient;

    @Value("${sdn-controller.socket}")
    private String sdnControllerSocket;

    public FlowService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ControllerResponseDTO addFlow(FlowDTO flowDTO) {
        return webClient.post().uri(sdnControllerSocket + "/flow/add")
                .bodyValue(flowDTO)
                .retrieve()
                .bodyToMono(ControllerResponseDTO.class)
                .block();
    }

    public ControllerResponseDTO deleteFlow(FlowDTO flowDTO) {
        return webClient.post().uri(sdnControllerSocket + "/flow/delete")
                .bodyValue(flowDTO)
                .retrieve()
                .bodyToMono(ControllerResponseDTO.class)
                .block();
    }

    public ControllerResponseDTO enableFlooding() {
        return webClient.post().uri(sdnControllerSocket + "/flood/enable")
                .retrieve()
                .bodyToMono(ControllerResponseDTO.class)
                .block();
    }

    public ControllerResponseDTO disableFlooding() {
        return webClient.post().uri(sdnControllerSocket + "/flood/disable")
                .retrieve()
                .bodyToMono(ControllerResponseDTO.class)
                .block();
    }
}
