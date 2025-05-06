import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask implements Runnable {
    private String fileUrl;
    private int startByte;
    private int endByte;
    private int threadNumber;

    public DownloadTask(String fileUrl, int startByte, int endByte, int threadNumber) {
        this.fileUrl = fileUrl;
        this.startByte = startByte;
        this.endByte = endByte;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        try {
            // Connect to the image URL
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Range", "bytes=" + startByte + "-" + endByte);

            // Input stream to read the chunk
            InputStream inputStream = connection.getInputStream();

            // Create a RandomAccessFile to write to a specific part of the image
            FileOutputStream file = new FileOutputStream("downloaded_image.jpg", true);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                file.write(buffer, 0, bytesRead);
            }

            System.out.println("Thread " + threadNumber + " finished downloading its chunk.");

            file.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
