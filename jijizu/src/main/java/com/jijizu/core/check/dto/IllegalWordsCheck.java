package com.jijizu.core.check.dto;

import java.io.Serializable;
import java.util.List;

import com.jijizu.core.constant.InitData;

public class IllegalWordsCheck implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4541737619621780772L;
	
	/**
	 * 是否开启敏感词替换
	 */
	private boolean isIllegalWordReplace;
	
	/**
	 * 敏感词替换符
	 */
	private String illegalWordReplaceWord;
	
	/**   
	 *******************************************************************************
	 * @function : 敏感词检查
	 * @param word
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2012-12-20   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	public String checkIllegalword(String word) {
		if(isIllegalWordReplace){
			StringBuilder strb = new StringBuilder();
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				String find = null;
				if (InitData.illegalwordMap.containsKey(c)) {
					List<String> words = InitData.illegalwordMap.get(c);
					for (String s : words) {
						String temp = word.substring(i,
								(s.length() <= (word.length() - i)) ? i + s.length()
										: i);
						if (s.equalsIgnoreCase(temp)) {
							find = s;
							break;
						}
					}
				}
				if (find != null) {
					strb.append(illegalWordReplaceWord);
					i += (find.length() - 1);
				} else {
					strb.append(c);
				}
			}
			return strb.toString();
		}else{
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (InitData.illegalwordMap.containsKey(c)) {
					List<String> words = InitData.illegalwordMap.get(c);
					for (String s : words) {
						String temp = word.substring(i,
								(s.length() <= (word.length() - i)) ? i + s.length()
										: i);
						if (s.equalsIgnoreCase(temp)) {
							return null;
						}
					}
				}
			}
		}
		return word;
	}

	public void setIllegalWordReplace(boolean isIllegalWordReplace) {
		this.isIllegalWordReplace = isIllegalWordReplace;
	}

	public void setIllegalWordReplaceWord(String illegalWordReplaceWord) {
		this.illegalWordReplaceWord = illegalWordReplaceWord;
	}
	
}
