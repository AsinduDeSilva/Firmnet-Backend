package com.example.firmnet.repository;

import com.example.firmnet.entity.IotDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IotDeviceRepository extends JpaRepository<IotDevice, Long> {
    @Override
    Optional<IotDevice> findById(Long id);
}
