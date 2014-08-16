package com.si.ha.device

import org.springframework.data.jpa.repository.JpaRepository

interface DeviceService extends JpaRepository<Device, Long>{
}
