package com.example.firmnet.service;

import com.example.firmnet.dto.IotDeviceDTO;
import com.example.firmnet.dto.IotMetricsDTO;
import com.example.firmnet.entity.IotDevice;
import com.example.firmnet.repository.IotDeviceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IotDeviceService {

    private final IotDeviceRepository iotDeviceRepository;
    private final ModelMapper modelMapper;
    private final WebClient webClient;

    public IotDeviceService(IotDeviceRepository iotDeviceRepository, ModelMapper modelMapper, WebClient webClient) {
        this.iotDeviceRepository = iotDeviceRepository;
        this.modelMapper = modelMapper;
        this.webClient = webClient;
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
        Optional<IotDevice> iotDevice = iotDeviceRepository.findById(id);
        return iotDevice.map(device -> modelMapper.map(device, IotDeviceDTO.class)).orElse(null);
    }

    public void deleteIotDevice(Long id) {
        iotDeviceRepository.deleteById(id);
    }

    public boolean updateIotDevice(Long id, IotDeviceDTO iotDeviceDTO) {
        Optional<IotDevice> iotDevice = iotDeviceRepository.findById(id);
        if (iotDevice.isPresent()) {
            IotDevice iotDevice1 = iotDevice.get();
            iotDevice1.setName(iotDeviceDTO.getName());
            iotDevice1.setUsername(iotDeviceDTO.getUsername());
            iotDevice1.setPassword(iotDeviceDTO.getPassword());
            iotDevice1.setIpAddress(iotDeviceDTO.getIpAddress());

            iotDeviceRepository.save(iotDevice1);
            return true;
        }
        return false;
    }

    public IotMetricsDTO getMetrics(Long id) {
        IotDeviceDTO iotDevice = findById(id);

        return webClient.get()
                .uri(iotDevice.getIpAddress() + "/metrics")
                .headers(headers -> headers.setBasicAuth(iotDevice.getUsername(), iotDevice.getPassword()))
                .retrieve()
                .bodyToMono(IotMetricsDTO.class)
                .block();
    }
}
