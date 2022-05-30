package com.kodgemisi.usermanagement;

/**
 * Represents a phone number, never stores a null value. If constructed with a
 * null value stores an empty string.
 */
public class Phone implements CharSequence {

	private String phoneNumber;
	public final boolean verified;

	public Phone(String phoneNumber) {

		this.phoneNumber = phoneNumber;

		validate(phoneNumber);

		this.verified = false;
	}

	public Phone(String phoneNumber, boolean verified) {

		validate(phoneNumber);

		this.verified = verified;
	}

	// PHONE NUMBER VALIDATION
	
	public boolean validate(String phoneNumber) {
		final String areaCodePlusSign = "+";

		if (phoneNumber.equals("112") || phoneNumber.equals("911")) {
			return true;
		}
		else if (phoneNumber==null ) {
			throw new IllegalArgumentException("A phone number cannot be null");
		}
		else if ( phoneNumber.isBlank() ) {
			throw new IllegalArgumentException("Phone Number cannot be blank");
		}
		else if (!String.valueOf(phoneNumber).startsWith(areaCodePlusSign)) {
			throw new IllegalArgumentException(
					"Invalid Phone Number, There Are No Plus Sign: '%s'!".formatted(phoneNumber));
		}
		else if (String.valueOf(phoneNumber).startsWith(areaCodePlusSign) && phoneNumber.length() < 5) {
			throw new IllegalArgumentException(
					"Invalid Phone Number, If you are starting with Plus Sign, Your number must contains least 5 char long: '%s'!"
							.formatted(phoneNumber));
		} else if (String.valueOf(phoneNumber).startsWith("+90") && phoneNumber.length() != 13) {
			throw new IllegalArgumentException(
					"Invalid Phone Number If you are starting with +90 prefix, Your number must contains at least 13 char long: '%s'!"
							.formatted(phoneNumber));
		}
		return true;
	}

	public String number() {
		return phoneNumber;
	}

	@Override
	public int length() {
		return phoneNumber.length();
	}

	@Override
	public char charAt(int index) {
		return phoneNumber.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return phoneNumber.subSequence(start, end);
	}

	@Override
	public String toString() {
		return phoneNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Phone phone = (Phone) o;

		return phoneNumber.equals(phone.phoneNumber);
	}

	@Override
	public int hashCode() {
		return phoneNumber.hashCode();
	}
}
