import java.util.Date;

import Article.ArticleType;

public class Main {

	public static void main(String[] args) {
		Subscriber s1 = new Subscriber();
		Subscriber s2 = new Subscriber();
		Publisher p1 = new Publisher();
		Publisher p2 = new Publisher();
		
		s1.subscribeToType(ArticleType.WEATHER);
		s1.subscribeToType(ArticleType.SPORT);
		s1.subscribeToPublisher(p1);
		
		s2.subscribeToType(ArticleType.WEATHER);
		s2.subscribeToType(ArticleType.NEWS);
		
		p1.writeArticle(ArticleType.WEATHER, new Date(2000,12,21));
		System.out.println();
		p1.writeArticle(ArticleType.NEWS, new Date(2000,03,21));
		System.out.println();
		p2.writeArticle(ArticleType.NEWS, new Date(2000,03,21));
	}
}
