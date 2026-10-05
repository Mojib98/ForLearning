package example.DSL;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.OptionalInt;

/**
 * @author M.R Khabireh
 * Date: 17/08/2026
 * Time: 14:09
 */
public class Main {
    static void main() {
        String date ="20260819143025";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime parse = LocalDateTime.parse(date, dateTimeFormatter);
        LocalDateTime localDateTime = parse.plusMinutes(30);
        LocalDate localDate = LocalDate.now();

        LocalDate result =
                localDate.with(TemporalAdjusters.lastDayOfMonth());
//        System.out.println(result);

        ZoneId bakuZone = ZoneId.of("America/New_York");
        LocalDateTime dateTime = LocalDateTime.now();
        ZonedDateTime zdt = dateTime.atZone(bakuZone);
        System.out.println(zdt);


        ZonedDateTime meetingInBaku = ZonedDateTime.of(
                LocalDateTime.of(2026, 8, 20, 14, 0), ZoneId.of("Asia/Baku"));
        ZonedDateTime meetingInNY = meetingInBaku.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("باکو: " + meetingInBaku);
        System.out.println("نیویورک: " + meetingInNY);
        /*_______________________*/
        Instant now = Instant.now();
        ZoneId zoneId1 = ZoneId.of("America/New_York");
        ZoneId zoneId = ZoneId.of("Asia/Baku");
        ZonedDateTime zonedDateTime = now.atZone(zoneId);
        ZonedDateTime zonedDateTime1 = now.atZone(zoneId1);
        System.out.println(zonedDateTime1.withZoneSameInstant(zoneId));
        System.out.println(zonedDateTime.withZoneSameInstant(zoneId1));
    }
}
