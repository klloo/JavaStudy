package main.java.프로그래머스.매칭점수;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WebPage {
    private String url;
    private int index;
    private int basicPoint;
    private int linkCnt;
    private double matchingPoint;
    private double linkPoint;
    private ArrayList<String> linkedList;
    public WebPage(String url,int index,int basicPoint,int linkCnt, ArrayList<String> linkedList) {
        this.url = url;
        this.index = index;
        this.basicPoint = basicPoint;
        this.linkCnt = linkCnt;
        this.linkedList = new ArrayList<>();
        this.linkedList = linkedList;
        this.linkPoint = 0;
        matchingPoint = basicPoint;
    }
    public void setLinkPoint(double linkPoint) {
        this.linkPoint = linkPoint;
    }

    public double getLinkPoint() {
        return linkPoint;
    }

    public int getBasicPoint() {
        return basicPoint;
    }

    public int getLinkCnt() {
        return linkCnt;
    }

    public void setMatchingPoint() {
        matchingPoint = basicPoint + linkPoint;
    }

    public ArrayList<String> getLinkedList() {
        return linkedList;
    }

    public int getIndex() {
        return index;
    }

    public double getMatchingPoint() {
        return matchingPoint;
    }
}
public class Solution {
    public int solution(String word, String[] pages) {
        int answer = 0;

        Pattern urlPattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"/>");
        Pattern linkPattern = Pattern.compile("<a href=\"(\\S\\s*)\">");
        Pattern wordPattern = Pattern.compile("(?<=[^a-zA-Z])(?i)"+word+"[^a-zA-Z]");

        Map<String,WebPage> webPages = new HashMap<String,WebPage>();
        int index = 0;
        for(String page:pages) {

            Matcher urlMatcher = urlPattern.matcher(page);

            String url = "";
            if(urlMatcher.find()) {
                url = urlMatcher.group();
                url = url.substring(url.indexOf("https://"),url.indexOf("\"/>"));
                System.out.println(url);
            }

            Matcher wordMatcher = wordPattern.matcher(page);

            int basicPoint = 0;
            while(wordMatcher.find()) {
                basicPoint++;
            }

            Matcher linkMatcher = linkPattern.matcher(page);

            ArrayList<String> linkedList = new ArrayList<String>();
            int linkCnt = 0;

            while(linkMatcher.find()){
                linkCnt ++;
                String link = linkMatcher.group();
                link = link.substring(link.indexOf("https://"),link.indexOf(">"));
                linkedList.add(link);
            }
            webPages.put(url,new WebPage(url,index++,basicPoint,linkCnt,linkedList));

        }

        for(WebPage page : webPages.values()) {
            ArrayList<String> linkedList = page.getLinkedList();
            double linkPoint = (double)page.getBasicPoint()/(double)page.getLinkCnt();
            for(String linked : linkedList) {
                if(webPages.containsKey(linked)) {
                    WebPage linkedPage = webPages.get(linked);
                    double linkedPoint = linkedPage.getLinkPoint() + linkPoint;
                    linkedPage.setLinkPoint(linkedPoint);
                    linkedPage.setMatchingPoint();
                }
            }
        }

        double max = 0;
        for(WebPage page : webPages.values()) {
            if(max < page.getMatchingPoint()) {
                answer = page.getIndex();
                max = page.getMatchingPoint();
            }
        }


        return answer;
    }
}
