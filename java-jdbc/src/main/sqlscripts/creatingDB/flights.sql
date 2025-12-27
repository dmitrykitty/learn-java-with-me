create table if not exists airport
(
    code    char(3) primary key,
    country varchar(64) not null,
    city    varchar(64) not null
);

create table plane
(
    id    bigserial primary key,
    model varchar(256) not null
);

create table seat
(
    plane_id bigint references plane (id),

    seat_no  varchar(4) not null,
    primary key (plane_id, seat_no)
);

create domain flight_status as varchar(32)
    check (value in ('cancelled', 'arrived', 'departed', 'scheduled'));

create table flight
(
    id                     bigserial,
    plane_id               bigint references plane (id),

    flight_no              int       not null,
    departure_date         timestamp not null,
    departure_airport_code char(3)   not null references airport (code),
    arrival_date           timestamp not null,
    arrival_airport_code   char(3)   not null references airport (code),
    status                 flight_status,

    primary key (id, plane_id),
    check (departure_date < arrival_date)
);

create table passenger
(
    id          bigserial primary key,
    first_name  varchar(64)        not null,
    last_name   varchar(64)        not null,
    passport_no varchar(20) unique not null
);

create table booking
(
    id           bigserial primary key,
    customer_id  bigint    not null references passenger (id),
    payment_date timestamp not null
);

create table customer
(
    id       bigserial primary key,
    email    varchar(255) unique not null,
    phone_no varchar(20)
);

create table ticket
(
    id           bigserial primary key,
    passenger_id bigint         not null references customer (id),
    booking_id   bigint         not null references booking (id),

    flight_id    bigint         not null,
    plane_id     bigint         not null,

    seat_no      varchar(4)     not null,
    price        decimal(10, 2) not null,

    foreign key (flight_id, plane_id) references flight (id, plane_id),
    foreign key (plane_id, seat_no) references seat (plane_id, seat_no),
    unique (flight_id, seat_no)
);

alter table ticket
    drop constraint ticket_passenger_id_fkey;

alter table ticket
    add constraint fk_ticket_passenger
        foreign key (passenger_id) references passenger (id);

alter table booking
    drop constraint booking_customer_id_fkey;

alter table booking
    add constraint fk_booking_customer
        foreign key (customer_id) references customer (id);


INSERT INTO airport (code, country, city)
VALUES ('WAW', 'Polska', 'Warszawa'),
       ('JFK', 'USA', 'Nowy Jork'),
       ('LHR', 'Wielka Brytania', 'Londyn'),
       ('FRA', 'Niemcy', 'Frankfurt');

INSERT INTO plane (model)
VALUES ('Boeing 737-800'),
       ('Airbus A320'),
       ('Boeing 787 Dreamliner');



INSERT INTO seat (plane_id, seat_no)
VALUES (1, '1A'),
       (1, '1B'),
       (1, '2A'),
       (1, '2B'), -- Boeing 737
       (2, '10A'),
       (2, '10B'),
       (2, '11A'),
       (2, '11B'); -- Airbus A320

INSERT INTO flight (plane_id, flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code,
                    status)
VALUES (1, 101, '2025-05-10 08:00:00', 'WAW', '2025-05-10 11:30:00', 'LHR', 'scheduled'),
       (2, 202, '2025-05-12 14:00:00', 'FRA', '2025-05-13 02:00:00', 'JFK', 'scheduled');

INSERT INTO passenger (first_name, last_name, passport_no)
VALUES ('Jan', 'Kowalski', 'ABC123456'),
       ('Anna', 'Nowak', 'DEF789012'),
       ('John', 'Smith', 'GHI345678');

INSERT INTO customer (email, phone_no)
VALUES ('jan.kowalski@email.com', '+48 123 456 789'),
       ('anna.nowak@email.com', '+48 987 654 321');

-- Rezerwacja 1: Jan kupuje 2 bilety
INSERT INTO booking (customer_id, payment_date)
VALUES (1, '2025-04-01 10:00:00');

-- Rezerwacja 2: Anna kupuje 1 bilet
INSERT INTO booking (customer_id, payment_date)
VALUES (2, '2025-04-05 15:30:00');

-- Bilety do rezerwacji nr 1 (Lot 1, Boeing 737)
INSERT INTO ticket (passenger_id, booking_id, flight_id, plane_id, seat_no, price)
VALUES (1, 1, 1, 1, '1A', 450.00), -- Jan leci na 1A
       (2, 1, 1, 1, '1B', 450.00);
-- Anna leci na 1B (Jan płacił)

-- Bilet do rezerwacji nr 2 (Lot 2, Airbus A320)
INSERT INTO ticket (passenger_id, booking_id, flight_id, plane_id, seat_no, price)
VALUES (3, 2, 2, 2, '10A', 1200.00); -- John leci na 10A (Anna płaciła)

INSERT INTO airport (code, country, city)
VALUES ('CDG', 'Francja', 'Paryż'),
       ('DXB', 'Zjednoczone Emiraty Arabskie', 'Dubaj'),
       ('NRT', 'Japonia', 'Tokio'),
       ('BCN', 'Hiszpania', 'Barcelona');

INSERT INTO plane (model)
VALUES ('Embraer 190'),
       ('Boeing 777-300ER');
-- To będzie nasz samolot o ID 5


-- Miejsca dla Boeing 787 (ID 3)
INSERT INTO seat (plane_id, seat_no)
VALUES (3, '1A'),
       (3, '1C'),
       (3, '5A'),
       (3, '5C'),
       (3, '20A'),
       (3, '20B');

-- Miejsca dla Embraer 190 (ID 4)
INSERT INTO seat (plane_id, seat_no)
VALUES (4, '1A'),
       (4, '1B'),
       (4, '2A'),
       (4, '2B'),
       (4, '10A'),
       (4, '10B');


INSERT INTO flight (plane_id, flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code,
                    status)
VALUES (3, 303, '2025-06-15 10:00:00', 'WAW', '2025-06-15 18:00:00', 'DXB', 'scheduled'),
       (3, 304, '2025-06-17 12:00:00', 'DXB', '2025-06-18 01:00:00', 'NRT', 'scheduled'),
       (4, 404, '2025-06-20 09:00:00', 'CDG', '2025-06-20 10:30:00', 'BCN', 'scheduled'),
       (1, 105, '2025-07-01 16:00:00', 'LHR', '2025-07-01 18:00:00', 'FRA', 'scheduled');


INSERT INTO passenger (first_name, last_name, passport_no)
VALUES ('Maria', 'Wiśniewska', 'XYZ987654'),
       ('Robert', 'Lewandowski', 'PLN000009'),
       ('Emily', 'Blunt', 'UKB112233'),
       ('Tom', 'Cruise', 'USA554433');

INSERT INTO customer (email, phone_no)
VALUES ('r.lewandowski@email.com', '+48 600 700 800'),
       ('emily.b@email.com', '+44 20 7946 0000');


-- Rezerwacja 3: Robert kupuje bilety do Dubaju dla 2 osób
INSERT INTO booking (customer_id, payment_date)
VALUES (3, '2025-05-01 12:00:00');

INSERT INTO ticket (passenger_id, booking_id, flight_id, plane_id, seat_no, price)
VALUES (5, 3, 3, 3, '1A', 2500.00), -- Robert na 1A
       (4, 3, 3, 3, '1C', 2500.00);
-- Maria na 1C

-- Rezerwacja 4: Emily kupuje bilet dla siebie (Paryż -> Barcelona)
INSERT INTO booking (customer_id, payment_date)
VALUES (4, '2025-05-10 08:30:00');

INSERT INTO ticket (passenger_id, booking_id, flight_id, plane_id, seat_no, price)
VALUES (6, 4, 5, 4, '10A', 150.00);
-- Emily leci Embraerem

-- Rezerwacja 5: Tom Cruise leci z Dubaju do Tokio
INSERT INTO booking (customer_id, payment_date)
VALUES (3, '2025-05-20 14:00:00'); -- Robert (klient) płaci za Toma

INSERT INTO ticket (passenger_id, booking_id, flight_id, plane_id, seat_no, price)
VALUES (7, 5, 4, 3, '5A', 4200.00);