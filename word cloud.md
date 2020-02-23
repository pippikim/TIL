```java
package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import scala.Tuple2;
import vo.Article;
import vo.Word;

@WebServlet(value="/getWords",loadOnStartup = 1)
public class Crawling extends HttpServlet {
	

	static List<Word> words;
	 
	 static {
		 words = new Vector<Word>();
	 }
	 
	private JavaSparkContext sc;
	
	@Override
	public void init() throws ServletException {
		//스파크 서버
		SparkConf conf = new SparkConf().setAppName("word_count").setMaster("local[*]").set("spark.driver.host", "localhost");


​		

		sc = new JavaSparkContext(conf);


​		 
​		

		System.out.println("0) 스파크 서버 켜기 완료");
	}
	
	@Override
	public void destroy() {
		//스파크 멈춤
		sc.stop();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		//유저 사전 경로
		String path = this.getServletContext().getRealPath("WEB-INF"+File.separator+"userdic");
		
		//넘어온 파라미터
		String query = req.getParameter("q");
		
		//만약 넘어온 파라미터가 없다면 하둡으로
		if(query==null) query = "하둡";
		
		//urlencode방식으로 인코딩
		String q = URLEncoder.encode(query, "UTF-8");
		
		//URL을 이용하여 결과 js파일을 얻어옴

//		URL con = new URL(
//				"https://cse.google.com/cse/element/v1?rsz=filtered_cse&num=10&hl=ko&source=gcsc&gss=.com&cselibv=8b2252448421acb3&cx=000172812701156105176:cz9bvbfoi-c&q="
//						+q
//						+"&safe=off&cse_tok=AKaTTZidct1N2Ph7vXQ4VTrTy0fd:1577478141680&sort=&exp=csqr,cc&callback=google.search.cse.api19995");
		URL con = new URL("https://www.itfind.or.kr/ajax/search_cmd.do?query="+q+"&requery=&collection=TREND&alias=ICTNEWS&startnum=0&listnum=10&sort=RANK%2FDESC&startdate=&enddate=&recommend=Y&_=1577528394577");


​		
​		
​		

		//InputStream 연결
		InputStream in = con.openStream();
	
		//Scanner를 이용해서 읽어옴
		Scanner scan = new Scanner(in);
		
		// json형태
		StringBuilder jsonSb = new StringBuilder();
	
		//만약 다음줄이 있으면 json 문자열 합침
		while (scan.hasNext()) {
			jsonSb.append(scan.nextLine());
		}//while end
		
		System.out.println("1) 검색 결과 얻어오기 완료!");
		
		String json = null;
	
		//결과를 자바스크립트의 배열로 만들기 위해서 앞뒤 자름
		//System.out.println(jsonSb);

//		json = jsonSb.substring(jsonSb.indexOf("\"results\"") + 11);
//		json = json.substring(0,json.indexOf("\"findMoreOnGoogle\""));
		json = jsonSb.substring(jsonSb.indexOf("\"resultDocumentList\"") + 11);
		json = json.substring(0,json.indexOf("\"findMoreOnGoogle\""));
		

		//out.print(json+"<hr>");


​		

		//JacksonJson 라이브러리를 이용해 json을 자바의 VO로 변경
		ObjectMapper om = new ObjectMapper();
		Article[] articles = om.readValue(json, Article[].class);
		
		System.out.println("2) json 생성 완료!");
		
		List<String> list = new ArrayList<String>();
		
		//Komoran 형태소 분석기 라이브러리를 활용해서 명사만 추출
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
		komoran.setUserDic(path+File.separator+"userdic.txt");
	
		int cnt = 0;
		//Article마다 반복
		for (vo.Article article : articles) {
			
			//url을 얻어옴
			String url = article.getUnescapedUrl();
			
			//page가 포함된 문서의 경우 글(article)이 아님
			//if (!url.contains("page")) {
				//Jsoup 라이브러리를 이용해 HTML을 파싱함
				Document doc = Jsoup.connect(url).get();
				
				System.out.println("3) HTML 크롤링 "+ ++cnt +"번 글 읽어오기 완료");
				
				//본문이 있는 요소를 선택
				Element element = doc.select(".node_body").get(0);
				System.out.println(element.text());
	
				KomoranResult analyzeResultList = komoran.analyze(element.text());
				
				System.out.println("4) 형태소 분석 완료!");
				
				List<Token> tokenList = analyzeResultList.getTokenList();
				
				for (Token token : tokenList) {
					
					//System.out.println(token);
					
					//잘려진 토큰중에 명사만(의존명사는 제외)
					if(token.getPos().contains("NN") && !token.getPos().equals("NNB")) {
						
						list.add(token.getMorph());
						
					}//if end
					
				}//for end
	
			//} // if end
			//break;
		} // for end
		
		sc.parallelize(list)
		.mapToPair(w -> new Tuple2<String,Integer>(w, 1))
		.reduceByKey((Integer c1, Integer c2) -> c1 + c2)
		.foreach(t->{
			words.add(new Word(t._1,t._2));
		});
		
		//응답은 json으로
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out =  resp.getWriter();
		
		//json을 응답
		out.print(om.writeValueAsString(words));
		
		// 이전의 결과를 지움
		words.clear();
	
	}//doGet() end

}//Crawling end
```





```json
[{"SMALL_CLASS":"","ORIGIN_DUPLICATEURL":"","CATEGORY_ID":"","MIDDLE_CLASS":"ICTNEWS","REGIST_DATE":"2016-04-15 14:24:05.066","ORIGIN_ORIGINALURL":"http://www.zdnet.co.kr/news/news_view.asp?artice_id=20160414155724","APPLICATION":"","ALIAS","KEYWORD","DOCID","APPDATE":"","AGENT","MAIN_CLASS","SUMMARY","MODIFY_DATE","SUMMARY_EN","PUBLISHER_NAME","TITLE_EN","TITLE_EQ","APPID","DETAIL_CLASS","F_IDENTIFIER","FORM_TYPE","CATEGORY_TYPE","FIELD_TYPE","INVENTOR","DATE","LIST_CONTENT","CREATE_DATE","FILE_ORIGIN_NAME","TITLE":"<span style=\"color:#fe1a1a;\">하둡</span>의 아버지 “코어 <span style=\"color:#fe1a1a;\">하둡</span>의 대체재? 상관없다”","PUBLISHER_PLACENAME","IPC","REPRESENT_IMAGE","COUNTRY_CODE"},{"SMALL_CLASS":"","ORIGIN_DUPLICATEURL":"","CATEGORY_ID":"","MIDDLE_CLASS":"ICTNEWS","REGIST_DATE":"2018-08-09 05:00:09.669","ORIGIN_ORIGINALURL":"http://www.etnews.com/20180808000269","APPLICATION":"","ALIAS":"TREND","KEYWORD":"","DOCID":"TodayNews_0000280768","APPDATE":"","AGENT":"","MAIN_CLASS":"TREND","SUMMARY":"네이버 비즈니스 플랫폼(이하 NBP)은 8일 빅데이터 분석 상품인 클라우드 <span style=\"color:#fe1a1a;\">하둡</span>을 비롯한 신규 상품 3종을 출시했다고 밝혔다. 〃클라우드 <span style=\"color:#fe1a1a;\">하둡</span>은 빅데이터를 쉽고 빠르게 처리할 수 있는 오픈소스 기반 분석 서비...","MODIFY_DATE":"","SUMMARY_EN":"","PUBLISHER_NAME":"","TITLE_EN":"","TITLE_EQ":"","APPID":"","DETAIL_CLASS":"","F_IDENTIFIER":"","FORM_TYPE":"TodayNews","CATEGORY_TYPE":"","FIELD_TYPE":"","INVENTOR":"","DATE":"20180808024600","LIST_CONTENT":"","CREATE_DATE":"2018-08-08 14:46:00.0","FILE_ORIGIN_NAME":"","TITLE":"네이버 클라우드 플랫폼, 데이터 분석 및 관리 상품 라인업 강화","PUBLISHER_PLACENAME":"전자신문-SW&바이오","IPC":"","REPRESENT_IMAGE":"","COUNTRY_CODE":""}]
```

//JacksonJson 라이브러리를 이용해 json을 자바의 VO로 변경
		Article[] articles = om.readValue(json, Article[].class);
		log.info("2) json 생성 완료!");
		
		List<String> list = new ArrayList<>();
		
		//Komoran 형태소 분석기 라이브러리를 활용해서 명사만 추출 
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
		komoran.setUserDic(path+File.separator+"userdic.txt");
		
		int cnt = 0; 
		
		//Article 마다 반복
		for(Article article : articles) {
			
			// url 얻어오기
			String url = article.getORIGIN_ORIGINALURL();
			
			//Jsoup 라이브러리를 이용해 HTML을 파싱함
			Document doc = Jsoup.connect(url).get();
			log.info("3) HTML 크롤링"+  ++cnt +"번 읽어오기 완료");
			
			//본문이 있는 요소 선택 
			Element element = doc.select("p").get(0);
			
			log.info(element.text());
		}


​	 

```xml
  



```

