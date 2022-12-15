/*
 * Copyright (c) 2022, Ideas2It and/or its affiliates. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.i2i.ibus.mapper;

import com.i2i.ibus.dto.AddressDto;
import com.i2i.ibus.dto.BookingDetailDto;
import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.dto.CancellationDto;
import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.dto.ScheduleDto;
import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.dto.StopDto;
import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.model.Account;
import com.i2i.ibus.model.Address;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.BookingDetail;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.Cancellation;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.model.Payment;
import com.i2i.ibus.model.Schedule;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.model.Stop;
import com.i2i.ibus.model.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Mapper class contains methods that convert objects from DTO to Entity or vice-versa
 *
 * @author Ragul
 * @version 1.0
 * @since Dec 05 2022
 */
public class Mapper {

    private static ModelMapper mapper = new ModelMapper();

    /**
     * Convert a UserDto object to a User object.
     *
     * @param userDto The source object to map from.
     * @return A User object
     */
    public static User toUser(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    /**
     * Convert a User object to a UserDto object.
     *
     * @param user The user object to be converted to a UserDto object.
     * @return A UserDto object
     */
    public static UserDto toUserDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    /**
     * It takes a list of UserDTOs and returns a list of Users
     *
     * @param userDTOs The list of UserDto objects that we want to convert to User objects.
     * @return A list of users
     */
    public static List<User> toUsers(List<UserDto> userDTOs) {
        return userDTOs.stream().map(userDto -> toUser(userDto)).collect(Collectors.toList());
    }

    /**
     * Convert a list of users to a list of user DTOs.
     *
     * @param users The list of users to convert
     * @return A list of UserDto objects
     */
    public static List<UserDto> toUserDTOs(List<User> users) {
        return users.stream().map(user -> toUserDto(user)).collect(Collectors.toList());
    }

    /**
     * Convert an OperatorDto to an Operator
     *
     * @param operatorDto The OperatorDto object that we want to convert to an Operator object.
     * @return A list of addresses
     */
    public static Operator toOperator(OperatorDto operatorDto) {
        Operator operator = mapper.map(operatorDto, Operator.class);
        operator.setAddresses(
                operatorDto.getAddresses().stream().map(addressDto -> mapper.map(addressDto, Address.class)).toList());
        return operator;
    }

    /**
     * Convert an Operator object to an OperatorDto object by mapping the fields and converting the list of addresses to a
     * list of AddressDto objects.
     *
     * @param operator The source object to be mapped.
     * @return A list of OperatorDto objects
     */
    public static OperatorDto toOperatorDto(Operator operator) {
        OperatorDto operatorDto = mapper.map(operator, OperatorDto.class);
        operatorDto.setAddresses(
                operator.getAddresses().stream().map(address -> mapper.map(address, AddressDto.class)).toList());
        return operatorDto;
    }

    /**
     * Convert a list of OperatorDto objects to a list of Operator objects.
     *
     * @param operatorDtos The list of OperatorDto objects to be converted to Operator objects.
     * @return A list of operators
     */
    public static List<Operator> toOperators(List<OperatorDto> operatorDtos) {
        return operatorDtos.stream().map(operatorDto -> toOperator(operatorDto)).toList();
    }

    /**
     * Convert a list of operators to a list of operator DTOs.
     *
     * @param operators The list of operators to convert to DTOs.
     * @return A list of OperatorDto objects.
     */
    public static List<OperatorDto> toOperatorDTOs(List<Operator> operators) {
        return operators.stream().map(operator -> toOperatorDto(operator)).toList();
    }

    /**
     * Convert a BookingDto to a Booking
     *
     * @param bookingDto The BookingDto object to be converted to a Booking object.
     * @return A Booking object
     */
    public static Booking toBooking(BookingDto bookingDto) {
        return mapper.map(bookingDto, Booking.class);
    }

    /**
     * Convert a Booking object to a BookingDto object.
     *
     * @param booking The Booking object to be converted to a BookingDto object.
     * @return A BookingDto object
     */
    public static BookingDto toBookingDto(Booking booking) {
        return mapper.map(booking, BookingDto.class);
    }

    /**
     * Convert a list of BookingDto objects to a list of Booking objects.
     *
     * @param bookingDtos The list of BookingDto objects that we want to convert to Booking objects.
     * @return A list of Booking objects.
     */
    public static List<Booking> toBooking(List<BookingDto> bookingDtos) {
        return bookingDtos.stream().map(bookingDto -> toBooking(bookingDto)).toList();
    }

    /**
     * Convert a list of Booking objects to a list of BookingDto objects.
     *
     * @param bookings The list of bookings to convert
     * @return A list of BookingDto objects.
     */
    public static List<BookingDto> toBookingDTOs(List<Booking> bookings) {
        return bookings.stream().map(booking -> toBookingDto(booking)).toList();
    }

    /**
     * Convert a Bus object to a BusDto object.
     *
     * @param bus The source object to be mapped.
     * @return A BusDto object
     */
    public static BusDto toBusDto(Bus bus) {
        return mapper.map(bus, BusDto.class);
    }

    /**
     * Convert a BusDto object to a Bus object.
     *
     * @param busDto The source object to map from.
     * @return A Bus object
     */
    public static Bus toBus(BusDto busDto) {
        return mapper.map(busDto, Bus.class);
    }

    /**
     * Convert a BookingDetail object to a BookingDetailDto object.
     *
     * @param bookingDetail The BookingDetail object to be converted to a BookingDetailDto object.
     * @return A BookingDetailDto object
     */
    public static BookingDetailDto toBookingDetailDto(BookingDetail bookingDetail) {
        return mapper.map(bookingDetail, BookingDetailDto.class);
    }

    /**
     * It takes a BookingDetailDto object and maps it to a BookingDetail object
     *
     * @param bookingDetailDto The BookingDetailDto object to be converted to BookingDetail.
     * @return A BookingDetail object
     */
    public static BookingDetail toBookingDetail(BookingDetailDto bookingDetailDto) {
        return mapper.map(bookingDetailDto, BookingDetail.class);
    }

    /**
     * It takes a list of BookingDetail objects and returns a list of BookingDetailDto objects
     *
     * @param bookingDetails The list of BookingDetail objects that we want to convert to BookingDetailDto objects.
     * @return A list of BookingDetailDto objects.
     */
    public static List<BookingDetailDto> toBookingDetailDtos(List<BookingDetail> bookingDetails) {
        return bookingDetails.stream().map(bookingDetail -> toBookingDetailDto(bookingDetail)).toList();
    }

    /**
     * It takes a list of BookingDetailDto objects and returns a list of BookingDetail objects
     *
     * @param bookingDetailDtos The list of BookingDetailDto objects that we want to convert to BookingDetail objects.
     * @return A list of BookingDetail objects.
     */
    public static List<BookingDetail> toBookingDetails(List<BookingDetailDto> bookingDetailDtos) {
        return bookingDetailDtos.stream().map(bookingDetailDto -> toBookingDetail(bookingDetailDto)).toList();
    }

    /**
     * Convert a Payment object to a PaymentDto object.
     *
     * @param payment The Payment object to be converted to a PaymentDto object.
     * @return A PaymentDto object
     */
    public static PaymentDto toPaymentDto(Payment payment) {
        return mapper.map(payment, PaymentDto.class);
    }

    /**
     * Convert a PaymentDto to a Payment
     *
     * @param paymentDto The source object to map from.
     * @return A Payment object
     */
    public static Payment toPayment(PaymentDto paymentDto) {
        return mapper.map(paymentDto, Payment.class);
    }

    /**
     * Convert a list of payments to a list of payment DTOs.
     *
     * @param payments The list of payments to be converted to paymentDtos.
     * @return A list of PaymentDto objects.
     */
    public static List<PaymentDto> toPaymentDtos(List<Payment> payments) {
        return payments.stream().map(payment -> toPaymentDto(payment)).toList();
    }

    /**
     * Convert a SeatDto to a Seat.
     *
     * @param seatDto The SeatDto object to be converted to a Seat object.
     * @return A Seat object
     */
    public static Seat toSeat(SeatDto seatDto) {
        return mapper.map(seatDto, Seat.class);
    }

    /**
     * Convert a Seat object to a SeatDto object.
     *
     * @param seat The seat object to be converted to a SeatDto object.
     * @return A SeatDto object
     */
    public static SeatDto toSeatDto(Seat seat) {
        return mapper.map(seat, SeatDto.class);
    }

    /**
     * Convert a ScheduleDto to a Schedule
     *
     * @param scheduleDto The DTO object that we want to convert to a Schedule object.
     * @return A Schedule object
     */
    public static Schedule toSchedule(ScheduleDto scheduleDto) {
        return mapper.map(scheduleDto, Schedule.class);
    }

    /**
     * Convert a Schedule object to a ScheduleDto object.
     *
     * @param schedule The Schedule object to be converted to a ScheduleDto object.
     * @return A ScheduleDto object
     */
    public static ScheduleDto toScheduleDto(Schedule schedule) {
        return mapper.map(schedule, ScheduleDto.class);
    }

    /**
     * Convert a StopDto to a Stop.
     *
     * @param stopDto The StopDto object to be converted to a Stop object.
     * @return A Stop object
     */
    public static Stop toStop(StopDto stopDto) {
        return mapper.map(stopDto, Stop.class);
    }

    /**
     * Convert a Stop object to a StopDto object.
     *
     * @param stop The Stop object to be converted to a StopDto object.
     * @return A StopDto object
     */
    public static StopDto toStopDto(Stop stop) {
        return mapper.map(stop, StopDto.class);
    }

    /**
     * It takes a Cancellation object and returns a CancellationDto object
     *
     * @param cancellation The Cancellation object that we want to convert to a CancellationDto object.
     * @return A CancellationDto object
     */
    public static CancellationDto toCancellationDto(Cancellation cancellation) {
        return mapper.map(cancellation, CancellationDto.class);
    }

}
