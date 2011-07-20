package weibo.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 时间线返回信息
 * @author zhaoli
 */

public class TimeLineInfo {
  private String city_code;
  private int count = 0;
  private String country_code;
  private String from;
  private String fromurl;
  private String geo;
  private String head;
  private String id;
  private String isvip;
  private String location;
  private String mcount;
  private String name;
  private String nick;
  private String origtext;
  private String province_code;
  private String self;
  private String status;
  private String text;
  private long timestamp;
  private String type;
  public String getCity_code() {
    return city_code;
  }
  public void setCity_code(String city_code) {
    this.city_code = city_code;
  }
  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
  }
  public String getCountry_code() {
    return country_code;
  }
  public void setCountry_code(String country_code) {
    this.country_code = country_code;
  }
  public String getFrom() {
    return from;
  }
  public void setFrom(String from) {
    this.from = from;
  }
  public String getFromurl() {
    return fromurl;
  }
  public void setFromurl(String fromurl) {
    this.fromurl = fromurl;
  }
  public String getGeo() {
    return geo;
  }
  public void setGeo(String geo) {
    this.geo = geo;
  }
  public String getHead() {
    return head;
  }
  public void setHead(String head) {
    this.head = head;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getIsvip() {
    return isvip;
  }
  public void setIsvip(String isvip) {
    this.isvip = isvip;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public String getMcount() {
    return mcount;
  }
  public void setMcount(String mcount) {
    this.mcount = mcount;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getNick() {
    return nick;
  }
  public void setNick(String nick) {
    this.nick = nick;
  }
  public String getOrigtext() {
    return origtext;
  }
  public void setOrigtext(String origtext) {
    this.origtext = origtext;
  }
  public String getProvince_code() {
    return province_code;
  }
  public void setProvince_code(String province_code) {
    this.province_code = province_code;
  }
  public String getSelf() {
    return self;
  }
  public void setSelf(String self) {
    this.self = self;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public long getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  
  public String toString(){
    return ReflectionToStringBuilder.toString(this);
  }
}
