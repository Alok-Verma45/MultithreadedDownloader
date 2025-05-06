import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get file URL and number of threads from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file URL: ");
        String fileUrl = scanner.nextLine();

        System.out.print("Enter the number of threads: ");
        int numThreads = scanner.nextInt();

        // Create an instance of FileDownloader to manage the downloading process
        FileDownloader downloader = new FileDownloader(fileUrl, numThreads);

        // Start the download
        downloader.startDownload();
    }
}
