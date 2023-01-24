/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.constants;

/**
 * It contains exception messages, regex patterns, http code messages, invalid
 * messages, etc.
 * 
 * @version 1.0
 * @since Nov 29 2022
 *
 */
public class Constants {

    // Http status code messages.
    public static final String BAD_REQUEST = "400";
    public static final String EVERYTHING_IS_OK = "200";

    // Create message.
    public static final String CREATE_MESSAGE = "Created successfully.";

    // Update message.
    public static final String UPDATE_MESSAGE = "Updated successfully";

    // Delete messages.
    public static final String DELETE_MESSAGE = "Deleted successfully.";

    // Invalid pattern messages.
    public static final String INVALID_BUS_NUMBER_PATTERN_MESSAGE = "Given bus number is not valid (eg: AB12CD3456)";
    public static final String INVALID_BUS_TYPE_PATTERN_MESSAGE = "Given bus type is not valid (eg: AC or NONAC)";
    public static final String INVALID_CARD_NUMBER_PATTERN_MESSAGE = "Card number must contain 16 character. (eg: 0123456789012345)";
    public static final String INVALID_CITY_PATTERN_MESSAGE = "City should be in valid format.";
    public static final String INVALID_DESTINATION_PATTERN_MESSAGE = "Given destination is not valid (eg: abcde)";
    public static final String INVALID_DISTRICT_PATTERN_MESSAGE = "District should be in valid format.";
    public static final String INVALID_DROP_POINT_MESSAGE = "Invalid Drop off point in ";
    public static final String INVALID_EMAIL_ID_PATTERN_MESSAGE = "Email id should be in valid format.";
    public static final String INVALID_GENDER_PATTERN_MESSAGE = "Please enter the valid format gender.";
    public static final String INVALID_GST_NUMBER_PATTERN_MESSAGE = "GST number should be in valid format...(12ABCDE1234A1Z1)";
    public static final String INVALID_LANDMARK_PATTERN_MESSAGE = "Landmark should be in valid format.";
    public static final String INVALID_NAME_PATTERN_MESSAGE = "Please enter the valid name format.";
    public static final String INVALID_PAN_NUMBER_PATTERN_MESSAGE = "PAN number should be in valid format...(ABCDE1234A)";
    public static final String INVALID_PASSWORD_PATTERN_MESSAGE = "A lower case alphabet must occur at least once."
            +("An upper case alphabet that must occur at least once.")
            +("A special character that must occur at least once.")
            +("White spaces don’t allowed in the entire string.")
            +("At least 8 characters and At most 20 characters.");
    public static final String INVALID_PAYMENT_TYPE_MESSAGE = "only credit, debit, upi payments are allowed.";
    public static final String INVALID_PHONE_NUMBER_PATTERN_MESSAGE = "Phone no should be 10 digits or with \"+91\" or \"0\"";
    public static final String INVALID_PICKUP_POINT_MESSAGE = "Invalid Pick up point in ";
    public static final String INVALID_ROLE_NAME_PATTERN_MESSAGE = "Role should be user or operator.";
    public static final String INVALID_SEAT_GENDER_PATTERN_MESSAGE = "Given seat gender is not valid. (eg: male,female or other)";
    public static final String INVALID_SEAT_NUMBER_PATTERN_MESSAGE = "Given seat number is not valid. (eg: LLS1,LUS1,RLS1,RUS2)";
    public static final String INVALID_SEAT_TYPE_PATTERN_MESSAGE = "Given seat type is not valid. (eg: seater or sleeper)";
    public static final String INVALID_STOP_PATTERN_MESSAGE = "Given stop is not valid. (eg: abcde)";
    public static final String INVALID_SOURCE_PATTERN_MESSAGE = "Given source is not valid. (eg: abcd)";

    public static final String INVALID_STATE_PATTERN_MESSAGE = "State should be in valid format.";
    public static final String INVALID_STREET_PATTERN_MESSAGE = "Street should be in valid format.";
    public static final String INVALID_TRAVELS_NAME_PATTERN_MESSAGE = "Travel's name should be in valid format...(a-zA-Z)";
    public static final String INVALID_OWNERS_NAME_PATTERN_MESSAGE = "Owner's name should be in valid format...(a-zA-Z)";

    // Mandatory message for empty inputs.
    public static final String CARD_NUMBER_MANDATORY_MESSAGE = "Card number name is mandatory.";
    public static final String CITY_MANDATORY_MESSAGE = "City name is mandatory.";
    public static final String DISTRICT_MANDATORY_MESSAGE = "District name is mandatory.";
    public static final String GENDER_MANDATORY_MESSAGE = "Gender is mandatory.";
    public static final String GST_NUMBER_MANDATORY_MESSAGE = "GST number is mandatory.";
    public static final String LANDMARK_MANDATORY_MESSAGE = "Landmark is mandatory.";
    public static final String MAIL_ID_MANDATORY_MESSAGE = "Mail id is mandatory.";
    public static final String NAME_MANDATORY_MESSAGE = "Name is mandatory.";
    public static final String PAN_NUMBER_MANDATORY_MESSAGE = "PAN number is mandatory.";
    public static final String PAYMENT_TYPE_MANDATORY_MESSAGE = "Payment type is mandatory.";
    public static final String PHONE_NUMBER_MANDATORY_MESSAGE = "Phone number is mandatory.";
    public static final String STATE_NAME_MANDATORY_MESSAGE = "State name is mandatory.";
    public static final String STREET_NAME_MANDATORY_MESSAGE = "Street name is mandatory.";
    public static final String TRAVELS_NAME_MANDATORY_MESSAGE = "Travel's name is mandatory.";
    public static final String USER_NAME_MANDATORY_MESSAGE = "User name is mandatory.";

    // Message for wrong inputs.
    public static final String INVALID_BUS_DEPARTURE_DATE = "This bus is not departure on this date.";
    public static final String INVALID_SEAT_INPUT_MESSAGE = "This seat is not for ";

    // Regex pattern messages.
    public static final String BUS_NUMBER_PATTERN = "(^[A-Z]{2}(([0][1-9])|([1-9][0-9]))([A-Z]{1})?[A-Z]{1}[0-9]{3}[1-9]{1}$)";
    public static final String BUS_TYPE_PATTERN = "^((?i)(NONAC)|(AC))$";
    public static final String CARD_NUMBER_PATTERN = "^[1-9][0-9]{15}$";
    public static final String GENDER_PATTERN = "(?i)^(male)|(female)|(others)$";
    public static final String GST_NUMBER_PATTERN = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z][0-9](Z)[0-9]$";
    public static final String NAME_PATTERN = "([a-zA-Z][ ]?){2,50}";
    public static final String PAN_NUMBER_PATTERN = "^[A-Z]{5}[0-9]{4}[A-Z]$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
    public static final String PAYMENT_TYPE_PATTERN = "(?i)^(upi)|(debit)|(credit)$";
    public static final String PHONE_NUMBER_PATTERN = "^(\\+91|0)?[6789][0-9]{9}$";
    public static final String PLACE_NAME_PATTERN = "^([a-zA-Z][ ]?){2,}$";
    public static final String ROLE_NAME_PATTERN = "(?i)^(user)|(operator)$";
    public static final String SEAT_NUMBER_PATTERN = "^([L]|[R])([L]|[U])([S]|[B])([0-9]?[1-9])$";
    public static final String SEAT_TYPE_PATTERN = "^((?i)(sleeper)|(semisleeper)|(seater))$";
    public static final String STREET_NAME_PATTERN = "^([a-zA-Z0-9,][ ]?){2,}$";

    // Stable messages.
    public static final String ALREADY_EXIST = "already exists";
    public static final String BOOKING = "booking";
    public static final String NOT_EXIST= "doesn't exists";
    public static final String CANCELLATION = "cancellation";
    public static final String CANCELLED = "cancelled";
    public static final String COMPLETED = "completed";
    public static final String CONFIRMED = "confirmed";
    public static final String DECLINED = "declined";
    public static final String ENDED = "ended";
    public static final String NOT_CONFIRMED = "not confirmed";
    public static final String NOT_PAID = "not paid";
    public static final String PAID = "paid";
    public static final String PAYMENT = "payment";
    public static final String REFUND = "refund";
    public static final String REFUNDED = "refunded";
    public static final String ROLE_OPERATOR = "ROLE_OPERATOR";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String STARTED = "started";
    public static final String SUCCESS = "success";
    public static final String UNPAID = "unpaid";
    public static final String UPCOMING = "upcoming";

    // Not found and not exist messages.
    public static final String BOOKING_NOT_EXIST = "Booking details doesn't exist.";
    public static final String BUS_ID_NOT_EXIST = "Bus details doesn't exist.";
    public static final String OPERATOR_ID_NOT_EXIST = "Operator details doesn't exist.";
    public static final String PAYMENT_NOT_EXIST = "Payment details doesn't exist.";
    public static final String SEAT_NOT_EXIST = "Seat doesn't exist.";
    public static final String SCHEDULE_NOT_EXIST = "Schedule doesn't exist.";
    public static final String STOP_NOT_EXIST = "Stop doesn't exist.";
    public static final String USER_NOT_EXIST = "User details doesn't exist.";

    // Already present and duplicate found messages.
    public static final String DUPLICATE_BUS_NUMBER_FOUND = "Bus number already exists.";
    public static final String GST_NUMBER_ALREADY_EXISTS = "GST number already exist.";
    public static final String MAIL_ID_ALREADY_EXISTS = "Mail id already exist.";
    public static final String MAIL_ID_PHONE_NUMBER_GST_ALREADY_EXIST = "Mail id, phone number and GST number are already exist.";
    public static final String MAIL_ID_PHONE_NUMBER_ALREADY_EXIST = "Mail id and phone number already exist.";
    public static final String PHONE_NUMBER_ALREADY_EXISTS = "Phone number already exist";
    public static final String PICKUP_POINT_ALREADY_EXIST = "Pickup point already exists.";

    // Booking messages.
    public static final String BOOKING_ALREADY_CANCEL_MESSAGE = "Booking is already cancelled.";
    public static final String BOOKING_CANCEL_MESSAGE = "Booking cancelled.";
    public static final String BOOKING_SUCCESSFULLY = "Booked successfully.";

    // Seat messages.
    public static final String SEAT_NOT_AVAILABLE = "Seat is not available";
    public static final String SEAT_ALREADY_BOOKED = "Seat already booked";

    // Payment messages.
    public static final String ALREADY_PAYMENT_SUCCEED_MESSAGE = "Payment already succeeded.";
    public static final String EXPIRED_PAYMENT_TIME_MESSAGE = "Payment time is expired, your booking is cancelled.";
    public static final String INVALID_PAYMENT_MESSAGE = "Payment is cancelled due to invalid amount. The total fare is ";
    public static final String PAID_MESSAGE = "Paid successfully.";
    public static final String PAYMENT_CANCEL_MESSAGE = "Payment is cancelled due to cancellation of booking.";
    public static final String PAYMENT_DETAILS_GET_MESSAGE= "Payment details are getting successfully between the given two dates.";

    // Same data provided messages.
    public static final String SAME_SOURCE_AND_DESTINATION = "Source and destination are same.";
    public static final String SAME_DEPARTURE_AND_ARRIVAL_DATETIME = "Departure and arrival date and time not to be same.";

    //other messages.
    public static final String AMOUNT = " AMOUNT : ";
    public static final String BOOKING_ID = " BOOKING ID : ";
    public static final String BUS_NUMBER = " BUS NUMBER : ";
    public static final String BUS_ID = " BUS ID : ";
    public static final String GST_NUMBER = " GST NUMBER : ";
    public static final String MAIL_ID = " MAIL ID : ";
    public static final String OPERATOR_ID = " OPERATOR ID : ";
    public static final String PAYMENT_ID = " PAYMENT ID : ";
    public static final String PHONE_NUMBER = " PHONE NUMBER : ";
    public static final String REFUND_AMOUNT = " REFUND AMOUNT : ";
    public static final String SCHEDULE_ID = " SCHEDULE ID ID : ";
    public static final String SEAT_ID = " SEAT ID : ";
    public static final String SEAT_NUMBER = " SEAT NUMBER : ";
    public static final String STOP_ID = " STOP ID : ";
    public static final String USER_ID = " USER ID : ";
    public static final String DUPLICATE_SCHEDULE = "Schedule entries are already exist";
}
