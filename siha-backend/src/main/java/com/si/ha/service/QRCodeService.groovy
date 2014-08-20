package com.si.ha.service;

import java.awt.image.BufferedImage;
import java.io.File;


interface QRCodeService {
	BufferedImage generateImage(String payload)
}
