package com.mhatre.sagar.creational;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class ComplexNumber{
	private int i,j;
	
	public ComplexNumber(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	@Override
	public String toString() {
		return "ComplexNumber [i=" + i + ", j=" + j + "]";
	}	
	
	
}

abstract class Event  implements Cloneable{
	private final String originatorServerIP;	
	private Date occuranceTimeStamp;
	private ComplexNumber complexNumber = new ComplexNumber(10,20);
	
	public enum TYPE{INFO, ERROR}
	
	public Event(String originatorServerIP) {
		super();
		this.originatorServerIP = originatorServerIP;
	}

	public Date getOccuranceTimeStamp() {
		return occuranceTimeStamp;
	}

	public void setOccuranceTimeStamp(Date occuranceTimeStamp) { 		
		this.occuranceTimeStamp = occuranceTimeStamp;
	}

	public String getOriginatorServerIP() {
		return originatorServerIP;
	}

	public void setComplexNumber(ComplexNumber complexNumber) {
		this.complexNumber = complexNumber;
	}

	@Override
	public String toString() {
		return "Event [originatorServerIP=" + originatorServerIP + ", occuranceTimeStamp=" + occuranceTimeStamp
				+ ", complexNumber=" + complexNumber + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}	
}

class InformativeEvent extends Event{
	private String messageToDisplay;
	public InformativeEvent(String originatorServerIP) {
		super(originatorServerIP);
	}
	public String getMessageToDisplay() {
		return messageToDisplay;
	}
	public void setMessageToDisplay(String messageToDisplay) {
		this.messageToDisplay = messageToDisplay;
	}
	@Override
	public String toString() {
		return super.toString() + "InformativeEvent [messageToDisplay=" + messageToDisplay + "]";
	}	
	
}

class ErrorEvent extends Event{

	private Exception exception;
	
	public ErrorEvent(String originatorServerIP) {
		super(originatorServerIP);
	}
	
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}

	@Override
	public String toString() {		
		return super.toString() + "ErrorEvent [exception=" + exception + "]";
	}	
	
}

class EventRegistry {
	// Define a registry with skeletons of all the possible basic objects 
	private Map<Event.TYPE, Event> registryMap;
	
	public EventRegistry(String sourceServerIP){
		if(registryMap == null){
			registryMap = new HashMap<Event.TYPE, Event> ();
			loadSkeletonEvents(sourceServerIP);
		}	
	}
	// Load the registry with skeletons of all the possible basic objects
	private void loadSkeletonEvents(String sourceServerIP) {
		registryMap.put(Event.TYPE.INFO, new InformativeEvent(sourceServerIP));
		registryMap.put(Event.TYPE.ERROR, new ErrorEvent(sourceServerIP));	
	}
	
	// Expose a method to return a cloned instance of the skeleton object
	public Event getEventPrototype(Event.TYPE eventType){
		Event event = null;
				try {
					event = (Event) registryMap.get(eventType).clone();
					//Typecasting is mandatory since the clone method returns Object
					
					event.setOccuranceTimeStamp(new Date());
					// Setting the occurance timestamp of the object after creating
					// a clone so that only the occurance timestamp of copy is set		
				} catch (CloneNotSupportedException e) {					
					e.printStackTrace();
				}		
		return event; 
	}	

}

public class C_PrototypeExample {
	public static void main(String[] args) throws InterruptedException {
		EventRegistry eventRegistry = new EventRegistry("192.168.0.11");
		
		ErrorEvent errorEvent = (ErrorEvent) eventRegistry.getEventPrototype(Event.TYPE.ERROR);
		errorEvent.setException(new NullPointerException());
		System.out.println(errorEvent.toString());
		Thread.sleep(1000);
		
		InformativeEvent informativeEvent = (InformativeEvent) eventRegistry.getEventPrototype(Event.TYPE.INFO);
		informativeEvent.setMessageToDisplay("This is an info message");		
		System.out.println(informativeEvent.toString());
		Thread.sleep(1000);
		
		ErrorEvent errorEvent2 = (ErrorEvent) eventRegistry.getEventPrototype(Event.TYPE.ERROR);
		errorEvent2.setException(new ArrayIndexOutOfBoundsException());
		System.out.println(errorEvent2.toString());
		
		errorEvent.setComplexNumber(new ComplexNumber(100,200));
		informativeEvent.setComplexNumber(new ComplexNumber(300,400));
		errorEvent2.setComplexNumber(new ComplexNumber(500,600));
		
		System.out.println(errorEvent.toString());
		System.out.println(informativeEvent.toString());
		System.out.println(errorEvent2.toString());		
	}
}
