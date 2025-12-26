create table airport (
    code char(3) primary key,
    country varchar(64) not null,
    city varchar(64) not null
);

create table plane (
    id bigserial primary key,
    model varchar(256) not null
);

create table seat (
    plane_id bigint references plane(id),

    seat_no varchar(4) not null,
    primary key (plane_id, seat_no)
);

create domain flight_status as varchar(32)
check(value in ('cancelled', 'arrived', 'departed', 'scheduled'));

create table flight(
    id bigserial primary key,
    plane_id bigint references plane(id),
    flight_no int,
    departure_date timestamp,
    departure_airport_code char(3) references airport(code),
    arrival_date timestamp,
    arrival_airport_code char(3) references airport(code),
    status flight_status
);

create table passenger (
    id bigserial primary key,
    first_name varchar(64) not null,
    last_name varchar(64) not null,
    passport_no varchar(20) unique not null
);

create table booking(
    id bigserial primary key,
    customer_id bigint not null references passenger(id),
    payment_date timestamp not null
);

create table ticket (
    id bigserial primary key,
    passenger_id bigint not null references passenger(id),
    flight_id bigint not null references flight(id),
    booking_id bigint not null references booking(id),
    seat_no varchar(4) references seat(seat_no),
    plane_id bigint,
    price decimal(10, 2) not null,
    unique (flight_id, seat_no)
);
