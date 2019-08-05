package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class WebSpider {

    public static void main(String[] args) {

        String url1 = "https://www.bmc.nl/";
        String url2 = "https://www.randstad.nl/";
        String url3 = "https://fcbayern.com/en";
        String url4 = "https://www.unive.nl/";
        String url5 = "https://www.ns.nl/en";
        String url6 = "https://www.weleda.nl/";
        String url7 = "https://www.anwb.nl/";
        String url8 = "https://www.greetz.nl";
        String url9 = "https://www.ovoenergy.com";
        String url10 = "https://www.airmiles.nl/";
        String url11 = "https://ingenico.nl/en";
        String url12 = "https://www.hellermanntyton.nl/";
        String url13 = "https://www.forever21.com/eu/shop";
        String url14 = "https://www.dpv.de/";
        String url15 = "https://www.triodos.com/";

        List<String> urls = new ArrayList<>();

        int amount = 0;
        int amountWebfiles = 0;
        int amountBinaries = 0;
        int amountBloomreach = 0;

        urls.add(url1);
        urls.add(url2);
        urls.add(url3);
        urls.add(url4);
        urls.add(url5);
        urls.add(url6);
        urls.add(url7);
        urls.add(url8);
        urls.add(url9);
        urls.add(url10);
        urls.add(url11);
        urls.add(url12);
        urls.add(url13);
        urls.add(url14);
        urls.add(url15);

        try {
            for (String url : urls) {
                Document document = Jsoup.connect(url).get();

                Elements linkWebfiles = document.select("*[:contains(webfiles)]");
                Elements useWebfiles = document.select("*[xlink:href*=webfiles]");
                Elements metaWebfiles = document.select("*[content*=webfiles]");
                Elements scriptWebfiles = document.select("*[src*=webfiles]");
                Elements imageWebfiles = document.select("*[xlink:href*=webfiles]");

                Elements imgBinaries = document.select("*[src*=binaries]");
                Elements sourceBinaries = document.select("*[srcset*=binaries]");
                Elements spanBinariesLarge = document.select("*[data-image-large*=binaries]");
                Elements spanBinariesMedium = document.select("*[data-image-medium*=binaries]");
                Elements spanBinariesSmall = document.select("*[data-image-small*=binaries]");
                Elements metaBinaries = document.select("*[content*=binaries]");
                Elements divBinaries = document.select("*[style*=binaries]");
                Elements imgDataOriginal = document.select("*[data-original*=binaries]");
                Elements imgDataSourceBinaries = document.select("*[data-src*=binaries]");

                Elements bloomreachScript = document.select("script[src*=bloomreach]");


                amountWebfiles = linkWebfiles.size() + useWebfiles.size() + metaWebfiles.size() + scriptWebfiles.size() +
                        imageWebfiles.size();

                amountBinaries = imgBinaries.size() + sourceBinaries.size() + spanBinariesLarge.size() + spanBinariesMedium.size() +
                        spanBinariesSmall.size() +
                        metaBinaries.size() + divBinaries.size() + imgDataOriginal.size() +
                        imgDataSourceBinaries.size();

                amountBloomreach = bloomreachScript.size();

                amount = amountWebfiles + amountBinaries + amountBloomreach;

                System.out.println(url);
                System.out.println("hits: " + amount);
                System.out.println("amount webfiles: " + amountWebfiles);
                System.out.println("amount binaries: " + amountBinaries);
                System.out.println("amount bloomreach: " + amountBloomreach);
                System.out.println();


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
