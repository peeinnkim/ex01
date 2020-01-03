package com.khrd.domain;

import java.util.Date;

public class ReplyVO {
	private int rno;
	private int bno;
	private String replytext;
	private String replyer;
	private Date regdate;
	private Date updatedate;

	public ReplyVO() {
		// TODO Auto-generated constructor stub
	}   

	public ReplyVO(int bno, String replytext, String replyer) {
		this.rno = 0;
		this.bno = bno;
		this.replytext = replytext;
		this.replyer = replyer;
		this.regdate = new Date();
		this.updatedate = new Date();
	}
	
	public ReplyVO(int rno, int bno, String replytext, String replyer, Date regdate, Date updatedate) {
		this.rno = rno;
		this.bno = bno;
		this.replytext = replytext;
		this.replyer = replyer;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getReplytext() {
		return replytext;
	}

	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {
		return "Reply [rno=" + rno + ", bno=" + bno + ", replytext=" + replytext + ", replyer=" + replyer + ", regdate="
				+ regdate + ", updatedate=" + updatedate + "]";
	}

}// Reply
