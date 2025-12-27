-- Task 1: Who flew the day before yesterday on the flight from Minsk (MNK) to London (LDN) in seat B1?

select t.passenger_name
from ticket t
         join flight f on t.flight_id = f.id
where t.seat_no = 'B1'
  and f.departure_airport_code = 'MNK'
  and f.arrival_airport_code = 'LDN'
  and f.departure_date::date = (current_date - interval '2 days')::date;

--Task 2: How many seats remained unoccupied on 2020-06-14 for flight MN3002?

select count(*)
from aircraft a
         join flight f on a.id = f.aircraft_id
         join seat s on a.id = s.aircraft_id
         left join ticket t on s.seat_no = t.seat_no and f.id = t.flight_id
where f.flight_no = 'MN3002'
  and f.departure_date::date = '2020-06-14'
  and t.flight_id is null;

select (select count(*)
        from seat s
                 join flight f on f.aircraft_id = s.aircraft_id
        where flight_no = 'MN3002'
          and f.departure_date::date = '2020-06-14')
           -
       (select count(*)
        from flight f
                 join ticket t on f.id = t.flight_id
        where f.flight_no = 'MN3002'
          and f.departure_date::date = '2020-06-14');


--Task 3: Which two flights were the longest of all time?
select (arrival_date - departure_date)::time as diff
from flight
order by diff desc
limit 2;

--Task 4: What are the maximum and minimum flight durations between Minsk and London, and how many such flights were there in total?
select min(diff), max(diff), count(*)
from (select (arrival_date - departure_date)::time as diff
      from flight
      where departure_airport_code in ('MNK', 'LDN')
        and arrival_airport_code in ('LDN', 'MNK')) help;


--Task 5: Which names occur most frequently, and what percentage of the total passenger count do they represent?
select passenger_name,
       round((count(*)::numeric / sum(count(*)) over ()), 3) freq
from ticket
group by passenger_name
order by freq desc;


-- Task 6: Display passenger names and the total number of tickets bought for each name. Additionally,
-- show the difference between this count and the maximum number of tickets bought by any single name group.
select passenger_no, passenger_name, count(*) tickets_amounts,
       max(count(*)) over () - count(*) diff
from ticket
group by passenger_no, passenger_name
order by tickets_amounts desc;

-- Task 7: List the costs of all routes in descending order. Display the price difference
-- between the current route and the subsequent routes in the sorted list.
select f.flight_no, f.departure_airport_code || ' -> ' || f.arrival_airport_code route, t.cost,
       t.cost - lead(t.cost) over (order by t.cost desc) diff
from flight f join ticket t on f.id = t.flight_id
order by t.cost desc;