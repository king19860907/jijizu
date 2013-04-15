package com.jijizu.core.diary.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jijizu.base.util.DateUtil;

public class DiaryWeekCalendar {

	public DiaryWeekCalendar(Date date){
		
		firstDayOfWeek = new DiaryDate(DateUtil.getFirstDayOfWeek(date).getTime());
		
		lastDayOfWeek = new DiaryDate(DateUtil.getLastDayOfWeek(date).getTime());
		
		for (int i =0;i<7;i++){
			if(i==0){
				daysOfWeek.add(firstDayOfWeek);
			}else{
				daysOfWeek.add(new DiaryDate(DateUtil.addDay(firstDayOfWeek, i).getTime()));
			}
		}
		
		nextWeekFirstDay = DateUtil.addDay(firstDayOfWeek.toDate(), 7);
		preWeekFirstDay = DateUtil.addDay(firstDayOfWeek.toDate(), -7);
		
	}
	
	/**
	 * 一周开始日期
	 */
	private DiaryDate firstDayOfWeek;
	
	/**
	 * 一周结束日期
	 */
	private DiaryDate lastDayOfWeek;
	
	/**
	 * 一周的日期列表
	 */
	private List<DiaryDate> daysOfWeek = new ArrayList<DiaryDate>();
	
	/**
	 * 下周的第一天
	 */
	private Date preWeekFirstDay;
	
	/**
	 * 上周的第一天
	 */
	private Date nextWeekFirstDay;
	
	public DiaryDate getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

	public DiaryDate getLastDayOfWeek() {
		return lastDayOfWeek;
	}

	public List<DiaryDate> getDaysOfWeek() {
		return daysOfWeek;
	}

	public Date getPreWeekFirstDay() {
		return preWeekFirstDay;
	}

	public Date getNextWeekFirstDay() {
		return nextWeekFirstDay;
	}

	@Override
	public String toString() {
		return "DiaryWeekCalendar [firstDayOfWeek=" + firstDayOfWeek
				+ ", lastDayOfWeek=" + lastDayOfWeek + ", daysOfWeek="
				+ daysOfWeek + ", preWeekFirstDay=" + preWeekFirstDay
				+ ", nextWeekFirstDay=" + nextWeekFirstDay + "]";
	}

	public static void main(String[] args) {
		DiaryWeekCalendar dw = new DiaryWeekCalendar(new Date());
		System.out.println(dw);
	}
	
}
