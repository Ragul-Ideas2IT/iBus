package com.i2i.ibus.mapper;

import com.i2i.ibus.dto.AccountDto;
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

    public static List<Operator> toOperators(List<OperatorDto> operatorDtos) {
        return operatorDtos.stream().map(operatorDto -> toOperator(operatorDto)).toList();
    }

    public static List<OperatorDto> toOperatorDTOs(List<Operator> operators) {
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

    public static List<BookingDto> toBookingDTOs(List<Booking> bookings) {
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

    public static Schedule toSchedule(ScheduleDto scheduleDto) {
        return mapper.map(scheduleDto, Schedule.class);
    }

    public static ScheduleDto toScheduleDto(Schedule schedule) {
        return mapper.map(schedule, ScheduleDto.class);
    }

    public static Stop toStop(StopDto stopDto) {
        return mapper.map(stopDto, Stop.class);
    }

    public static StopDto toStopDto(Stop stop) {
        return mapper.map(stop, StopDto.class);
    }

    public static CancellationDto toCancellationDto(Cancellation cancellation) {
        return mapper.map(cancellation, CancellationDto.class);
    }

    public static Account toAccount(AccountDto accountDto) {
        return mapper.map(accountDto, Account.class);
    }

    public static AccountDto toAccountDto(Account account) {
        return mapper.map(account, AccountDto.class);
    }
}
