package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WebSpider {

    public static void main(String[] args) {

        ArrayList<String> urls = cvsToArrayList("bloomreach_bedrijven.csv");

        try {

            System.out.println("Checking " + urls.size() + " URL's...\n");

            for (String url: urls) {

                System.out.println("URL: " + url +"\n");
                Document document = Jsoup.connect(url).get();

                System.out.println(url);
                System.out.println("Total Hits: " + checkHits(document));
                System.out.println("Hits Webfiles: " + checkWebfiles(document));
                System.out.println("Hits Binaries: " + checkBinaries(document));
                System.out.println("Hits Bloomreach: " + checkBloomreach(document));
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Document getPageSource(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        return document;
    }

    private static int checkHits(Document document) {
        return checkWebfiles(document) + checkBinaries(document) + checkBloomreach(document);
    }

    private static int checkBloomreach(Document document) {
        int amountBloomreach;

        Elements srcBloomReach = document.select("*[src*=bloomreach]");
        Elements classBloomReach = document.select("*[class*=bloomreach]");

        amountBloomreach = srcBloomReach.size() + classBloomReach.size();

        return amountBloomreach;
    }

    private static int checkBinaries(Document document) {
        int amountBinaries;

        Elements srcBinaries = document.select("*[src*=binaries]");
        Elements srcsetBinaries = document.select("*[srcset*=binaries]");
        Elements dataImageLargeBinaries = document.select("*[data-image-large*=binaries]");
        Elements dataImageMediumBinaries = document.select("*[data-image-medium*=binaries]");
        Elements dataImageSmallBinaries = document.select("*[data-image-small*=binaries]");
        Elements contentBinaries = document.select("*[content*=binaries]");
        Elements styleBinaries = document.select("*[style*=binaries]");
        Elements dataOriginalBinaries = document.select("*[data-original*=binaries]");
        Elements dataSourceBinaries = document.select("*[data-src*=binaries]");

        amountBinaries = srcBinaries.size() + srcsetBinaries.size() + dataImageLargeBinaries.size() + dataImageMediumBinaries.size() +
                dataImageSmallBinaries.size() + contentBinaries.size() + styleBinaries.size() + dataOriginalBinaries.size() +
                dataSourceBinaries.size();

        return amountBinaries;
    }

    private static int checkWebfiles(Document document) {
        int amountWebfiles;

        Elements hrefWebfiles = document.select("*[href*=webfiles]");
        Elements xlinkWebfiles = document.select("*[xlink:href*=webfiles]");
        Elements contentWebfiles = document.select("*[content*=webfiles]");
        Elements srcWebfiles = document.select("*[src*=webfiles]");
        Elements dataBgWebfiles = document.select("*[data-bg*=webfiles]");
        Elements dataSrcWebfiles = document.select("*[data-src*=webfiles]");

        amountWebfiles = hrefWebfiles.size() + xlinkWebfiles.size() + contentWebfiles.size() + srcWebfiles.size()
                + dataBgWebfiles.size() + dataSrcWebfiles.size();

        return amountWebfiles;
    }

    private static ArrayList<String> cvsToArrayList(String csvFile) {
        ArrayList<String> urlList = new ArrayList<>();
        try {
            File filename = new File(csvFile);
            Scanner leesFile = new Scanner(filename);
            while (leesFile.hasNext()) {
                urlList.add(leesFile.next());
            }
        } catch (FileNotFoundException fout) {
            fout.printStackTrace();
        }
        return urlList;
    }

    private static ArrayList<String> txtToArrayList() {
        ArrayList<String> urlList = new ArrayList<>();

        try {
            File file = new File("websites.txt");
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                urlList.add(input.next());
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlList;
    }



}
