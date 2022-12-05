package com.i2i.ibus.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.i2i.ibus.dto.AddressDto;
import com.i2i.ibus.dto.BookingDto;
import com.i2i.ibus.dto.OperatorDto;
import com.i2i.ibus.dto.UserDto;
import com.i2i.ibus.model.Address;
import com.i2i.ibus.model.Booking;
import com.i2i.ibus.model.Operator;
import com.i2i.ibus.model.User;

/**
 * @version 1.0
 * 
 * @created Dec 05 2022
 *
 */
public class Mapper {
   
    private static ModelMapper mapper;

    @Autowired
    public Mapper(ModelMapper mapper) {
	Mapper.mapper = mapper;
    }
    
    public static User toUser(UserDto userDto) {
	return mapper.map(userDto, User.class);
    }

    public static UserDto toUserDto(User user) {
	return mapper.map(user, UserDto.class);
    }
    
    public static List<User> toUsers(List<UserDto> userDtos) {
	return userDtos.stream().map(userDto -> toUser(userDto))
		.collect(Collectors.toList());	
    }

    public static List<UserDto> toUserDtos(List<User> users) {
	return users.stream().map(user -> toUserDto(user))
		.collect(Collectors.toList());	
    }
    
    public static Operator toOperator(OperatorDto operatorDto) {
	Operator operator = mapper.map(operatorDto, Operator.class);
	operator.setAddresses(operatorDto.getAddresses().stream()
		.map(addressDto -> mapper.map(addressDto, Address.class)).toList());
	return operator;
    }

    public static OperatorDto toOperatorDto(Operator operator) {
	OperatorDto operatorDto = mapper.map(operator, OperatorDto.class);
	operatorDto.setAddresses(
		operator.getAddresses().stream().map(address -> mapper.map(address, AddressDto.class)).toList());
	return operatorDto;
    }
    
    public static List<Operator> toOperators(List<OperatorDto> operatorDtos) {
	return operatorDtos.stream().map(operatorDto -> toOperator(operatorDto))
		.collect(Collectors.toList());	
    }

    public static List<OperatorDto> toOperatorDtos(List<Operator> operators) {
	return operators.stream().map(operator -> toOperatorDto(operator))
		.collect(Collectors.toList());	
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
}
