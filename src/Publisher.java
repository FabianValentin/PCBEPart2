import java.util.Date;

import Article.*;

public class Publisher {
	private static int ids = 0;
	private static int readers = 0;
	private int id;
	
	public Publisher () {
		this.id = ids;
		ids++;
	}
	
	public void writeArticle (ArticleType type, Date d) {
		Article article = new Article(type, d);
		Dispatcher.noticeEvent(EventType.CREATE_ARTICLE, article, this);
	}
	
	public static void newReader () {
		readers++;
		System.out.println("New reader added. Total number: " + readers + ".");
	}
}
