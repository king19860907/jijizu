package com.jijizu.core.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.jijizu.base.util.RequestUtil;


public class PaginationDTO<T> implements java.io.Serializable {
	
	private static final long serialVersionUID = 1862583307479666158L;
	/**
	 * 总记录数
	 */
	private long recordCnt = 0;
	/**
	 * 起始记录
	 */
	private long begRecordNum;
	
	/**
	 * 结束记录
	 */
	private long endRecordNum;
	
	/**
	 * 总页数
	 */
	private long pageCnt = 1;
	/**
	 * 每页记录数
	 */
	private long  perPageRecordNum = 10;
	/**
	 * 当前页
	 */
	private long curPageNum;
	/**
	 * 前一页
	 */
	private long befPageNum; 
	/**
	 * 下一页
	 */
	private long aftPageNum; 
	/**
	 * 最后一页
	 */
	private long endPageNum = 1;
	/**
	 * 开始页
	 */
	private long begPageNum = 1;
	/**
	 * 查询条件
	 */
	private Map<String,Object>  para;
	/**
	 * 查询结果
	 */
	private List<T> result;
	/**
	 * 分页信息
	 */
	private List<Long> pageList = new ArrayList<Long>();
	
	/**
	 * 每页显示的分页数
	 */
	private int perPagePageSize = 10;
	
	public long getRecordCnt() {
		return recordCnt;
	}
	public void setRecordCnt(long recordCnt) {
		this.recordCnt = recordCnt;
		this.pageCnt = (long)Math.ceil((double)this.recordCnt/this.perPageRecordNum);
		if(this.pageCnt < 1){
			this.pageCnt = 1;
		}
		
		this.aftPageNum = curPageNum+1;
		if(this.aftPageNum > this.pageCnt){
			this.aftPageNum = this.pageCnt;
		}
		
		if(this.curPageNum > this.pageCnt){
		    this.setCurPageNum(this.pageCnt);
		}
		
		this.pageList.clear();
		long centerNum = (long)Math.ceil(this.perPagePageSize/2.0);
		long flag = this.perPagePageSize%2;
		if(this.pageCnt<=this.perPagePageSize){
            for(long i=1; i<=this.pageCnt; i++){
                this.pageList.add(i);
            }		    
		}else if(this.curPageNum <= centerNum){
            for(long i=1; i<=this.perPagePageSize; i++){
                this.pageList.add(i);
            }           
		}else{
            long end = this.pageCnt - this.curPageNum;
		    if(end < centerNum || (end == centerNum && flag == 0)){
	            long begin = this.pageCnt - this.perPagePageSize;
	            for(int i=1; i<=this.perPagePageSize; i++){
	                this.pageList.add(begin + i);
	            }
		    }else{
                long begin = this.curPageNum - centerNum;
                for(int i=1; i<=this.perPagePageSize; i++){
                    this.pageList.add(begin + i);
                }
		    }
		}
	}
	public void setRecordCnt(Object recordCnt) {
		long rcnt = Long.valueOf(recordCnt.toString()).longValue();
		setRecordCnt(rcnt);
	}
	
	public long getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(long pageCnt) {
		this.pageCnt = pageCnt;
	}
	public long getPerPageRecordNum() {
		return perPageRecordNum;
	}
	public void setPerPageRecordNum(long perPageRecordNum) {
		this.perPageRecordNum = perPageRecordNum;
	}
	public long getCurPageNum() {
		return curPageNum;
	}
	public void setCurPageNum(long curPageNum) {
		this.curPageNum = curPageNum;
		if(this.curPageNum <= 0){
			this.curPageNum = 1;
			this.befPageNum = 1;
		}
		if(this.curPageNum > 1){
			this.befPageNum = curPageNum - 1;
		}
		this.aftPageNum = curPageNum + 1;		
		this.begRecordNum = (this.curPageNum - 1) * this.perPageRecordNum;
		this.endRecordNum = this.curPageNum * this.perPageRecordNum;
	}
	
	public void setCurPageNum(String curPageNum) {
		if(curPageNum == null){
			this.setCurPageNum(0L);
		}else{
			this.setCurPageNum(Long.valueOf(curPageNum).longValue());
		}
	}
	
	public long getBefPageNum() {
		return befPageNum;
	}
	public void setBefPageNum(long befPageNum) {
		this.befPageNum = befPageNum;
	}
	public long getAftPageNum() {
		return aftPageNum;
	}
	public void setAftPageNum(long aftPageNum) {
		this.aftPageNum = aftPageNum;
	}
	public long getEndPageNum() {
		return endPageNum;
	}
	public void setEndPageNum(long endPageNum) {
		this.endPageNum = endPageNum;
	}
	public long getBegPageNum() {
		return begPageNum;
	}
	/*
	public void setBegPageNum(long begPageNum) {
		this.begPageNum = begPageNum;
	}
	*/
	public Map<String, Object> getPara() {
		return para;
	}
	
	public void setPara(Map<String, Object> para) {
		this.para = para;
	}
	
	public void setPara(ServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();		
		for (Map.Entry<String, String> entry : RequestUtil.getParaForAction(request).entrySet()){
			map.put(entry.getKey(), entry.getValue());
		}		
		this.para = map;
	}
	
	public long getBegRecordNum() {
		return begRecordNum;
	}
	public void setBegRecordNum(long begRecordNum) {
		this.begRecordNum = begRecordNum;
	}
	public long getEndRecordNum() {
		return endRecordNum;
	}
	public void setEndRecordNum(long endRecordNum) {
		this.endRecordNum = endRecordNum;
	}
	public void setBegPageNum(long begPageNum) {
		this.begPageNum = begPageNum;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
    public List<Long> getPageList() {
        return pageList;
    }
	public int getPerPagePageSize() {
		return perPagePageSize;
	}
	public void setPerPagePageSize(int perPagePageSize) {
		this.perPagePageSize = perPagePageSize;
	}
	
}
