package estudo.com.br;

import java.io.Serializable;

public class Shhhot implements Serializable {

	private int id;	
	private String title;
	private String description;
	private int height;
	private int width;	
	private int likes_count;	
	private int comments_count;	
	private int rebounds_count;
	private String url;	
	private String short_url;
	private int views_count;	
	private int rebound_sourceId;
	private String image_url;	
	private String image_teaser_url;
	private Player player;	
	private String image_400_url;	
	private String created_at;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getLikes_count() {
		return likes_count;
	}
	public void setLikes_count(int likes_count) {
		this.likes_count = likes_count;
	}
	public int getComments_count() {
		return comments_count;
	}
	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}
	public int getRebounds_count() {
		return rebounds_count;
	}
	public void setRebounds_count(int rebounds_count) {
		this.rebounds_count = rebounds_count;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShort_url() {
		return short_url;
	}
	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}
	public int getViews_count() {
		return views_count;
	}
	public void setViews_count(int views_count) {
		this.views_count = views_count;
	}
	public int getRebound_sourceId() {
		return rebound_sourceId;
	}
	public void setRebound_sourceId(int rebound_sourceId) {
		this.rebound_sourceId = rebound_sourceId;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getImage_teaser_url() {
		return image_teaser_url;
	}
	public void setImage_teaser_url(String image_teaser_url) {
		this.image_teaser_url = image_teaser_url;
	}
	public String getImage_400_url() {
		return image_400_url;
	}
	public void setImage_400_url(String image_400_url) {
		this.image_400_url = image_400_url;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getId(){
		return id;
	}
	public String getTitle(){
		return title;
	}
	public String getDescription(){
		return description;
	}
	public String getImage400(){
		return image_400_url;
	}
	public String getImage(){
		return image_url;
	}
	public String getTeaserImage(){
		return image_teaser_url;
	}
	public Player getPlayer(){
		return player;
	}
	@Override
    public String toString(){
        return title;
    }
}
