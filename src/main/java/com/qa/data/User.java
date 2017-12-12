package com.qa.data;

public class User {

	
	public String name;
	public String job;
	public String id;
	public String createdAt;
	
	public User(){}
	
	public User(String name, String job){
		this.name = name;
		this.job = job;
	}
	public String getName(){
		return name;
	}
	public String getJob(){
		return job;
	}
	public String getId(){
		return id;
	}
	public String getCreatedAt(){
		return createdAt;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setJob(String job){
		this.job = job;
	}
	public void setId(String id){
		this.id = id;
	}
	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}
		
	
}
