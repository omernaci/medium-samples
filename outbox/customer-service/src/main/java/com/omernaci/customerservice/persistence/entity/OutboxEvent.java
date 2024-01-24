package com.omernaci.customerservice.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table
@Entity
public class OutboxEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String eventType;

	private String eventPayload;

	@Enumerated(EnumType.STRING)
	private EventStatus eventStatus;

	private LocalDateTime createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventPayload() {
		return eventPayload;
	}

	public void setEventPayload(String eventPayload) {
		this.eventPayload = eventPayload;
	}

	public EventStatus getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(EventStatus eventStatus) {
		this.eventStatus = eventStatus;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
}
