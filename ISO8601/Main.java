public class Main {

    public static void main(String[] args) {
        // every expected type of input.

        DateFormatter formatter = new DateFormatter("3/20/2016");
        System.out.println(formatter.format());

        formatter = new DateFormatter("4:05:07");
        System.out.println(formatter.format());

        formatter = new DateFormatter("Sunday, March 20, 2016");
        System.out.println(formatter.format());

        formatter = new DateFormatter("Sunday, March 20, 2016 4:05 PM");
        System.out.println(formatter.format());

        formatter = new DateFormatter("Sunday, March 20, 2016 4:05:07 PM");
        System.out.println(formatter.format());

        formatter = new DateFormatter("Sunday 20th of March 2016 04:05:07 PM");
        System.out.println(formatter.format());

        formatter = new DateFormatter("Sunday, MAR 20, 2016");
        System.out.println(formatter.format());

        formatter = new DateFormatter("3/20/2016 4:05 PM");
        System.out.println(formatter.format());

        formatter = new DateFormatter("3/20/2016 4:05:07 PM");
        System.out.println(formatter.format());

        formatter = new DateFormatter("March 20, 2016");
        System.out.println(formatter.format());

        formatter = new DateFormatter("March 20");
        System.out.println(formatter.format());

        formatter = new DateFormatter("March, 2016");
        System.out.println(formatter.format());

        formatter = new DateFormatter("Sun, 20 Mar 2016 16:05:07 -0800");
        System.out.println(formatter.format());

        formatter = new DateFormatter("20160320 16:05:07");
        System.out.println(formatter.format());

        formatter = new DateFormatter("20160320");
        System.out.println(formatter.format());

        formatter = new DateFormatter("2016.03.20");
        System.out.println(formatter.format());

        formatter = new DateFormatter("20/03/2016");
        System.out.println(formatter.format());

        formatter = new DateFormatter("20 March 2016");
        System.out.println(formatter.format());

        formatter = new DateFormatter("2016-20-03T16:05:07-08:00");
        System.out.println(formatter.format());
    }
}
