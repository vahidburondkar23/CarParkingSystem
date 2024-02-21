public class Car implements Vehicle{
	private String carNumber;
	private String inTime;
	private String outTime;
	private long slotDuration;

	public long getSlotDuration() {
		return slotDuration;
	}

	public void setSlotDuration(long l) {
		this.slotDuration = l;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

}
