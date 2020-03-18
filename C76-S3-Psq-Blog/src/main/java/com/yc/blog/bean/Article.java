package com.yc.blog.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Article {
    private Integer id;

    private String author;

    private String title;

    private String keywords;

    private String description;

    private Integer categoryid;

    private String label;

    private String titleimgs;

    private String status;

    @DateTimeFormat(pattern="yyyy-MM-dd")//set
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//get
    private Date createtime;

    private Integer readcnt;

    private Integer agreecnt;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getTitleimgs() {
        return titleimgs;
    }

    public void setTitleimgs(String titleimgs) {
        this.titleimgs = titleimgs == null ? null : titleimgs.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//get
    public Date getCreatetime() {
        return createtime;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")//set
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getReadcnt() {
        return readcnt;
    }

    public void setReadcnt(Integer readcnt) {
        this.readcnt = readcnt;
    }

    public Integer getAgreecnt() {
        return agreecnt;
    }

    public void setAgreecnt(Integer agreecnt) {
        this.agreecnt = agreecnt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	@Override
	public String toString() {
		return "Article [id=" + id + ", author=" + author + ", title=" + title + ", keywords=" + keywords
				+ ", description=" + description + ", categoryid=" + categoryid + ", label=" + label + ", titleimgs="
				+ titleimgs + ", status=" + status + ", createtime=" + createtime + ", readcnt=" + readcnt
				+ ", agreecnt=" + agreecnt + ", content=" + content + "]";
	}
    
}