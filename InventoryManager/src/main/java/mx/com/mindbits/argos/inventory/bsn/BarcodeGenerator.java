package mx.com.mindbits.argos.inventory.bsn;

public interface BarcodeGenerator {

	byte[] generateCode128(String data, int copies);
}
