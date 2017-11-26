package com.saytopay.model;

public class PrivacyPolicy {
	
	private boolean askPaymentPIN = false;
	private boolean showAccountBalance = true;
	private boolean shouldAllowTransfers = true;
	

	public boolean isShowAccountBalance() {
		return showAccountBalance;
	}
	public void setShowAccountBalance(boolean showAccountBalance) {
		this.showAccountBalance = showAccountBalance;
	}
	public boolean isShouldAllowTransfers() {
		return shouldAllowTransfers;
	}
	public void setShouldAllowTransfers(boolean shouldAllowTransfers) {
		this.shouldAllowTransfers = shouldAllowTransfers;
	}
	public boolean isAskPaymentPIN() {
		return askPaymentPIN;
	}
	public void setAskPaymentPIN(boolean askPaymentPIN) {
		this.askPaymentPIN = askPaymentPIN;
	}
	
}
