package com.jijizu.core.diary.dto;

import java.util.Date;

import com.jijizu.base.util.DateUtil;

public class DiaryDayCalendar {
	
	public DiaryDayCalendar(Date date) {
		currentDay = new DiaryDate(DateUtil.getStartDateByDate(date).getTime());
		nextDay = new DiaryDate(DateUtil.addDay(currentDay.toDate(), 1).getTime());
		nextTwoDay = new DiaryDate(DateUtil.addDay(currentDay.toDate(), 2).getTime());
		preTwoDay = new DiaryDate(DateUtil.addDay(currentDay.toDate(), -2).getTime());
	}
	
	/**
	 * 当前天
	 */
	private DiaryDate currentDay;
	
	/**
	 *	下一天 
	 */
	private DiaryDate nextDay;
	
	/**
	 * 下两天
	 */
	private DiaryDate nextTwoDay;
	
	/**
	 * 前两天
	 */
	private DiaryDate preTwoDay;

	public DiaryDate getNextDay() {
		return nextDay;
	}

	public DiaryDate getNextTwoDay() {
		return nextTwoDay;
	}

	public DiaryDate getPreTwoDay() {
		return preTwoDay;
	}

	public DiaryDate getCurrentDay() {
		return currentDay;
	}

	@Override
	public String toString() {
		return "DiaryDayCalendar [currentDay=" + currentDay + ", nextDay="
				+ nextDay + ", nextTwoDay=" + nextTwoDay + ", preTwoDay="
				+ preTwoDay + "]";
	}
	
	public static void main(String[] args) {
		DiaryDayCalendar dd = new DiaryDayCalendar(new Date());
		System.out.println(dd);
	}
}
