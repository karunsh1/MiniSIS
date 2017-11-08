package DTO;

public class Term {
	
	int termid;
	String term;
	
	public Term(){
		super();
	}
	public Term(String term, int termid){
		super();
		this.term = term;
		this.termid = termid;
		
	}
	
	public int getTermid() {
		return termid;
	}

	public void setTermid(int termid) {
		this.termid = termid;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	
	
	

}
