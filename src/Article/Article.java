package Article;

import java.util.Date;

public class Article {
	private static int ids = 0;
	private int id;
	private ArticleType type;
	private Date publishing_date, last_change;
	
	public Article (ArticleType type, Date date) {
		this.type = type;
		this.publishing_date = date;
		this.last_change = date;
		this.id = ids;
		ids++;
	}
	
	public void modifyArticle (Date date) {
		this.last_change = date;
	}
	
	public ArticleType getType () {
		return this.type;
	}

	public int getId() {
		return this.id;
	}
}
