package com.si.ha.device.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.si.ha.device.Device;

public interface DeviceDao extends JpaRepository<Device, Long>{

}
