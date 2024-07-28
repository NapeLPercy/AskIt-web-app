package Utils;

import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PartConverter {

    public PartConverter() {}

    public final byte[] getByte(Part part) throws IOException {

        InputStream stream = part.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] image = null;
        int read = 0;
        byte[] buffer = new byte[1024];

        while ((read = stream.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }

        image = baos.toByteArray();
        return image;
    }//end
}
