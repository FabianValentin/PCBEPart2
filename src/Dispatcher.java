import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Article.*;

public class Dispatcher {
	private static HashMap<ArticleType, ArrayList<Subscriber>> domain_subscriber = new HashMap<>();
	private static HashMap<ArticleType, ArrayList<Article>> articles = new HashMap<>();
	private static HashMap<Publisher, ArrayList<Subscriber>> publisher_subscriber = new HashMap<>();
	
	public static void noticeEvent (EventType event_type, Article article, Publisher p) {
		Set<Subscriber> alreadyNotify = new HashSet<Subscriber>();
		switch (event_type) {
			case CREATE_ARTICLE: 
				articles.computeIfAbsent(article.getType(), k -> new ArrayList<Article>()).add(article);
				domain_subscriber.get(article.getType()).forEach(k -> { k.handleCreateArticle(article); alreadyNotify.add(k); }); 
				if (publisher_subscriber.containsKey(p)) {
					publisher_subscriber.get(p).stream().filter(i -> !alreadyNotify.contains(i)).forEach(k -> k.handleCreateArticle(article)); 
				}
				break;
			case MODIFY_ARTICLE: 
				domain_subscriber.get(article.getType()).forEach(k -> { k.handleModifyArticle(article); alreadyNotify.add(k);});
				if (publisher_subscriber.containsKey(p)) {
					publisher_subscriber.get(p).stream().filter(i -> !alreadyNotify.contains(i)).forEach(k -> k.handleModifyArticle(article)); 
				}
				break;
			case REMOVE_ARTICLE: 
				domain_subscriber.get(article.getType()).forEach(k -> {k.handleRemoveArticle(article);alreadyNotify.add(k);});
				if (publisher_subscriber.containsKey(p)) {
					publisher_subscriber.get(p).stream().filter(i -> !alreadyNotify.contains(i)).forEach(k -> k.handleRemoveArticle(article)); 
				}
				break;
			case READ_ARTICLE:
				Publisher.newReader();
		}
	}
	
	public static void addSubscriberToType (ArticleType article_type, Subscriber s) {
		domain_subscriber.computeIfAbsent(article_type, k -> new ArrayList<Subscriber>()).add(s);
	}

	public static void addSubscriberToPublisher(Publisher p, Subscriber s) {
		publisher_subscriber.computeIfAbsent(p, k -> new ArrayList<Subscriber>()).add(s);
	}
}
