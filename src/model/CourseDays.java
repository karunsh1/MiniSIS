package model;

public class CourseDays {

    String courseDays;

    public String getCourseDays(int days) {
        courseDays = dayString(("" + days).charAt(0));

        if (("" + days).length() == 2) {
            courseDays = courseDays + ", " + dayString(("" + days).charAt(1));
        }

        return courseDays;
    }

    private String dayString(char day) {
        switch (day) {
            case '1':
                return "Monday";
            case '2':
                return "Tuesday";
            case '3':
                return "Wednesday";
            case '4':
                return "Thursday";
            case '5':
                return "Friday";
            default:
                return "No Day(s) available";
        }
    }
}
