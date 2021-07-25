package ru.skillbench.tasks.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.*;

public class ContactCardImpl implements ContactCard {
    private String fullName; //FN
    private String organization; //ORG
    private String gender; //G
    private Calendar birthday; //BDAY
    private Map<String, String> phones = new HashMap<>(); //TEL

    @Override
    public ContactCard getInstance(Scanner scanner) {
        String firstLine = scanner.nextLine();
        if (!firstLine.equals("BEGIN:VCARD")) {
            throw new NoSuchElementException();
        }
        boolean hasEnd = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("END:VCARD")) {
                hasEnd = true;
                break;
            }
            if (!line.contains(":")) {
                throw new InputMismatchException();
            }
            if (line.startsWith("TEL")) {
                String type = line.split("TYPE=")[1].split(":")[0];
                String num = line.split(":")[1];
                if (num.length() != 10) {
                    throw new InputMismatchException();
                }
                try {
                    Long.parseLong(num);
                } catch (NumberFormatException e) {
                    throw new InputMismatchException();
                }
                this.phones.put(type, num);
                continue;
            }

            String[] split = line.split(":");
            if (split[0].equals("FN")) {
                this.fullName = split[1];
            } else if (split[0].equals("ORG")) {
                this.organization = split[1];
            } else if (split[0].equals("GENDER")) {
                if (!split[1].equals("F") && !split[1].equals("M")) {
                    throw new InputMismatchException();
                }
                this.gender = split[1];
            } else if (split[0].equals("BDAY")) {
                try {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    cal.setTime(sdf.parse(split[1]));// all done
                    birthday = cal;
                } catch (DateTimeException | ParseException e) {
                    throw new InputMismatchException();
                }
            }
        }
        if (!hasEnd || fullName == null || organization == null) {
            throw new NoSuchElementException();
        }

        return this;
    }

    @Override
    public ContactCard getInstance(String data) {
        return getInstance(new Scanner(data));
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getOrganization() {
        return organization;
    }

    @Override
    public boolean isWoman() {
        if (gender == null) {
            return false;
        }
        return gender.equals("F");
    }

    @Override
    public Calendar getBirthday() {
        if (birthday == null) {
            throw new NoSuchElementException();
        }
        return birthday;
    }

    @Override
    public Period getAge() {
        if (birthday == null) {
            throw new NoSuchElementException();
        }
        LocalDate start = Instant.ofEpochMilli(birthday.getTimeInMillis()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = LocalDate.now();
        return Period.between(start, end);
    }

    @Override
    public int getAgeYears() {
        if (birthday == null) {
            throw new NoSuchElementException();
        }
        return getAge().getYears();
    }

    @Override
    public String getPhone(String type) {
        if (!phones.containsKey(type)) {
            throw new NoSuchElementException();
        }
        String number = phones.get(type);
        return "(" + number.substring(0, 3) + ")" + " " + number.substring(3, 6) + "-" + number.substring(6);
    }

}
