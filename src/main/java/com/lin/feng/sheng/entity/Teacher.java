package com.lin.feng.sheng.entity;

import java.io.Serializable;

public class Teacher implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -8198990074402042659L;
	private Integer id ;
	private String name ;
	private Long age;


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
		this.name = name;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", age=" + age + "]";
	}



}
