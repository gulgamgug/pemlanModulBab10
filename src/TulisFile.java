import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TulisFile {
    public static void main(String[] args) {
        var keyboard = new Scanner(System.in);
        System.out.print("Masukkan teks yang akan disimpan: ");
        var text = keyboard.nextLine();

        try (var writer = new FileWriter("test.txt", true)) {
            writer.write(text);
        } catch (IOException e) {
            System.err.println("Gagal menulis ke file");
        }

        File file = new File("test.txt");
        showFileSize(file);
        listIsiFolder(file);
        hapusFolder();
    }
    static void showFileSize(File file) {
        long length = file.length();
        double size;
        String satuan;
        if (length>=1024*1024) {
            size = (double) length/ (1024*1024);
            satuan = " MB";
        } else if (length>=1024) {
            size = (double) length/1024;
            satuan = " KB";
        } else {
            size = (double) length;
            satuan = " B";
        }
        System.out.println("file size: " + size + satuan);
    }
    static void listIsiFolder(File file) {
        File pathFolder = file.getAbsoluteFile().getParentFile();
        String[] isiFolder = pathFolder.list();
        System.out.println("\nIsi folder " + file.getAbsoluteFile().getParent() + ":");
        for (String nama : isiFolder) {
            System.out.println(nama);
        }
    }
    static void hapusFolder() {
    File folder = new File("FolderContoh");
    if (!folder.exists()) {
        folder.mkdir();
        System.out.println("Folder contoh berhasil dibuat.");
    }
    for (int i = 1; i <= 3; i++) {
        File fileDummy = new File(folder, "File" + i + ".txt");
        try {
            if (fileDummy.createNewFile()) {
                System.out.println(fileDummy.getName() + " berhasil dibuat.");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    System.out.println("Menghapus folder " + folder.getName());

    File[] daftarFile = folder.listFiles();
    if (daftarFile != null) {
        for (File f : daftarFile) {
            boolean fileTerhapus = f.delete();
            System.out.println("Operasi hapus" + f.getName() + ": " + (fileTerhapus ? "sukses" : "gagal"));
        }
    }

    boolean folderTerhapus = folder.delete();
    System.out.println("Menghapus folder " + (folderTerhapus ? "sukses" : "gagal"));
}
}