package com.dnikitin.jdbc.dto;

/**
 * A Data Transfer Object (DTO) used to encapsulate and transport data
 * required for filtering query results.
 * <p>
 * This class adheres to the DTO pattern by acting as a pure data container
 * without any business logic or behavior. Its primary purpose is to aggregate
 * multiple search criteria (such as pagination limits and offsets) into a
 * single object, thereby decoupling the service layer method signatures
 * from the specific parameters required by the data access layer.
 * </p>
 * * @see <a href="https://martinfowler.com/eaaCatalog/dataTransferObject.html">
 * Pattern: Data Transfer Object (Martin Fowler)</a>
 */
public record TicketFilter(int limit,
                           int offset,
                           String passengerName,
                           String seatNo) {
}
