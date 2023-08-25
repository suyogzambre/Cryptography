package com.crypto.project.model;

public class TextEncDcrtModel {
		private long id;
		private String userId;
		private String text;
		private String key;
		private String senderName;
		private String time;
		private String reciverLocation;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getSenderName() {
			return senderName;
		}
		public void setSenderName(String senderName) {
			this.senderName = senderName;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getReciverLocation() {
			return reciverLocation;
		}
		public void setReciverLocation(String reciverLocation) {
			this.reciverLocation = reciverLocation;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return super.equals(obj);
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString();
		}
		@Override
		protected void finalize() throws Throwable {
			// TODO Auto-generated method stub
			super.finalize();
		}
		
		public TextEncDcrtModel(long id, String userId, String text, String key, String senderName, String time,
				String reciverLocation) {
			super();
			this.id = id;
			this.userId = userId;
			this.text = text;
			this.key = key;
			this.senderName = senderName;
			this.time = time;
			this.reciverLocation = reciverLocation;
		}
		
		
}
