package com.demoblaze.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    // Method ini menyimpan file dan mengembalikan path relatif untuk laporan
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            // 1. Buat nama file unik
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";

            // 2. Tentukan lokasi simpan FISIK (di folder test-output/screenshots)
            // Laporan ada di test-output, jadi kita buat folder screenshots di dalamnya
            String directory = System.getProperty("user.dir") + "/test-output/screenshots/";
            File finalDestination = new File(directory + fileName);

            // 3. Simpan File
            FileUtils.copyFile(source, finalDestination);

            // 4. Return RELATIVE PATH (Ini kuncinya agar gambar tidak pecah di HTML)
            // Karena file HTML ada di folder "test-output", dia cukup panggil "screenshots/namafile.png"
            return "screenshots/" + fileName;

        } catch (IOException e) {
            System.out.println("Gagal ambil screenshot: " + e.getMessage());
            return null;
        }
    }
}
