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

    public String getUrl() {
        return url;
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

        //정규표현식으로 pattern 만들기
        Pattern urlPattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"/>");
        Pattern linkPattern = Pattern.compile("<a href=\"(\\S*)\">");
        Pattern wordPattern = Pattern.compile("(?<=[^a-zA-Z])(?i)"+word+"[^a-zA-Z]");

        ArrayList<WebPage> webPages = new ArrayList<WebPage>();

        int index = 0;
        for(String page:pages) {

            //페이지 url파싱
            Matcher urlMatcher = urlPattern.matcher(page);

            String url = "";
            if(urlMatcher.find()) {
                url = urlMatcher.group();
                url = url.substring(url.indexOf("https://"),url.indexOf("\"/>"));
            }

            Matcher wordMatcher = wordPattern.matcher(page);

            //기본점수 계산
            int basicPoint = 0;
            while(wordMatcher.find()) {
                basicPoint++;
            }


            //링크된 페이지 수와 링크된 페이지들 계산
            Matcher linkMatcher = linkPattern.matcher(page);

            ArrayList<String> linkedList = new ArrayList<String>();
            int linkCnt = 0;

            while(linkMatcher.find()){
                linkCnt ++;
                String link = linkMatcher.group();
                link = link.substring(link.indexOf("https://"),link.indexOf("\">"));
                linkedList.add(link);
            }
            webPages.add(new WebPage(url,index++,basicPoint,linkCnt,linkedList));

        }

        //링크점수 계산
        for(WebPage page : webPages) {
            //현재 확인하고 있는 페이지의 링크된 페이지 목록
            ArrayList<String> linkedList = page.getLinkedList();
            //현재 확인하고 있는 페이지의 기본점/링크된 페이지수
            //이거를 이 페이지에 링크된 페이지들에 더해줘야 됨
            double linkPoint = (double)page.getBasicPoint()/page.getLinkCnt();
            for(String linked : linkedList) {
                // 모든 페이지를 확인하면서 링크된 페이지와 같은 페이지에 링크 점수 더해주기
                for(WebPage linkedPage : webPages) {
                    if(linkedPage.getUrl().equals(linked)) {
                        double linkedPoint = linkedPage.getLinkPoint() + linkPoint;
                        linkedPage.setLinkPoint(linkedPoint);
                        linkedPage.setMatchingPoint();
                    }
                }
            }
        }

        //가장 큰 매칭점수를 가진 페이지의 인덱스 구하기
        webPages.sort((WebPage a,WebPage b )->{
            if(a.getMatchingPoint() > b.getMatchingPoint())
                return -1;
            return 1;
        });
        answer = webPages.get(0).getIndex();


        return answer;
    }
}