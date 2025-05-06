import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader {
    private String fileUrl;
    private int numThreads;

    public FileDownloader(String fileUrl, int numThreads) {
        this.fileUrl = fileUrl;
        this.numThreads = numThreads;
    }

    public void startDownload() {
        try {
            // Open connection to the file URL
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int fileSize = connection.getContentLength();

            // Calculate the chunk size for each thread
            int chunkSize = fileSize / numThreads;

            // Start the threads for downloading the chunks
            for (int i = 0; i < numThreads; i++) {
                int startByte = i * chunkSize;
                int endByte = (i == numThreads - 1) ? fileSize : (startByte + chunkSize - 1);

                // Create and start a new DownloadTask thread
                DownloadTask task = new DownloadTask(fileUrl, startByte, endByte, i + 1);
                new Thread(task).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
