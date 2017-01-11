import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/*Parsing the file and returning the content in "results" 
 *
 * */
public class Utilities {
	// In J++, the console window can close up before you read it,
		// so this method can be used to wait until you're ready to proceed.
		public static void waitHere(String msg)
		{
			System.out.println("");
			System.out.println(msg);
			try { System.in.read(); } catch(Exception e) {} // Ignore any errors while reading.
		}

		public static synchronized String getFileContents(String fileName)
		{
			File file = new File(fileName);
			String results = null;

			try
			{
				int length = (int)file.length(), bytesRead;
				byte byteArray[] = new byte[length];

				ByteArrayOutputStream bytesBuffer = new ByteArrayOutputStream(length);
				FileInputStream       inputStream = new FileInputStream(file);
				bytesRead = inputStream.read(byteArray);
				bytesBuffer.write(byteArray, 0, bytesRead);
				inputStream.close();

				results = bytesBuffer.toString();
			}
			catch(IOException e)
			{
				System.out.println("Exception in getFileContents(" + fileName + "), msg=" + e);
			}

			return results;
		}
}
