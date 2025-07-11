package com.example.firmnet.controller;

import com.example.firmnet.dto.ControllerResponseDTO;
import com.example.firmnet.dto.CrudResponseDTO;
import com.example.firmnet.dto.FlowDTO;
import com.example.firmnet.service.FlowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/flow")
@CrossOrigin
public class FlowController {

    private final FlowService flowService;

    public FlowController(FlowService flowService) {
        this.flowService = flowService;
    }

    @PostMapping
    public ResponseEntity<CrudResponseDTO<ControllerResponseDTO>> addFlow(@RequestBody FlowDTO flowDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CrudResponseDTO<>(true, "Flow added successfully", flowService.addFlow(flowDTO)));
    }

    @DeleteMapping
    public ResponseEntity<CrudResponseDTO<ControllerResponseDTO>> deleteFlow(@RequestBody FlowDTO flowDTO) {
        return ResponseEntity.ok(new CrudResponseDTO<>(true, "Flow deleted successfully", flowService.deleteFlow(flowDTO)));
    }

    @PostMapping("/flood")
    public ResponseEntity<CrudResponseDTO<ControllerResponseDTO>> enableFlooding() {
        return ResponseEntity.ok(new CrudResponseDTO<>(true, "Flooding enabled successfully", flowService.enableFlooding()));
    }

    @DeleteMapping("/flood")
    public ResponseEntity<CrudResponseDTO<ControllerResponseDTO>> disableFlooding() {
        return ResponseEntity.ok(new CrudResponseDTO<>(true, "Flooding disabled successfully", flowService.disableFlooding()));
    }
}
