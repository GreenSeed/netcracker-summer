package com.nitok.netcrackercourse.week1.address;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Presentation {
    List<Human> humanList;

    public Presentation(List<Human> humanList) {
        this.humanList = humanList;
    }

    public Human findHumanByLastName(String lastName) {
        return humanList.stream()
                .filter(human -> human.getLastName().equals(lastName))
                .findAny().get();
    }

    public Human findHumanByAddress(Address address) {
        return humanList.stream()
                .filter(human -> human.getAddress().equals(address))
                .findAny().get();
    }

    public List<Human> findHumansBetweenDates(LocalDate from, LocalDate to) {
        return humanList.stream()
                .filter(human ->
                        human.getBirthdate().isAfter(from) && human.getBirthdate().isBefore(to))
                .collect(Collectors.toList());
    }

    public Human findElderestHuman() {
        return humanList.stream()
                .min(Comparator.comparing(Human::getBirthdate))
                .get();
    }

    public Map<String, List<Human>> findHumansOnSameStreet() {
        Map<String, List<Human>> tmp = new HashMap<>();
        Set<String> uniqueStreets = humanList.stream().map(human -> human.getAddress().getStreet()).collect(Collectors.toSet());

        uniqueStreets.forEach(s -> {
            tmp.put(s, new ArrayList<>());
        });
        humanList.forEach(human -> {
            String street = human.getAddress().getStreet();
            if (tmp.containsKey(street)) {
                tmp.get(street).add(human);
            }
        });
        Map<String, List<Human>> result = new HashMap<>();
        uniqueStreets.forEach(s -> {
            List<Human> humans = tmp.get(s);
            if (humans.size() > 1) {
                result.put(s, humans);
            }
        });
        return result;
    }

    public static void main(String[] args) {

        Address lipki = new Address(
                Country.RUSSIA,
                "??????????",
                "??????????????????",
                1,
                1
        );

        Address sergiev = new Address(
                Country.RUSSIA,
                "?????????????? ??????????",
                "??????????????????",
                200
        );

        Address moscow = new Address(
                Country.RUSSIA,
                "????????????",
                "?????????????????????? ??????????",
                9,
                58
        );

        Address berlin = new Address(
                Country.GERMANY,
                "Berlin",
                "Schweisse",
                10
        );

        Human ivan = new Human(
                "????????",
                "??????????????????",
                lipki,
                LocalDate.of(1998, 10, 20)
        );
        Human pavel = new Human(
                "??????????",
                "??????????????",
                sergiev,
                LocalDate.of(1999, 6, 30)
        );
        Human kirill = new Human(
                "????????????",
                "??????????????????",
                moscow,
                LocalDate.of(1969, 4, 5)
        );
        Human such = new Human(
                "Such",
                "Kovoisch",
                moscow,
                LocalDate.of(2005, 6, 21)
        );

        ArrayList<Human> humanList = new ArrayList<Human>() {{
            add(ivan);
            add(pavel);
            add(kirill);
            add(such);
        }};
        Presentation presentation = new Presentation(humanList);
        System.out.println("?????????????? ????????: ");
        humanList.forEach(System.out::println);
        System.out.println();

        Human kovalenko = presentation.findHumanByLastName("??????????????????");
        System.out.println("?????????????? ?? ???????????????? ?????????????????? "+kovalenko);
        System.out.println();

        Human servievHuman = presentation.findHumanByAddress(sergiev);
        System.out.println("?????????????? ???? ???????????? ?????????????? ?????????? "+servievHuman);
        System.out.println();

        List<Human> between19981999 = presentation.findHumansBetweenDates(
                LocalDate.of(1998,1,1),
                LocalDate.of(2000,1,1)
        );
        System.out.println("???????? 1998-2000 ??.??. ");
        between19981999.forEach(System.out::println);
        System.out.println();

        Human theElderest = presentation.findElderestHuman();
        System.out.println("?????????? ???????????? ?????????????? "+theElderest);

        System.out.println();
        Map<String,List<Human>> humans = presentation.findHumansOnSameStreet();
        humans.forEach((s, humans1) -> {
            System.out.println("???? ??????????  "+s+" ?????????? "+humans1.size() +" ????????????????");
            humans1.forEach(System.out::println);
        });
    }
}
