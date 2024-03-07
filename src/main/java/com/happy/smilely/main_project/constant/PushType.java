package com.happy.smilely.main_project.constant;

import lombok.Getter;

public enum PushType {
	
	NOTICE("N", "공지"),
	REPORT("R", "결과");
	
	@Getter
	private String idx;
	
	@Getter
	private String name;
	
	private PushType(String idx, String name) {
		this.idx = idx;
		this.name = name;
	}
	
	public static PushType from(String idx) {
		if (idx == null || idx.isEmpty())
			return null;
		else {
			for (PushType pushType : values())
				if (pushType.getIdx().equals(idx))
					return pushType;
		}
		return null;
	}

}
