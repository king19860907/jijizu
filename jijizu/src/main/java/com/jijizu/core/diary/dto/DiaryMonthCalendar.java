package com.jijizu.core.diary.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jijizu.base.util.DateUtil;

public class DiaryMonthCalendar {
	
	public DiaryMonthCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		dayOfMonth = cal.getActualMaximum(cal.DAY_OF_MONTH);
		monthOfYear = cal.get(cal.MONTH)+1;
		year = cal.get(cal.YEAR);
		
		currentMonthDay = getDiaryDateList(1, dayOfMonth, date);
		
		firstDayOfWeek = currentMonthDay.get(0).getDayOfWeek();
		lastDayOfWeek = currentMonthDay.get(dayOfMonth-1).getDayOfWeek();
		
		nextMonthDate = DateUtil.addMonth(date, 1);
		//如果一个月的最后一天为星期天 则无需取下月数据
		if(lastDayOfWeek != 0) {
			nextMonthDay = this.getDiaryDateList(1, 7-lastDayOfWeek, nextMonthDate);
		}
		
		preMonthDate = DateUtil.addMonth(date, -1);
		Calendar preCal = Calendar.getInstance();
		preCal.setTime(preMonthDate);
		int preDayOfMonth = preCal.getActualMaximum(preCal.DAY_OF_MONTH);
		preMonthDay = this.getDiaryDateList(preDayOfMonth-firstDayOfWeek+2, preDayOfMonth, preMonthDate);
		
		if(preMonthDay != null && preMonthDay.size() > 0){
			startDate = new Date(preMonthDay.get(0).getTime());
		}else{
			startDate = new Date(currentMonthDay.get(0).getTime());
		}
		
		if(nextMonthDay != null && nextMonthDay.size() > 0){
			endDate = new Date(nextMonthDay.get(nextMonthDay.size()-1).getTime());
		}else{
			endDate = new Date(currentMonthDay.get(currentMonthDay.size()-1).getTime());
		}
	}
	
	/**   
	 *******************************************************************************
	 * @function : 更具日期获取当月日期列表
	 * @param start	开始	日期
	 * @param end	结束日期
	 * @param date	
	 * @return
	 *******************************************************************************
	 * @creator ：majun   
	 * @date ：2013-4-2   
	 *******************************************************************************
	 * @revisor ：   
	 * @date ：   
	 * @memo ：   
	 *******************************************************************************
	 */
	private List<DiaryDate> getDiaryDateList(int startDayOfMonth,int endDayOfMonth,Date date){
		List<DiaryDate> dateList = new ArrayList<DiaryDate>();
		int year = DateUtil.getYearByDate(date);
		int month = DateUtil.getMonthByDate(date);
		for(int i=startDayOfMonth;i<=endDayOfMonth;i++) {
			dateList.add(new DiaryDate(DateUtil.getDate(year, month, i).getTime()));
		}
		return dateList;
	}
	
	/**
	 * 一个月的天数
	 */
	private int dayOfMonth;
	
	/**
	 * 当前月份
	 */
	private int monthOfYear;
	
	/**
	 * 当前年
	 */
	private int year;
	
	/**
	 * 当前月份的日期列表
	 */
	private List<DiaryDate> currentMonthDay;
	
	/**
	 * 下月月份的日期列表
	 */
	private List<DiaryDate> nextMonthDay;
	
	/**
	 * 上个月份的日期列表
	 */
	private List<DiaryDate> preMonthDay;
	
	/**
	 *	当月第一天为星期几
	 */
	private int firstDayOfWeek;
	
	/**
	 * 当月最后1天为星期几
	 */
	private int lastDayOfWeek;
	
	/**
	 * 下个月的日期
	 */
	private Date nextMonthDate;
	
	/**
	 * 上个月的日期
	 */
	private Date preMonthDate;
	
	/**
	 * 开始日期
	 */
	private Date startDate;
	
	/**
	 * 结束日期
	 */
	private Date endDate;
	
	@Override
	public String toString() {
		return "DiaryMonthCalendar [currentMonthDay=" + currentMonthDay
				+ ", dayOfMonth=" + dayOfMonth + ", firstDayOfWeek="
				+ firstDayOfWeek + ", lastDayOfWeek=" + lastDayOfWeek
				+ ", monthOfYear=" + monthOfYear + ", nextMonthDate="
				+ nextMonthDate + ", nextMonthDay=" + nextMonthDay
				+ ", preMonthDate=" + preMonthDate + ", preMonthDay="
				+ preMonthDay + ", year=" + year + "]";
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}
	
	public int getMonthOfYear() {
		return monthOfYear;
	}
	
	public int getYear() {
		return year;
	}

	public List<DiaryDate> getCurrentMonthDay() {
		return currentMonthDay;
	}

	public int getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

	public int getLastDayOfWeek() {
		return lastDayOfWeek;
	}

	public Date getNextMonthDate() {
		return nextMonthDate;
	}

	public List<DiaryDate> getNextMonthDay() {
		return nextMonthDay;
	}

	public List<DiaryDate> getPreMonthDay() {
		return preMonthDay;
	}

	public Date getPreMonthDate() {
		return preMonthDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public static void main(String [] args) {
		DiaryMonthCalendar dc = new DiaryMonthCalendar(DateUtil.addMonth(new Date(), 1));
		System.out.println(dc);
	}
	
}
