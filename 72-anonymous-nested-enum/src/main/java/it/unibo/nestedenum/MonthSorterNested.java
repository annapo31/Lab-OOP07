package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    static final Locale LOCALE = Locale.ITALY;

    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
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

        public Month fromString (final String name){
            Objects.requireNonNull(name, "The string 'name' iterable cannot be null");
            // Si convertono in maiuscolo i caratteri minuscoli della string
            name.toUpperCase();

            try{
                return Month.valueOf(name);
            } catch (IllegalArgumentException e) {
                // Nessun mese corrisponde esattamente al nome

                // Creo una lista vuota per contenere i mesi che matchano parzialmente
                // Se nessun mese matcha, la lista rimane vuota e lanciamo errore
                // Se ne matchano più di uno, lanciamo errore
                List<String> matchingMonths =  List.of();
                for (Month m : Month.values()) {
                    if (m.name().startsWith(name)) {
                        matchingMonths.add(m.name());
                    }
                }

                if(matchingMonths.size() > 1) {
                    throw new IllegalArgumentException("The string in input [" + name +
                        "] is ambiguous because it matches more than 1 month");
                } else if(matchingMonths.isEmpty()) {
                    throw new IllegalArgumentException("The string in input [" + name +
                        "] doesn't matches any of the month");
                }

                return Month.valueOf(matchingMonths.get(0));
            }
        }

        /* TODOOOOOOO
        public static SortByMonthOrder implements Comparator<String> {
            
        }

        public static SortByDate implements Comparator<String>{

        }*/
    }
}
