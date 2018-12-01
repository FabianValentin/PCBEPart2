import java.util.Date;

import Article.Article;
import Article.ArticleType;

public class Subscriber {
	private static int ids = 0;
	private int id;
	
	public Subscriber () {
		this.id = ids;
		ids++;
	}
	
	public void subscribeToType (ArticleType type) {
		Dispatcher.addSubscriberToType(type, this);
	}
	
	public void subscribeToPublisher(Publisher p) {
		Dispatcher.addSubscriberToPublisher(p, this);
		
	}
	
	private void readOrNot(int subscriber_id, Article article) {
		int rand = (int)(Math.random()*10); 
		if (rand % 2 == 1) {
			System.out.println("Subscriber " + subscriber_id + " reads article " + article.getId() + ".");
			Dispatcher.noticeEvent(EventType.READ_ARTICLE, article, null);
		} else {
			System.out.println("Subscriber " + subscriber_id + " does not read article " + article.getId() + ".");
		}
	}
	
	public void handleCreateArticle (Article article) {
		System.out.println("Subscriber " + this.id + " received notification: " + article.getId() + " was published.");
		readOrNot(this.id, article);
	}
	
	public void handleModifyArticle (Article article) {
		System.out.println("Subscriber " + this.id + " received notification: " + article.getId() + " was modified.");
		readOrNot(this.id, article);
	}
	
	public void handleRemoveArticle (Article article) {
		System.out.println("Subscriber " + this.id + " received notification: " + article.getId() + " was removed.");
	}
}
