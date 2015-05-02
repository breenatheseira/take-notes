package com.example.sqltest.models;

public class Notes {
	String id, doc_id, note;
	
	public Notes(){}
	
	public Notes(String id, String doc_id, String note){
		this.id = id;
		this.doc_id = doc_id;
		this.note = note;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setDocId(String did){
		this.doc_id = did;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getDocId(){
		return this.doc_id;
	}
	
	public String getNote(){
		return this.note;
	}
}
