package com.be.poten.service;

import com.be.poten.domain.Article;
import com.be.poten.mapper.CrawlerMapper;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CrawlerService {

    private final CrawlerMapper crawlerMapper;

    @Transactional
    public void newDataCrawling() {
        //다운받은 드라이버 (프로그램) 명과 경로(경로 끝에 프로그램명.exe 작성)

        WebDriver driver;
        WebDriver driver2;
        String WEB_DRIVER_ID = "webdriver.chrome.driver";
//        private static final String WEB_DRIVER_PATH = "D:/chromedriver_win32/chromedriver.exe";
        String WEB_DRIVER_PATH = "src/main/resources/chromedriver";
        String base_url;

        //setProperty 메소드를 통해 프로그램명과 경로 받기
        //base_url에 스크래핑 할 브라우저 url 작성
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("--start-maximized"); // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking"); // 팝업 무시
        options.addArguments("--disable-default-apps"); // 기본앱 사용안함

        driver = new ChromeDriver();
        driver2 = new ChromeDriver();

        int maxPage = 10;
        int sid = 105; // 뉴스 카테고리 - 정치, 경제, 사회, 생활문화, 세계, IT/과학 (100~105)
        base_url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=" + sid + "#&date=%2000:00:00&page=";


        try {

            // 저장할 자료 구조
            List<Article> articleList = null;

            for(int i=1; i<=maxPage; i++) {

                articleList = new ArrayList<>();

                driver.get(base_url + i);

                Thread.sleep(1000);

                List<WebElement> ulList = driver.findElement(By.id("section_body")).findElements(By.tagName("ul"));

                int checkNum1 = 0;

                System.out.println("===================");
                System.out.println("page : " + i);

                for(WebElement e : ulList) {
                    List<WebElement> liList = e.findElements(By.tagName("li"));

                    for(WebElement li : liList) {
                        System.out.println(checkNum1);

                        String title = li.findElements(By.tagName("dt")).get(1).findElement(By.tagName("a")).getText();
                        String url = li.findElement(By.tagName("a")).getAttribute("href");
                        String imgUrl = li.findElement(By.className("photo")).findElement(By.tagName("a")).findElement(By.tagName("img")).getAttribute("src");
                        String company = li.findElement(By.tagName("dl")).findElement(By.tagName("dd")).findElement(By.className("writing")).getText();

                        System.out.println("title : " + title);
                        System.out.println("url : " + url);
                        System.out.println("imgUrl : " + imgUrl);
                        System.out.println("company : " + company);

                        // 상세 페이지 조회
                        driver2.get(url);

                        String articleIdFront = url.split("/article/")[1].split("/")[0];
                        String articleIdEnd = url.split("/article/")[1].split("/")[1].split("\\?")[0];
                        String articleId = articleIdFront + "_" + articleIdEnd;
                        String categoryId = url.split("sid=")[1];

                        String articleCreatedDate = driver2.findElement(By.className("_ARTICLE_DATE_TIME")).getAttribute("data-date-time");
                        String content = driver2.findElement(By.id("newsct_article")).getText();

                        System.out.println("createDateTime : " + articleCreatedDate);
//                        System.out.println("content : " + content);
                        System.out.println("articleId : " + articleId);
                        System.out.println("categoryId : " + categoryId);


                        // TODO: 아티클 생성일이 지정 기간에 해당하지 않으면 for문 중단


                        // 아티클 리스트에 추가
                        Article article = new Article(articleId, categoryId, articleCreatedDate, company, title, content, imgUrl, url);
                        articleList.add(article);

                        checkNum1++;
                    }

                System.out.println("===================");

            }

                // data 적재
                articleList.forEach(v -> System.out.println("[" + v.getArticleCreatedDate() + "]" + v.getArticleId() + " : " + v.getTitle()));
                crawlerMapper.insertArticleList(articleList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }

    public void test() throws ParseException {

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date articleDate = transFormat.parse("2023-12-09 15:22:01");
        Date now = new Date();

        System.out.println(now);

        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE, -7);

        String day = transFormat.format(cal.getTime());

        System.out.println(day);

        // 현재 ~ 현재-7일 까지 크롤링 할거임
        // 끊기면 안됨..

    }
}
