select ps.id, ps.first_name || ' ' || ps.last_name as name, t.seat_no, afrom.country country_from, ato.country country_to
from passenger ps
         join ticket t on ps.id = t.passenger_id
         join flight f on f.id = t.flight_id and f.plane_id = t.plane_id
         join airport afrom on f.arrival_airport_code = afrom.code
         join airport ato on f.departure_airport_code = ato.code
where f.departure_airport_code = 'WAW'
  and f.arrival_airport_code = 'DXB';