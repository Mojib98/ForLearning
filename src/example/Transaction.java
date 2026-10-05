package example;

/**
 * @author M.R Khabireh
 * Date: 15/08/2026
 * Time: 17:06
 */
public record Transaction(
        String accountNumber,
        String type,
        long amount
) {}
