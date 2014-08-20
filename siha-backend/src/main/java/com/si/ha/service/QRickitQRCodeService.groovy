package com.si.ha.service;

import java.awt.image.BufferedImage

import javax.imageio.ImageIO

import org.springframework.stereotype.Service

@Service
class QRickitQRCodeService implements QRCodeService {

	BufferedImage generateImage(String payload) {
		String encodedPayload = URLEncoder.encode(payload, 'UTF-8')
		return ImageIO.read(new URL("http://qrickit.com/api/qr?d=${encodedPayload}"))
	}

}
