package com.ssm.bean;

public class Birth {

	private static final long serialVersionUID = 1L;
	

	/** 序号 **/
	private Integer id;

	/** name **/
	private String name;

	/** 生日 **/
	private String birthday;

	public Birth(){

	}

	public Birth(String name,String birthday){
		this.name=name;
		this.birthday=birthday;
	}
	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}
	
	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}
	
	public void setBirthday(String birthday){
		this.birthday=birthday;
	}

	public String getBirthday(){
		return this.birthday;
	}
	
}