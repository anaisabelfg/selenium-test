package com.williamhill.test;

public enum Webdriver {

    FIREFOX("firefox/geckodriver.exe", "firefox/geckodriverMac", "firefox/geckodriverMac"),
    CHROME("chrome/chromedriver.exe", "chrome/chromedriverMac", "chrome/chromedriverLinux"),
    IE("ie/IEDriverServer.exe", "", ""),
    SAFARI("", "SafariDriver.safariextz", "");

    public static String path = "webdrivers/";
    private String windows;
    private String mac;
    private String linux;

    Webdriver(String windows, String mac, String linux) {
        this.windows = windows;
        this.mac = mac;
        this.linux = linux;
    }

    public String getWindows() {
        return path + windows;
    }

    public String getMac() {
        return path + mac;
    }

    public String getLinux() {
        return path + linux;
    }

    public String getCorrectWebdriver(String os) {
        if (os.toLowerCase().contains("windows")) return this.getWindows();
        else if (os.toLowerCase().contains("mac")) return this.getMac();
        else return this.getLinux();
    }
}
