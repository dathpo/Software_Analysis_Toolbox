import java.io.*;
import java.util.*;

class Stego {

	/**
	 * A constant to hold the number of bits per byte
	 */
	private final int byteLength = 8;


	/**
	 * A constant to hold the number of bits used to store the size of the file
	 * extracted
	 */
	protected final int sizeBitsLength = 32;


	/**
	 * A constant to hold the number of bits used to store the extension of the
	 * file extracted
	 */
	protected final int extBitsLength = 64;


	protected final int headerBytes = 54;


	/**
	 * Default constructor to create a stego object, doesn't do anything - so we
	 * actually don't need to declare it explicitly. Oh well.
	 */
	public Stego() {
	}


	/**
	 * A method for hiding a string in an uncompressed image file such as a .bmp
	 * or .png You can assume a .bmp will be used
	 * 
	 * @param cover_filename
	 *            - the filename of the cover image as a string including the
	 *            extension
	 * @param payload
	 *            - the string which should be hidden in the cover image.
	 * @return a string which either contains 'Fail' or the name of the stego
	 *         image (including the extension) which has been written out as a
	 *         result of the successful hiding operation. You can assume that
	 *         the images are all in the same directory as the java files
	 */
	public String hideString(String payload, String cover_filename) {
		String outputFile = "stego_string.bmp";
		try {
			FileInputStream fInS = new FileInputStream(cover_filename);
			FileOutputStream fOutS = new FileOutputStream(outputFile);
			for (int i = 0; i < headerBytes; i++) {
				fOutS.write(fInS.read());
			}
			int payloadLength = payload.length();
			for (int i = 0; i < sizeBitsLength; i++) {
				fOutS.write(swapLsb(payloadLength % 2, fInS.read()));
				payloadLength = payloadLength >> 1;
			}
			for (int i = 0; i < payload.length(); i++) {
				int payloadByte = payload.charAt(i);
				for (int j = 0; j < byteLength; j++) {
					fOutS.write(swapLsb(payloadByte % 2, fInS.read()));
					payloadByte = payloadByte >> 1;
				}
			}
			int bytesToWrite = 0;
			while ((bytesToWrite = fInS.read()) != -1) {
				fOutS.write(bytesToWrite);
			}
			fInS.close();
			fOutS.close();
			return outputFile;
		}
		catch (FileNotFoundException e) {
			System.err.println("Unable to find file " + cover_filename + ".");
			return "Fail";
		}
		catch (IOException e) {
			System.err.println("I/O Error.");
			return "Fail";
		}
	}


	/**
	 * The extractString method should extract a string which has been hidden in
	 * the stegoimage
	 * 
	 * @param the
	 *            name of the stego image including the extension
	 * @return a string which contains either the message which has been
	 *         extracted or 'Fail' which indicates the extraction was
	 *         unsuccessful
	 */
	public String extractString(String stego_image) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		try
		{
			FileInputStream fInS = new FileInputStream(stego_image);
			for (int i = 0; i < headerBytes; i++) {
				fInS.read();
			}
			for (int i = 0; i < sizeBitsLength; i++) {
				list.add(fInS.read() % 2);
			}
			Collections.reverse(list);
			int stringLength = 0;
			for (Integer i : list) {
				stringLength = stringLength << 1;
				stringLength += i;
			}
			char[] charArray = new char[stringLength];
			for (int i = 0; i < stringLength; i++) {
				ArrayList<Integer> charBits = new ArrayList<Integer>();
				for (int j = 0; j < byteLength; j++) {
					charBits.add(fInS.read() % 2);
				}
				Collections.reverse(charBits);
				int character = 0;
				for (int j : charBits) {
					character = character << 1;
					character += j;
				}
				charArray[i] = (char) character;
			}
			String extractedString = "";
			for (int i = 0; i < stringLength; i++) {
				extractedString = extractedString + charArray[i];
			}
			fInS.close();
			return extractedString;
		}
		catch (FileNotFoundException e) {
			System.err.println("Unable to find file " + stego_image + ".");
			return "Fail";
		}
		catch (IOException e) {
			System.err.println("I/O Error.");
			return "Fail";
		}
	}


	/**
	 * The hideFile method hides any file (so long as there's enough capacity in
	 * the image file) in a cover image
	 * 
	 * @param file_payload
	 *            - the name of the file to be hidden including the extension,
	 *            you can assume it is in the same directory as the program
	 * @param cover_image
	 *            - the name of the cover image file including the extension,
	 *            you can assume it is in the same directory as the program
	 * @return String - either 'Fail' to indicate an error in the hiding
	 *         process, or the name of the stego image (including the extension)
	 *         written out as a result of the successful hiding process
	 */
	public String hideFile(String file_payload, String cover_image) {
		String outputFile = "stego_file.bmp";
		try {
			FileInputStream fInS = new FileInputStream(cover_image);
			FileOutputStream fOutS = new FileOutputStream(outputFile);
			List<Integer> sizeBits = new ArrayList<Integer>();
			List<Integer> extBits = new ArrayList<Integer>();
			File payloadFile = new File(file_payload);
			File imageFile = new File(cover_image);
			int fileSize = (int) (payloadFile.length() * byteLength);
			for (int i = 0; i < sizeBitsLength; i++) {
				sizeBits.add(fileSize % 2);
				fileSize = fileSize >> 1;
			}
			String extension = file_payload.substring(file_payload.lastIndexOf('.'));
			int currentChar = 0;
			for (int i = 0; i < extension.length(); i++) {
				currentChar = extension.charAt(i);
				for (int j = 0; j < byteLength; j++) {
					extBits.add(currentChar % 2);
					currentChar = currentChar >> 1;
				}
			}
			if (extBits.size() < extBitsLength) {
				for (int i = 0; i < extBitsLength - extBits.size(); i++) {
					extBits.add(0);
				}
			}
			if (payloadFile.length() > imageFile.length()) {
				fInS.close();
				fOutS.close();
				System.err.println("The file to store is too large for the image file selected.");
				return "Fail";
			}
			for (int i = 0; i < headerBytes; i++) {
				fOutS.write(fInS.read());
			}
			int bytesToWrite = 0;
			while ((bytesToWrite = fInS.read()) != -1) {
				fOutS.write(bytesToWrite);
			}
			fInS.close();
			fOutS.close();
			return outputFile;
		}
		catch (FileNotFoundException e) {
			System.err.println("Unable to find file " + file_payload + ".");
			return "Fail";
		}
		catch (IOException e) {
			System.err.println("I/O Error.");
			return "Fail";
		}
	}


	/**
	 * The extractFile method extracts a file from a stego image
	 * 
	 * @param stego_image
	 *            - name of the cover image (including the extension) from which
	 *            to extract, you can assume it is in the same directory as the
	 *            program
	 * @return String - either 'Fail' to indicate an error in the extraction
	 *         process, or the name of the file (including the extension)
	 *         written out as a result of the successful extraction process
	 */
	public String extractFile(String stego_image) {
		try {
			FileInputStream fInS = new FileInputStream(stego_image);
			for (int i = 0; i < headerBytes; i++) {
				fInS.read();
			}
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < sizeBitsLength; i++) {
				list.add(fInS.read() % 2);
			}
			Collections.reverse(list);
			int fileSize = 0;
			for (Integer i : list) {
				fileSize = fileSize << 1;
				fileSize += i;
			}
			char[] charArray = new char[extBitsLength];
			for (int i = 0; i < byteLength; i++) {
				ArrayList<Integer> extension = new ArrayList<Integer>();
				for (int j = 0; j < byteLength; j++) {
					extension.add(fInS.read() % 2);
				}
				Collections.reverse(extension);
				int character = 0;
				for (int j : extension) {
					character = character << 1;
					character += j;
				}
				charArray[i] = (char) character;
			}
			String extension = "";
			for (int i = 0; i < extBitsLength; i++) {
				if (charArray[i] != 0) {
					extension = extension + charArray[i];
				}
			}
			String extractedFile = "extracted_file";
			extractedFile = extractedFile + extension;
			File file = new File(extractedFile);
			file.createNewFile();
			FileOutputStream fOutS = new FileOutputStream(extractedFile);
			int[] byteArray = new int[byteLength];
			int i = 0;
			for (int j = 0; j < fileSize; j++) {
				byteArray[i] = fInS.read() % 2;
				if (i == 7) {
					int bytesToWrite = 0;
					for (int k = 7; k > -1; k--) {
						bytesToWrite = bytesToWrite << 1;
						bytesToWrite += byteArray[k];
					}
					fOutS.write(bytesToWrite);
					bytesToWrite = 0;
				}
				i++;
				i = i % 8;
			}
			fInS.close();
			fOutS.close();
			return extractedFile;
		}
		catch (FileNotFoundException e) {
			System.err.println("Unable to find file " + stego_image + ".");
			return "Fail";
		}
		catch (IOException e) {
			System.err.println("Error");
			return "Fail";
		}
	}


	/**
	 * This method swaps the least significant bit of a byte to match the bit
	 * passed in
	 * 
	 * @param bitToHide
	 *            - the bit which is to replace the lsb of the byte
	 * @param byt
	 *            - the current byte
	 * @return the altered byte
	 */
	public int swapLsb(int bitToHide, int byt) {
		if (bitToHide > byt % 2) {
			byt += 1;
			return byt;
		}
		else if (bitToHide < byt % 2) {
			byt -= 1;
			return byt;
		}
		return byt;
	}

}