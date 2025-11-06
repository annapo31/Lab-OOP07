package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new Month.SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Month.SortByMonthOrder();
    }

    public enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        private Month (final int days) {
            this.days = days ;
        }

        public int getNumOfDays () {
            return this.days ;
        }

        public static Month fromString (final String name){
            Objects.requireNonNull(name, "The string 'name' iterable cannot be null");
            // Convert the string to uppercase to match enum names
            String newName = name.toUpperCase(Locale.ITALY);

            try{
                return Month.valueOf(newName);
            } catch (IllegalArgumentException e) {
                // No direct match with any month, try partial match

                // Create an empty list to contain the partially matche months
                // If only one month matches, return it
                // else if more than one month matches or
                // there's no match, throw an IllegalArgumentException
                List<Month> matchingMonths =  new ArrayList<>();
                for (Month m : Month.values()) {
                    if (m.name().startsWith(newName)) {
                        matchingMonths.add(m);
                    }
                }

                if(matchingMonths.size() > 1) {
                    throw new IllegalArgumentException("The string in input [" + name +
                        "] is ambiguous because it matches more than 1 month");
                } else if(matchingMonths.isEmpty()) {
                    throw new IllegalArgumentException("The string in input [" + name +
                        "] doesn't matches any of the month");
                }

                return matchingMonths.get(0);
            }
        }

        
        private static class SortByMonthOrder implements Comparator<String> {

            @Override
            public int compare(String s1, String s2) {
                final Month m1 = Month.fromString(s1);
                final Month m2 = Month.fromString(s2);
                return m1.compareTo(m2);
            }
            
        }

        private static class SortByDate implements Comparator<String>{

            @Override
            public int compare(String s1, String s2) {
                final Month m1 = Month.fromString(s1);
                final Month m2 = Month.fromString(s2);
                return Integer.compare(m1.getNumOfDays(), m2.getNumOfDays());    
            }

        }
    }
}
