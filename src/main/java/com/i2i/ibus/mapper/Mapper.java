package com.i2i.ibus.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.i2i.ibus.dto.AddressDto;
import com.i2i.ibus.dto.BookingDetailDto;
import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.BusDto;
import com.i2i.ibus.dto.BusHistoryDto;
import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.dto.PaymentDto;
import com.i2i.ibus.dto.PickupPointDto;
import com.i2i.ibus.dto.SeatDto;
import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.model.Address;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.BookingDetail;
import com.i2i.ibus.model.Bus;
import com.i2i.ibus.model.BusHistory;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.model.Payment;
import com.i2i.ibus.model.PickupPoint;
import com.i2i.ibus.model.Seat;
import com.i2i.ibus.model.User;

/**
 * @version 1.0
 * 
 * @created Dec 05 2022
 *
 */
public class Mapper {

    private static ModelMapper mapper = new ModelMapper();

    public static User toUser(UserDto userDto) {
	return mapper.map(userDto, User.class);
    }

    public static UserDto toUserDto(User user) {
	return mapper.map(user, UserDto.class);
    }

    public static List<User> toUsers(List<UserDto> userDtos) {
	return userDtos.stream().map(userDto -> toUser(userDto)).collect(Collectors.toList());
    }

    public static List<UserDto> toUserDtos(List<User> users) {
	return users.stream().map(user -> toUserDto(user)).collect(Collectors.toList());
    }

    public static Operator toOperator(OperatorDto operatorDto) {
	Operator operator = mapper.map(operatorDto, Operator.class);
	operator.setAddresses(
		operatorDto.getAddresses().stream().map(addressDto -> mapper.map(addressDto, Address.class)).toList());
	return operator;
    }

    public static OperatorDto toOperatorDto(Operator operator) {
	OperatorDto operatorDto = mapper.map(operator, OperatorDto.class);
	operatorDto.setAddresses(
		operator.getAddresses().stream().map(address -> mapper.map(address, AddressDto.class)).toList());
	return operatorDto;
    }

    public static List<Operator> toOperators(List<OperatorDto> operatorDtos) {
	return operatorDtos.stream().map(operatorDto -> toOperator(operatorDto)).toList();
    }

    public static List<OperatorDto> toOperatorDtos(List<Operator> operators) {
	return operators.stream().map(operator -> toOperatorDto(operator)).toList();
    }

    public static Booking toBooking(BookingDto bookingDto) {
	return mapper.map(bookingDto, Booking.class);
    }

    public static BookingDto toBookingDto(Booking booking) {
	return mapper.map(booking, BookingDto.class);
    }

    public static List<Booking> toBooking(List<BookingDto> bookingDtos) {
	return bookingDtos.stream().map(bookingDto -> toBooking(bookingDto)).toList();
    }

    public static List<BookingDto> toBookingDtos(List<Booking> bookings) {
	return bookings.stream().map(booking -> toBookingDto(booking)).toList();
    }

    public static BusDto toBusDto(Bus bus) {
	return mapper.map(bus, BusDto.class);
    }

    public static Bus toBus(BusDto busDto) {
	return mapper.map(busDto, Bus.class);
    }

    public static BookingDetailDto toBookingDetailDto(BookingDetail bookingDetail) {
	return mapper.map(bookingDetail, BookingDetailDto.class);
    }

    public static BookingDetail toBookingDetail(BookingDetailDto bookingDetailDto) {
	return mapper.map(bookingDetailDto, BookingDetail.class);
    }

    public static List<BookingDetailDto> toBookingDetailDtos(List<BookingDetail> bookingDetails) {
	return bookingDetails.stream().map(bookingDetail -> toBookingDetailDto(bookingDetail)).toList();
    }

    public static List<BookingDetail> toBookingDetails(List<BookingDetailDto> bookingDetailDtos) {
	return bookingDetailDtos.stream().map(bookingDetailDto -> toBookingDetail(bookingDetailDto)).toList();
    }

    public static PaymentDto toPaymentDto(Payment payment) {
	return mapper.map(payment, PaymentDto.class);
    }
    
    public static Payment toPayment(PaymentDto paymentDto) {
	return mapper.map(paymentDto, Payment.class);
    }
    
    public static List<PaymentDto> toPaymentDtos(List<Payment> payments) {
	return payments.stream().map(payment -> toPaymentDto(payment)).toList();
    }
    
    public static Seat toSeat(SeatDto seatDto) {
	return mapper.map(seatDto, Seat.class);
    }

    public static SeatDto toSeatDto(Seat seat) {
	return mapper.map(seat, SeatDto.class);
    }
    
    public static BusHistory toBusHistory(BusHistoryDto busHistoryDto) {
	return mapper.map(busHistoryDto, BusHistory.class);
    }
    
    public static BusHistoryDto toBusHistoryDto(BusHistory busHistory) {
	return mapper.map(busHistory, BusHistoryDto.class);
    }
    
    public static PickupPoint toPickupPoint(PickupPointDto pickupPointDto) {
	return mapper.map(pickupPointDto, PickupPoint.class);
    }
    
    public static PickupPointDto toPickupPointDto(PickupPoint pickupPoint) {
	return mapper.map(pickupPoint, PickupPointDto.class);
    }
}
