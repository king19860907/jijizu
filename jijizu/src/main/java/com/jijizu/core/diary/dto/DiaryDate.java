package com.jijizu.core.diary.dto;

import java.util.Calendar;
import java.util.Date;

import com.jijizu.base.util.DateUtil;

public class DiaryDate extends Date {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8179990301539955594L;
	
	Calendar cal = Calendar.getInstance();
	
	public DiaryDate() {
		super();
	}
	
	public DiaryDate(long time) {
		this.setTime(time);
	}

	public boolean isLastDayOfWeekend() {
		if(this != null) {
			cal.setTime(this);
			int dayOfweek = cal.get(cal.DAY_OF_WEEK)-1;
			if(dayOfweek == 0) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean isFirstDayOfWeek() {
		if(this != null) {
			cal.setTime(this);
			int dayOfweek = cal.get(cal.DAY_OF_WEEK)-1;
			if(dayOfweek == 1) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean isToday() {
		if(this != null) {
			String toDay = DateUtil.get4yMd(new Date());
			String thisDay = DateUtil.get4yMd(this);
			if(toDay.equals(thisDay)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public int getDayOfWeek() {
		if(this != null) {
			cal.setTime(this);
			return cal.get(cal.DAY_OF_WEEK)-1;
		}else {
			return 0;
		}
	}
	
	public int getDayOfMonth() {
		if(this != null) {
			cal.setTime(this);
			return cal.get(cal.DAY_OF_MONTH);
		}else {
			return 0;
		}
	}
	
	public String getDayStr() {
		return DateUtil.get4yMd(this);
	}
	
	public Date toDate(){
		return new Date(this.getTime());
	}
	
}
