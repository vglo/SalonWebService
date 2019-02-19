package com.habbib.billing.response;


public class DefaultMessage<T> {

		private String responseCode;
	    // Response message of the service.
		private String responseMessage;
	    // Object template of the response.
		private T response;

		public String getResponseCode() {
			return this.responseCode;
		}

		public void setResponseCode(String responseCode) {
			this.responseCode = responseCode;
		}

		public String getResponseMessage() {
			return this.responseMessage;
		}

		public void setResponseMessage(String responseMessage) {
			this.responseMessage = responseMessage;
		}

		public T getResponse() {
			return this.response;
		}

		public void setResponse(T response) {
			this.response = response;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (this.responseCode == null ? 0 : this.responseCode.hashCode());
			result = prime * result + (this.responseMessage == null ? 0 : this.responseMessage.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (this.getClass() != obj.getClass())
				return false;
			DefaultMessage other = (DefaultMessage) obj;
			if (this.responseCode == null) {
				if (other.responseCode != null)
					return false;
			} else if (!this.responseCode.equals(other.responseCode))
				return false;
			return true;
		}

}
