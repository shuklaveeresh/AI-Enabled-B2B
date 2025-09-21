package com.highradius.training.dao;

public class POJO {
private Integer film_id;
private String title;
private String description;
private Integer release_year;
private String  language;
private String rating;
private String director;
private String special_features;
private Integer pagecount;
public Integer getPagecount() {
	return pagecount;
}
public void setPagecount(Integer pagecount) {
	this.pagecount = pagecount;
}
public Integer getFilm_id() {
	return film_id;
}
public void setFilm_id(Integer film_id) {
	this.film_id = film_id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Integer getRelease_year() {
	return release_year;
}
public void setRelease_year(Integer release_year) {
	this.release_year = release_year;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getRating() {
	return rating;
}
public void setRating(String rating) {
	this.rating = rating;
}
public String getDirector() {
	return director;
}
public void setDirector(String director) {
	this.director = director;
}
public String getSpecial_features() {
	return special_features;
}
public void setSpecial_features(String special_features) {
	this.special_features = special_features;
}


}
