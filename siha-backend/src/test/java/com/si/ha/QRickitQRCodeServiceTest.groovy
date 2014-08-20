package com.si.ha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.si.ha.service.QRCodeService;
import com.si.ha.service.QRickitQRCodeService

public class QRickitQRCodeServiceTest {
	@Test
	public void testSampleJSON() {
		String json = '''
{
    "ip": "ipcim",
    "outputs": [
        { "name": "out1", "type" : "bool" },
        { "name": "out2", "type" : "int" },
        { "name": "out3", "type" : "interval" }        
    ],
	"inputs": [
	    { "name": "in1", "type" : "bool" }
    ]
}
		'''.replaceAll("\\s","")
		
		
		def generated = new QRickitQRCodeService().generateImage(json)
//		ImageIO.write(generated, "jpg", new File('test.jpg'));
	}
}
