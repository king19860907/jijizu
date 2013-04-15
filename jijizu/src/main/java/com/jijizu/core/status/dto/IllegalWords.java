package com.jijizu.core.status.dto;

import java.io.Serializable;

/**   
 *******************************************************************************
 * @project : 也买网   
 * @type : IllegalWords
 * @function : 敏感词
 *******************************************************************************
 * @version ：1.1.0
 * @creator ：majun   
 * @date ：2012-12-20   
 *******************************************************************************
 * @revision ：
 * @revisor ：   
 * @date ：   
 * @memo ：   
 *******************************************************************************
 */ 

public class IllegalWords implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3014957504561748178L;
	
	private String word;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
}
