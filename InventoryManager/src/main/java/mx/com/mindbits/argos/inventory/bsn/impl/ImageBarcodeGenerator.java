package mx.com.mindbits.argos.inventory.bsn.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import mx.com.mindbits.argos.inventory.bsn.BarcodeGenerator;

import org.apache.log4j.Logger;

import com.itextpdf.text.pdf.Barcode128;

public class ImageBarcodeGenerator implements BarcodeGenerator {

	private final Logger LOGGER = Logger.getLogger(ImageBarcodeGenerator.class);
	
	@Override
	public byte[] generateCode128(String data, int copies) {
		byte[] result = null;
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			Barcode128 barcode = new Barcode128();
			barcode.setCodeType(Barcode128.CODE128);
			barcode.setCode(data);
			
			Image image = barcode.createAwtImage(Color.BLACK, Color.WHITE);
			BufferedImage outImage = new BufferedImage(image.getWidth(null), (image.getHeight(null) + image.getHeight(null) / 2), BufferedImage.TYPE_INT_ARGB);
			Graphics g = outImage.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.setColor(Color.BLACK);
			g.drawString(data, image.getWidth(null) / 6, image.getHeight(null) + image.getHeight(null) / 2);
			ImageIO.write(outImage, "png", baos);
			baos.flush();

			result = baos.toByteArray();
		}catch(IOException ex) {
			LOGGER.error("Error generating file: " + ex.getMessage(), ex);
		}
		
		return result;
	}

}
