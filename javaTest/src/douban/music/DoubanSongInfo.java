package douban.music;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class DoubanSongInfo {
  private String picture;
  private String albumtitle;
  private String company;
  private float rating_avg;
  private String public_time;
  private String ssid;
  private String album;
  private int like;
  private String artist;
  private String url;
  private String title;
  private String subtype;
  private int length;
  private String sid;
  private String aid;
  
  public String getPicture() {
    return picture;
  }
  public void setPicture(String picture) {
    this.picture = picture;
  }
  public String getAlbumtitle() {
    return albumtitle;
  }
  public void setAlbumtitle(String albumtitle) {
    this.albumtitle = albumtitle;
  }
  public String getCompany() {
    return company;
  }
  public void setCompany(String company) {
    this.company = company;
  }
  public String getPublic_time() {
    return public_time;
  }
  public void setPublic_time(String public_time) {
    this.public_time = public_time;
  }
  public String getSsid() {
    return ssid;
  }
  public void setSsid(String ssid) {
    this.ssid = ssid;
  }
  public String getAlbum() {
    return album;
  }
  public void setAlbum(String album) {
    this.album = album;
  }
  public String getArtist() {
    return artist;
  }
  public void setArtist(String artist) {
    this.artist = artist;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getSubtype() {
    return subtype;
  }
  public void setSubtype(String subtype) {
    this.subtype = subtype;
  }
  public float getRating_avg() {
    return rating_avg;
  }
  public void setRating_avg(float rating_avg) {
    this.rating_avg = rating_avg;
  }
  public int getLike() {
    return like;
  }
  public void setLike(int like) {
    this.like = like;
  }
  public int getLength() {
    return length;
  }
  public void setLength(int length) {
    this.length = length;
  }
  public String getSid() {
    return sid;
  }
  public void setSid(String sid) {
    this.sid = sid;
  }
  public String getAid() {
    return aid;
  }
  public void setAid(String aid) {
    this.aid = aid;
  }
   
  public String toString(){
    return ReflectionToStringBuilder.toString(this);
  }
}
