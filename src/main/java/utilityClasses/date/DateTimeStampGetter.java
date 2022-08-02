package utilityClasses.date;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * @see  #getDateTime()
 * static getter method, returns current datetime
 */
public interface DateTimeStampGetter {
    static String getDateTime(){
        try{
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
            String dateText = date.format(formatter);
            //LocalDate parsedDate = LocalDate.parse(dateText, formatter);
            LocalTime time = LocalTime.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH_mm_ss");
            String timeText = time.format(timeFormatter);
            return "date_"+dateText+"_time_"+timeText;
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }
}
