package com.example.firmnet.service;

import com.example.firmnet.dto.CrudResponseDTO;
import com.example.firmnet.dto.IotDeviceDTO;
import com.example.firmnet.entity.IotDevice;
import com.example.firmnet.repository.IotDeviceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IotDeviceService {

    private final IotDeviceRepository iotDeviceRepository;
    private final ModelMapper modelMapper;

    public IotDeviceService(IotDeviceRepository iotDeviceRepository, ModelMapper modelMapper) {
        this.iotDeviceRepository = iotDeviceRepository;
        this.modelMapper = modelMapper;
    }

    public void saveDevice(IotDeviceDTO iotDeviceDTO) {
        iotDeviceRepository.save(modelMapper.map(iotDeviceDTO, IotDevice.class));
    }

    public List<IotDeviceDTO> findAll() {
        return iotDeviceRepository.findAll().stream()
                .map(iotDevice -> modelMapper.map(iotDevice, IotDeviceDTO.class))
                .collect(Collectors.toList());
    }

    public IotDeviceDTO findById(Long id) {
        return modelMapper.map(iotDeviceRepository.findById(id), IotDeviceDTO.class);
    }

    public void deleteIotDevice(Long id) {
        iotDeviceRepository.deleteById(id);
    }

    public boolean updateIotDevice(Long id, IotDeviceDTO iotDeviceDTO) {
        Optional<IotDevice> iotDevice = iotDeviceRepository.findById(id);
        if(iotDevice.isPresent()) {
            IotDevice iotDevice1 = iotDevice.get();
            iotDevice1.setName(iotDeviceDTO.getName());
            iotDevice1.setIp(iotDeviceDTO.getIp());
            iotDevice1.setUsername(iotDeviceDTO.getUsername());
            iotDevice1.setPassword(iotDeviceDTO.getPassword());
            iotDevice1.setFirmwareUpdateURL(iotDeviceDTO.getFirmwareUpdateURL());
            iotDevice1.setFirmwareVersion(iotDeviceDTO.getFirmwareVersion());

            iotDeviceRepository.save(iotDevice1);
            return true;
        }
        return false;
    }
}
