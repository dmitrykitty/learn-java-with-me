--QUERY PLAN
--key word to know how does query work
--Seq Scan on ticket  (cost=0.00..1.55 rows=55 width=60)
--seq scan -> full scan of the table
--cost -> cost-based optimization (each operation has its cost)
    --page-cost (input-output) = 1.0
    --cpu-cost 0.01
--width -> avg sum of memory for one row
    ------------------------------------------------------------------------------------------
    --W bazach danych dane nie są czytane po jednym wierszu, ale w "stronach" (zazwyczaj 8 KB).
    --Page-cost określa wysiłek potrzebny na pobranie jednej takiej strony z dysku do pamięci RAM.

    --W PostgreSQL (którego używamy) rozróżnia się dwa główne rodzaje tego kosztu:

        --seq_page_cost: Koszt odczytu sekwencyjnego (czytanie strony po stronie, np. przy Full Table Scan).
        --Domyślnie wynosi 1.0 i stanowi punkt odniesienia dla wszystkich innych kosztów.

        --random_page_cost: Koszt odczytu losowego (skakanie po dysku, np. przy korzystaniu z indeksu).
        --Domyślnie wynosi 4.0. Jest wyższy, ponieważ fizyczne dyski (zwłaszcza HDD) wolniej radzą sobie z szukaniem danych w różnych miejscach.

    --Gdy strony z danymi są już w pamięci, procesor musi je "obrobić" –
    --przefiltrować wiersze, sprawdzić warunki WHERE czy połączyć dane z dwóch tabel. CPU-cost to koszt tych operacji.

    --Wartości te są zazwyczaj znacznie niższe niż Page-cost, bo operacje w pamięci są szybsze niż I/O:

        --cpu_tuple_cost: Koszt przetworzenia jednego wiersza (tupli). Domyślnie ok. 0.01.

        --cpu_index_tuple_cost: Koszt przetworzenia jednego wpisu w indeksie podczas skanowania. Domyślnie ok. 0.005.

        --cpu_operator_cost: Koszt wykonania operacji matematycznej lub logicznej (np. sprawdzenie czy wiek > 18).
    ------------------------------------------------------------------------------------------

--55 * 0.01 + x * 1.0 = 1.55 where x - amount of pages(pg_schema -> tables -> pg_class -> real_name = 'ticket'
explain select *
from ticket;

select reltuples, relpages, relkind
from pg_class
where relname = 'ticket';

explain select flight_id, count(*)
from ticket
group by flight_id;
--read plan from bottom
    --seq scan
    --grouping
    --hashaggregate


