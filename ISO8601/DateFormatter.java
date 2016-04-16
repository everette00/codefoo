/**
 * Created by everette on 4/15/16.
 */
public class DateFormatter {
    String dateTime = "", timeOffset = ""; // hold the information on what the date-time and time offset are.

    //the list of days of the week, both full name and three-letter abbreviations. Used for formatting strings.
    String[] days = ("sunday,sun,monday,mon,tuesday,tue" +
            ",wednesday,wed,thursday,thu,friday,fri,saturday,sat").split(",");

    // the list of months of the year and the abbreviations. Used for formatting strings.
    String[] months = ("january,february,march,april,may,june,july," +
            "august,september,october,november,december").split(",");
    String[] monthsAbbrev = ("jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec").split(",");

    public DateFormatter(String dateTime) {
        this.dateTime = dateTime;
    }

    public DateFormatter(String dateTime, String timeOffset) {
        this.dateTime = dateTime;
        this.timeOffset = timeOffset;
    }

    // the translation from the name of a month to a numerical value.
    public String monthNum(String name) {
        // loop through all month names and find the one that is equivalent to the
        // name passed via param. Also check each abbreviation. When the match is found,
        // we take the month number to be the current iteration + 1 (january will be one, and not zero at i = 0).
        for(int i = 0; i < months.length; i++) {
            if(name.equalsIgnoreCase(months[i]) || name.equalsIgnoreCase(monthsAbbrev[i])) {
                return "" + (i + 1);
            }
        }

        return "";
    }

    public String iso8601DayMonth(String month, String day) {
        // do a numerical check on the string given for month. If the 'month' is greater than
        // 12, it can't be the month. So swap the day and month
        if(Integer.parseInt(month) > 12) {
            String s = month;
            month = day;
            day = s;
        }

        if(day.length() == 1) {
            day = "0" + day; // left-pad the day value with a zero if the char count is one (04 instead of 4)
        }

        if(month.length() == 1) {
            month = "0" + month; // left-pad the month value with zero if the char count is one.
        }

        return month + "-" + day;
    }

    public String iso8601YearMonth(String year, String month) {
        // check to see if the year passed to the method is not four characters long.
        // if not, then that isn't a valid year.
        if(year.length() != 4) {
            String t = year; // temp variable for swapping.
            if(month.length() == 4) { // is the month four chars long?
                year = month; // change the year to what the month is and month to what the year was.
                month = t;
            }
        }

        if(month.length() == 1) {
            month = "0" + month; // left-pad the month value with zero if the char count is one.
        }

        return year + "-" + month;
    }

    // take a month, day and year and turn it into a iso8601 date-time string.
    public String iso8601(String month, String day, String year) {

        // check to see if the year passed to the method is not four characters long.
        // if not, then that isn't a valid year.
        if(year.length() != 4) {
            String t = year; // temp variable for swapping.
            if(month.length() == 4) { // is the month four chars long?
                year = month; // change the year to what the month is and month to what the year was.
                month = t;
            } else {
                year = day; // similarly, do the swap on the day.
                day = t; // day is no what year was.
            }
        }

        // do a numerical check on the string given for month. If the 'month' is greater than
        // 12, it can't be the month. So swap the day and month
        if(Integer.parseInt(month) > 12) {
            String s = month;
            month = day;
            day = s;
        }

        if(day.length() == 1) {
            day = "0" + day; // left-pad the day value with a zero if the char count is one (04 instead of 4)
        }

        if(month.length() == 1) {
            month = "0" + month; // left-pad the month value with zero if the char count is one.
        }

        return year + "-" + month + "-" + day; // the format of iso8601 dates.
    }

    String[] padElements(String[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].length() == 1) {
                arr[i] = "0" + arr[i];
            }
        }

        return arr;
    }

    String buildString(String[] arr, char delimeter) {
        String str = "";

        for(int i = 0; i < arr.length; i++) {
            str += arr[i] + ((i < arr.length - 1) ? ":" : "");
        }

        return str;
    }

    public String format() {

        // do a rather exhausting evaluation to see if the dateTime given is already in
        // a pseudo iso8601 format. If it is, break it apart and format it so that we can
        // get a correct iso8601 string. i.e, look for something like this: yyyy-dd-mmThh:mm:ss+hh:mm.
        if(dateTime.matches("^(\\d{4}-\\d{2}-\\d{2})") ||
                dateTime.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}[:]\\d{2}[:]\\d{2}") ||
                dateTime.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}[:]\\d{2}[:]\\d{2}[+|-]\\d{2}[:]\\d{2}")) {
            String date = dateTime.split("T")[0].replace("-", "/");
            String time = dateTime.split("T")[1];

            return format(date + " " + time);
        } else {
            // we have some date time with a time offset. This is patch code for a edge-case.
            // particularly, Sun, 20 Mar 2016 16:05:07 -0800
            String[] date = dateTime.split(" ");

            for(String d : date) {
                if(d.matches("[+|-]\\d{4}")) {
                    timeOffset = d.substring(0, 3) + ":" + d.substring(3);

                } else if(d.matches("[+|-]\\d{1,2}:\\d{2}") ||
                        d.matches("[+|-]\\d{1,2}:\\d{2}:\\d{2}")) {
                    timeOffset = d;
                    break;
                }
            }
        }

        return format(dateTime);
    }

    // the format method. This is to boil down each given example input into a singular
    // pattern of date information.
    public String format(String dateTime) {
        // is the time given flagged for PM?
        boolean isPM = dateTime.contains("PM");

        // check to see if the dateTime was just a timestamp.
        if(dateTime.matches("\\d{1,2}:\\d{2}:\\d{2}")) {
            String[] time = dateTime.split(":");
            if(isPM) { // is it flagged for PM?

                // get the hour stamp for adding twelve to it if early in the day.
                if(Integer.parseInt(time[0]) < 12) {
                    time[0] = "" + (Integer.parseInt(time[0] + 12));

                    // return the timestamp rejoined.
                    return String.format("%s:%s:%s", time[0], time[1], time[2]);
                }
            }

            //pad the time with zeros if each unit is not two characters long.
            return (dateTime = buildString(padElements(time), ':'));
        }

        // the capitalization of letters does not matter here. We care only for the numerical integrity of
        // the date-time data.
        dateTime = dateTime.toLowerCase();

        // find out if dateTime contains a non-numeric representation of the day of the week
        for (String day : days) {
            if (dateTime.contains(day)) {
                // strip the day out of the dateTime string. Weekdays do not play a key
                // role in iso8601.

                dateTime = dateTime.replace(day, "");
                break;
            }
        }

        //System.out.println(dateTime);

        // remove all instances of 'of', 'st' and so on so that we don't have
        // words in the dateTime, just numbers. "2nd" bares no more necessary data
        // on the day than the value '2' does.
        dateTime = dateTime.replaceAll("[of|st|th|nd|rd|on]{2}", "");

        // because of the formatting, we will have extra spaces where they shouldn't be.
        // we also don't need commas. So we remove the extra spaces and all commas.
        dateTime = dateTime.replaceAll(",", "").replaceAll("\\s{2,}", " ").replaceAll("^\\s", "");

        // create a list of all the left over information in dateTime.
        String[] dateInformation = dateTime.split(" ");

        String newDateTime = ""; // used for rebuilding dateTime after further formatting.

        for(int i = 0; i < dateInformation.length; i++) {
            // if we find a non-numeric month value, translate it.
            if(dateInformation[i].matches("\\w+[A-Za-z]")) {
                dateInformation[i] = monthNum(dateInformation[i]);
            } else if(dateInformation[i].matches("\\d{1,2}:\\d{2}:\\d{2}") && isPM) {
                // if we have a time stamp and we are flagged for PM,
                // translate it to military time by adding twelve to the hours,
                // only if the hour is less than twelve.
                String[] time = dateInformation[i].split(":");

                if(Integer.parseInt(time[0]) < 12) {
                    time[0] = "" + (Integer.parseInt(time[0]) + 12);
                    dateInformation[i] = String.format("%s:%s:%s", time[0], time[1], time[2]);
                }
            }

            // this conditional ladder will determine if we are going to separate
            // date information by slashes or put a space between the date and the time stamp.
            if(!dateInformation[i].matches("\\d{1,2}:\\d{2}:\\d{2}|\\d{1,2}:\\d{2}|(\\d{1,2}:\\d{2}:\\d{2}[+|-]\\d{2}:\\d{2})")) {
                if(i > 0) { // only add a forward slash if its not the first dateInformation item.
                    newDateTime += "/";
                }

                // append the dateInformation item to the end of newDateTime
                newDateTime += dateInformation[i];
            } else {
                if(i > 0) { // only add a space between the date and time stamp if it's not the first item
                    newDateTime += " ";
                }

                if(dateInformation[i].matches("\\d{1,2}:\\d{1,2}")) {
                    dateInformation[i] += ":00";
                }

                // pad and rebuild the string.
                dateInformation[i] = buildString(padElements(dateInformation[i].split(":")), ':');

                // append the time stamp to the end of newDateTime
                newDateTime += dateInformation[i];
                break;

            }
        }

        dateTime = newDateTime; // assign the date time item the value of newDateTime.

        // check to see if the pattern in dateTime follows, for example, 2014/12/3 or 3.12.2014
        if (dateTime.matches("\\d{1,4}[/.]\\d{1,4}[/.]\\d{1,4}")) {
            String[] date = dateTime.split("[/.]"); // split on either . or /

            // return a formatted string to the user.
            return iso8601(date[0], date[1], date[2]);
        }
        // check to see if we have the above pattern and a timestamp.
        else if (dateTime.matches("\\d{1,4}[/.]\\d{1,4}[/.]\\d{1,4} \\d{2}:\\d{2}:\\d{2}") ||
                dateTime.matches("\\d{1,4}[/.]\\d{1,4}[/.]\\d{1,4} \\d{2}:\\d{2}:\\d{2}[+|-]\\d{2}:\\d{2}")) {
            String[] date = dateTime.split(" ")[0].split("[/.]");
            String time = dateTime.split(" ")[1]; // the right side of the canyon.

            // we are returning the iso string and the time stamp, but with a special "T" character
            // as per the specification of iso8601. yyyymmddT00:00:00+0:0
            return iso8601(date[0], date[1], date[2]) + "T" + time + timeOffset;
        }
        // do we have a date time in the form 20160512?
        else if (dateTime.matches("\\d{4}\\d{2}\\d{2}")){
            // do a substring on the different components of the date.
            // the year is the first four digits, the month is the next two,
            // and the day is the last.
            String year = dateTime.substring(0, 4),
            month = dateTime.substring(4, 6),
            day = dateTime.substring(6, 8);

            return iso8601(month, day, year);
        } else if(dateTime.matches("\\d{4}\\d{2}\\d{2} \\d{1,2}:\\d{2}:\\d{2}")) {
            // evaluate similarly to the top, but with the addition of a
            // time stamp.
            String year = dateTime.substring(0, 4),
            month = dateTime.substring(4, 6),
            day = dateTime.substring(6, 8),
            time = dateTime.split(" ")[1]; // there is a space between the date and time, so use it to get the time.

            // yyyymmddT00:00:00+0:0 iso8601 format.
            return iso8601(day, month, year) + "T" + time + timeOffset;
        } else if(dateTime.matches("\\d{1,2}[/.]\\d{1,2}")) {
            String[] date = dateTime.split("[/.]"); // split on either . or /

            // return a formatted string to the user.
            return iso8601DayMonth(date[0], date[1]);
        } else if(dateTime.matches("\\d{1,4}[/.]\\d{1,4}")) {
            String[] date = dateTime.split("[/.]"); // split on either . or /

            // return a formatted string to the user.
            return iso8601YearMonth(date[0], date[1]);
        }

        return dateTime;
    }
}
