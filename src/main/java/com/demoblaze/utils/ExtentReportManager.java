package com.demoblaze.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Tentukan lokasi file output laporan
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // Konfigurasi Tampilan
            sparkReporter.config().setTheme(Theme.STANDARD); // Bisa ganti DARK
            sparkReporter.config().setDocumentTitle("Laporan Ujian Otomasi Dwi");
            sparkReporter.config().setReportName("Hasil Testing Demoblaze");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Info Tambahan di Laporan
            extent.setSystemInfo("Tester", "Dwi Ardianti");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}
