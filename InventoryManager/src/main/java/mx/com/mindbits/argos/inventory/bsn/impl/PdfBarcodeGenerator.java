package mx.com.mindbits.argos.inventory.bsn.impl;

import java.io.ByteArrayOutputStream;

import mx.com.mindbits.argos.inventory.bsn.BarcodeGenerator;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfBarcodeGenerator implements BarcodeGenerator {

	private final Logger LOGGER = Logger.getLogger(PdfBarcodeGenerator.class);
	
	protected int fileColumns;
	
	public PdfBarcodeGenerator(int fileColumns) {
		this.fileColumns = fileColumns;
	}
	
	@Override
	public byte[] generateCode128(String data, int copies) {
		byte[] response = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			Document document = new Document(PageSize.LETTER);
			PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
			PdfPTable pdfTable = new PdfPTable(fileColumns);
			
			document.open();
			
			for(int i = 0; i < copies; i++) {
				pdfTable.addCell(createBarcode(data, pdfWriter));
			}
			
			int emptyCells = copies % fileColumns;
			for (int i = 0; i < emptyCells; i++) {
				pdfTable.addCell(new PdfPCell());
			}
			
			document.add(pdfTable);
			document.close();
			
			response = baos.toByteArray();
		}catch(DocumentException dex) {
			LOGGER.error("Error generating file: " + dex.getMessage(), dex);
		}
		
		return response;
	}

	protected PdfPCell createBarcode(String data, PdfWriter writer) {
		Barcode128 barcode = new Barcode128();
		barcode.setCodeType(Barcode128.CODE128);
		barcode.setCode(data);
		
		PdfPCell pdfCell = new PdfPCell(barcode.createImageWithBarcode(
				writer.getDirectContent(), BaseColor.BLACK, BaseColor.GRAY), true);
		
		pdfCell.setPadding(10);
		
		return pdfCell;
	}
	
}
