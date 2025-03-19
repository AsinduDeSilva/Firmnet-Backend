package com.example.firmnet.controller;

import com.example.firmnet.dto.CrudResponseDTO;
import com.example.firmnet.dto.IotDeviceDTO;
import com.example.firmnet.service.IotDeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/device")
public class IotDeviceController {


    private final IotDeviceService iotDeviceService;

    public IotDeviceController(IotDeviceService iotDeviceService) {
        this.iotDeviceService = iotDeviceService;
    }

    @PostMapping
    public ResponseEntity<CrudResponseDTO<IotDeviceDTO>> addIotDevice(@RequestBody IotDeviceDTO iotDeviceDTO) {
        iotDeviceService.saveDevice(iotDeviceDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CrudResponseDTO<>(true, "Device added successfully"));
    }

    @GetMapping
    public List<IotDeviceDTO> getAllIotDevices() {
        return iotDeviceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrudResponseDTO<IotDeviceDTO>> getIotDeviceById(@PathVariable Long id) {
        IotDeviceDTO device = iotDeviceService.findById(id);

        if(device == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new CrudResponseDTO<>(false, "Device not found"));
        }
        return ResponseEntity.ok(new CrudResponseDTO<>(true, "Device found", device));

    }

    @PutMapping("/{id}")
    public ResponseEntity<CrudResponseDTO<IotDeviceDTO>> updateIotDevice(@PathVariable Long id, @RequestBody IotDeviceDTO iotDeviceDTO) {
        boolean isUpdated = iotDeviceService.updateIotDevice(id, iotDeviceDTO);
        if(isUpdated) {
            return ResponseEntity.ok(new CrudResponseDTO<>(true, "Successfully updated IoT device"));
        }
        return ResponseEntity.ok(new CrudResponseDTO<>(false, "Failed to update IoT device"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CrudResponseDTO<IotDeviceDTO>> deleteIotDeviceById(@PathVariable Long id) {
        iotDeviceService.deleteIotDevice(id);
        return ResponseEntity.ok(new CrudResponseDTO<>(true, "Successfully deleted IoT device"));
    }
}
