package com.i2i.ibus.constants;

public class Constants {

    //Http status code messages.
    public static final String BAD_REQUEST = "400";
    public static final String EVERYTHINK_IS_OK = "200";

    //Delete messages.
    public static final String DELETE_MSG = "Deleted sucessfully";

    //Update messages.
    public static final String UPDATE_MSG = "Updated sucessfully";

    //Invalid pattern messages.
    public static final String INVALID_CITY_PATTERN_MSG = "City should be in valid format";
    public static final String INVALID_DISTRICT_PATTERN_MSG = "District should be in valid format";
    public static final String INVALID_DROP_POINT_MSG = "Invalid Drop off point in ";
    public static final String INVALID_EMAILID_PATTERN_MSG = "Email id should be in valid format";
    public static final String INVALID_GENDER_PATTERN_MSG = "Please enter the valid format gender.";
    public static final String INVALID_GST_NUMBER_PATTERN_MSG = "GST number should be in valid format...(12ABCDE1234A1Z1)";
    public static final String INVALID_LANDMARK_PATTERN_MSG = "Landmark should be in valid format";
    public static final String INVALID_NAME_PATTERN_MSG = "Please enter the valid name format. ";
    public static final String INVALID_PANNUMBER_PATTERN_MSG = "PAN number should be in valid format...(ABCDE1234A)";
    public static final String INVALID_PAYMENT_TYPE_MSG = "only credit, debit, upi payments are allowed.";
    public static final String INVALID_PHONENUMBER_PATTERN_MSG = "Phone no should be 10 digits or with \"+91\" or \"0\"";
    public static final String INVALID_PICKUP_POINT_MSG = "Invalid Pick up point in ";
    public static final String INVALID_STATE_PATTERN_MSG = "State should be in valid format";
    public static final String INVALID_STREET_PATTERN_MSG = "Street should be in valid format";
    public static final String INVALID_TRAVELSNAME_PATTERN_MSG = "Travel's name should be in valid format...(a-zA-Z)";
    public static final String INVALID_USERNAME_PATTERN_MSG = "User name should be in valid format";
    public static final String INVALID_OWNERNAME_PATTERN_MSG = "Owner's name should be in valid format...(a-zA-Z)";

    //Mandatory message for empty inputs.
    public static final String CITY_MANDATORY_MSG = "City name is mandatory";
    public static final String DISTRICT_MANDATORY_MSG = "District name is mandatory";
    public static final String GENDER_MANDATORY_MSG = "Gender is mandatory";
    public static final String GSTNUMBER_MANDATORY_MSG = "GST number is mandatory";
    public static final String LANDMARK_MANDATORY_MSG = "Landmark is mandatory";
    public static final String MAILID_MANDATORY_MSG = "Mail ID is mandatory";
    public static final String PANNUMBER_MANDATORY_MSG = "PAN number is mandatory";
    public static final String PHONENUMBER_MANDATORY_MSG = "Phone number is mandatory";
    public static final String STATENAME_MANDATORY_MSG = "State name is mandatory";
    public static final String STREETNAME_MANDATORY_MSG = "Street name is mandatory";
    public static final String TRAVELSNAME_MANDATORY_MSG = "Travel's name is mandatory";
    public static final String USERNAME_MANDATORY_MSG = "User name is mandatory";
    public static final String OWNERNAME_MANDATORY_MSG = "Owner's name is mandatory";

    //Message for wrong inputs.
    public static final String INVALID_BUSDEPATURE_DATE = "This bus is not departure on this date";
    public static final String INVALID_PAYMENT_MSG = "Payment is cancelled due to invalid amount. The total fare is ";
    public static final String INVALID_SEAT_INPUT_MSG = "This seat is not for ";

    //Regex pattern messages.
    public static final String BUS_NUMBER_PATTERN = "(^[A-Z]{2}(([0][1-9])|([1-9][0-9]))([A-Z]{1})?[A-Z]{1}[0-9]{3}[1-9]{1}$)";
    public static final String BUS_TYPE_PATTERN = "^((?i)(NONAC)|(AC))$";
    public static final String CARD_NUMBER_PATTERN = "^[1-9][0-9]{15}$";
    public static final String GENDER_PATTERN = "(?i)^(male)|(female)|(others)$";
    public static final String GST_NUMBER_PATTERN = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z][0-9](Z)[0-9]$";
    public static final String NAME_PATTERN = "([a-zA-Z][ ]?){2,50}";
    public static final String PANNUMBER_PATTERN = "^[A-Z]{5}[0-9]{4}[A-Z]$";
    public static final String PAYMENT_TYPE_PATTERN = "(?i)^(upi)|(debit)|(credit)$";
    public static final String PHONENUMBER_PATTERN = "^(\\+91|0)?[6789][0-9]{9}$";
    public static final String PLACENAME_PATTERN = "^([a-zA-Z][ ]?){2,}$";
    public static final String SEAT_NUMBER_PATTERN = "^([L]|[R])([L]|[U])([S]|[B])([0-9]?[1-9])$";
    public static final String SEAT_TYPE_PATTERN = "^((?i)(sleeper)|(semisleeper)|(seater))$";
    public static final String STREET_NAME_PATTERN = "^([a-zA-Z0-9,][ ]?){2,}$";

    //Stable messages.
    public static final String ALREADY_EXIST = "already exists";
    public static final String COMPLETED = "completed";
    public static final String DECLINED = "declined";
    public static final String ENDED = "ended";
    public static final String SUCCESS = "success";
    public static final String UNPAID = "unpaid";
    public static final String UPCOMING = "upcoming";

    //Not found and not exist messages.
    public static final String BOOKING_NOT_EXIST = "Booking doesn't exists";
    public static final String BOOKINGID_NOT_EXIST = "Booking id doesn't exists";
    public static final String BUSID_NOT_EXIST = "Bus id doesn't exists";
    public static final String OPERATORID_NOT_EXIST = "Operator id doesn't exist.";
    public static final String PAYMENTID_NOT_EXIST = "Payment id doesn't exist.";
    public static final String SEAT_NOT_EXIST = "Seat doesn't exixts";
    public static final String USERID_NOT_EXIST = "User id doesn't exists";

    //Already present and dublicate found messages.
    public static final String DUBLICATE_BUSNUMBER_FOUND = "Bus number is alreday exist.";
    public static final String MAILID_PHONENUMBER_GST_ALREADY_EXIST = "MailId, Phone no, GST number are already exist";
    public static final String MAILID_PHONENUMBER_ALREADY_EXIST = "Mail Id and Phone No already exists";
    public static final String PICKUP_POINT_ALREADY_EXIST = "Pickuppoint is already exist.";

    //Date and time expired messages.
    public static final String EXPIRED_BOOKING_TIME = "Booking time is expired.";

    //Other messages.
    public static final String ALREADY_PAYMENT_SUCCEED_MSG = "Payment already succeeded";
    public static final String SAME_SOURCE_AND_DESTINATION = "Source and destination are same";
    public static final String SAME_DEPATURE_AND_ARRIVAL_DATETIME = "Departue and arrival date and time not to be same";
}
