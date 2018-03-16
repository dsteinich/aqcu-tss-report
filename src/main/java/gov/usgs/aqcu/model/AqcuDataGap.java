package gov.usgs.aqcu.model;

import java.time.Instant;
import java.time.Duration;
import java.math.BigDecimal;

public class AqcuDataGap {
	private Instant startTime = null;
	private Instant endTime = null;
	private BigDecimal durationInHours = null;
	private AqcuDataGapExtent gapExtent;
		
	public Instant getStartTime() {
		return startTime;
	}
	
	public Instant getEndTime() {
		return endTime;
	}
	
	public AqcuDataGapExtent getGapExtent() {
		return gapExtent;
	}
	
	public BigDecimal getDurationInHours() {
		return durationInHours;
	}
	
	public void setStartTime(Instant val) {
		startTime= val;
		calculateDurationInHours();
	}
	
	public void setEndTime(Instant val) {
		endTime = val;
		calculateDurationInHours();
	}
	
	public void setGapExtent(AqcuDataGapExtent val) {
		gapExtent = val;
	}
	
	public void calculateDurationInHours() {
		if(startTime != null && endTime != null) {
			durationInHours = BigDecimal.valueOf(Duration.between(startTime, endTime).getSeconds() / 3600.0);
		} else {
			durationInHours = null;
		}
	}
}