package com.yc.blog.bean;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class User {
    private Integer id;

    @NotEmpty(message = "昵称不能为空")
    @Length(min = 3,max = 16,message = "用户长度必须为3-16位")
    private String name;

    @NotEmpty
    @Length(min = 4,max = 16)
    private String account;


    @NotEmpty
    @Length(min = 6,max = 12)
    private String pwd;


    @NotEmpty
    @Length(min = 11,max = 11)
    private String phone;


    @NotEmpty
    @Email
    private String email;

    private String head;

    @DateTimeFormat(pattern="yyyy-MM-dd")//set
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//get
    private Date createtime;

    private String status;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//get
    public Date getCreatetime() {
        return createtime;
    }
    
    @DateTimeFormat(pattern="yyyy-MM-dd")//set
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", account=" + account + ", pwd=" + pwd + ", phone=" + phone
				+ ", email=" + email + ", head=" + head + ", createtime=" + createtime + ", status=" + status
				+ ", type=" + type + "]";
	}
    
}