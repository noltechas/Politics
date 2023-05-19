package com.example.politicsgame;

import java.util.*;

public class GameMap {
    private static ArrayList<City> cities = new ArrayList<>();
    private static ArrayList<MountainRange> mountainRanges = new ArrayList<>();
    private static String kingdomName = "";
    private static GameMap instance;
    public static ArrayList<Party> parties = new ArrayList<>();
    private static final List<String> partyNames = Arrays.asList(
            "The Freedom Party", "The Liberty Party", "The Justice Party", "The Equality Party",
            "The People's Party", "The Unity Party", "The Progress Party", "The Peace Party",
            "The Democracy Party", "The Future Party", "The Prosperity Party", "The Innovation Party",
            "The Solidarity Party", "The Opportunity Party", "The Vision Party", "The Change Party",
            "The Tradition Party", "The Integrity Party", "The Courage Party", "The Compassion Party",
            "The Wisdom Party", "The Strength Party", "The Honor Party", "The Hope Party",
            "The Harmony Party", "The Revival Party", "The Heritage Party", "The Community Party",
            "The Endeavor Party", "The Pioneer Party", "The Renaissance Party", "The Expedition Party",
            "The Discovery Party", "The Preservation Party", "The Patriot Party", "The Victory Party",
            "The Valor Party", "The Glory Party", "The Triumph Party", "The Pinnacle Party",
            "The Summit Party", "The Apex Party", "The Zenith Party", "The Crest Party",
            "The Crown Party", "The Peak Party", "The Pinnacle Party", "The Vertex Party",
            "The Acme Party", "The Summit Party", "The Crest Party", "The Zenith Party"
    );

    public GameMap() {
        createCities();
    }

    public static ArrayList<City> getCities() {
        return cities;
    }
    public static ArrayList<MountainRange> getMountainRanges() {
        return mountainRanges;
    }

    public static String getKingdomName() {
        return kingdomName;
    }

    public static void addParty(Party party) {
        parties.add(party);
    }

    public static GameMap getInstance() {
        if (instance == null) {
            instance = new GameMap();
        }
        return instance;
    }

    private void createCities() {
        setKingdomName();
        createParties(12);
        City city1 = new City(0, 290, 170, parties);
        City city2 = new City(1, 200, 325, parties);
        City city3 = new City(2, 300, 470, parties);
        City city4 = new City(3, 370, 290, parties);
        City city5 = new City(4, 522, 500, parties);
        City city6 = new City(5, 554, 335, parties);
        City city7 = new City(6, 700, 300, parties);
        City city8 = new City(7, 700, 580, parties);
        City city9 = new City(8, 770, 175, parties);
        City city10 = new City(9, 925, 295, parties);
        City city11 = new City(10, 860, 500, parties);
        City city12 = new City(11, 941, 400, parties);
        City city13 = new City(12, 1037, 530, parties);
        City city14 = new City(13, 1186, 442, parties);
        City city15 = new City(14, 1200, 300, parties);
        MountainRange mr1 = new MountainRange(740,423,-23,26);
        MountainRange mr2 = new MountainRange(800,580,56,17);
        MountainRange mr3 = new MountainRange(1072,400,-43,20);

        city1.addAdjacentCity(city2);
        city1.addAdjacentCity(city4);
        city1.addAdjacentCity(city6);
        city1.addAdjacentCity(city9);

        city2.addAdjacentCity(city1);
        city2.addAdjacentCity(city4);
        city2.addAdjacentCity(city3);

        city3.addAdjacentCity(city2);
        city3.addAdjacentCity(city4);
        city3.addAdjacentCity(city5);

        city4.addAdjacentCity(city1);
        city4.addAdjacentCity(city2);
        city4.addAdjacentCity(city3);
        city4.addAdjacentCity(city5);
        city4.addAdjacentCity(city6);

        city5.addAdjacentCity(city3);
        city5.addAdjacentCity(city4);
        city5.addAdjacentCity(city6);
        city5.addAdjacentCity(city8);

        city6.addAdjacentCity(city4);
        city6.addAdjacentCity(city5);
        city6.addAdjacentCity(city7);
        city6.addAdjacentCity(city9);

        city7.addAdjacentCity(city6);
        city7.addAdjacentCity(city9);
        city7.addAdjacentCity(city10);

        city8.addAdjacentCity(city5);
        city8.addAdjacentCity(city11);

        city9.addAdjacentCity(city1);
        city9.addAdjacentCity(city6);
        city9.addAdjacentCity(city7);
        city9.addAdjacentCity(city10);
        city9.addAdjacentCity(city15);

        city10.addAdjacentCity(city7);
        city10.addAdjacentCity(city9);
        city10.addAdjacentCity(city12);
        city10.addAdjacentCity(city15);

        city11.addAdjacentCity(city8);
        city11.addAdjacentCity(city12);
        city11.addAdjacentCity(city13);

        city12.addAdjacentCity(city10);
        city12.addAdjacentCity(city11);
        city12.addAdjacentCity(city13);

        city13.addAdjacentCity(city11);
        city13.addAdjacentCity(city12);
        city13.addAdjacentCity(city14);

        city14.addAdjacentCity(city13);
        city14.addAdjacentCity(city15);

        city15.addAdjacentCity(city10);
        city15.addAdjacentCity(city14);

        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        cities.add(city5);
        cities.add(city6);
        cities.add(city7);
        cities.add(city8);
        cities.add(city9);
        cities.add(city10);
        cities.add(city11);
        cities.add(city12);
        cities.add(city13);
        cities.add(city14);
        cities.add(city15);

        mountainRanges.add(mr1);
        mountainRanges.add(mr2);
        mountainRanges.add(mr3);
    }

    public void createParties(int number) {
        ArrayList<String> shuffledNames = new ArrayList<>(partyNames);
        Collections.shuffle(shuffledNames);

        ArrayList<Party> parties = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String name = shuffledNames.get(i);
            Party party = new Party(name,false);
            parties.add(party);
        }
        parties.add(Main.party);

        GameMap.parties.addAll(parties);
    }


    private static double getWeight(String connectionType) {
        switch (connectionType) {
            case "land":
                return 2;
            case "water":
                return 1;
            case "mountains":
                return 4;
            default:
                return Double.POSITIVE_INFINITY;
        }
    }

    public static void addCity(City city){
        cities.add(city);
    }

    public void setKingdomName(){
        ArrayList<String> kingdomNames = new ArrayList<>();
        kingdomNames.add("Avaloria");
        kingdomNames.add("Eldoria");
        kingdomNames.add("Glenholme");
        kingdomNames.add("Lorendale");
        kingdomNames.add("Sylvandor");
        kingdomNames.add("Westreach");
        kingdomNames.add("Duskwood");
        kingdomNames.add("Silverwind");
        kingdomNames.add("Stormgaard");
        kingdomNames.add("Blackwater");
        kingdomNames.add("Ravencrest");
        kingdomNames.add("Ironhold");
        kingdomNames.add("Thornridge");
        kingdomNames.add("Goldenhelm");
        kingdomNames.add("Highmoor");
        kingdomNames.add("Shadowfen");
        kingdomNames.add("Dragonfall");
        kingdomNames.add("Frostholm");
        kingdomNames.add("Wolfheart");
        kingdomNames.add("Stonehaven");
        kingdomNames.add("Crystalwyn");
        kingdomNames.add("Moonridge");
        kingdomNames.add("Emberstone");
        kingdomNames.add("Stormwatch");
        kingdomNames.add("Mistwood");
        kingdomNames.add("Rivervale");
        kingdomNames.add("Starfall");
        kingdomNames.add("Sundew");
        kingdomNames.add("Dawnharbor");
        kingdomNames.add("Blackthorn");
        kingdomNames.add("Westermark");
        kingdomNames.add("Crimsonreach");
        kingdomNames.add("Wintermere");
        kingdomNames.add("Silverpeak");
        kingdomNames.add("Hollowspire");
        kingdomNames.add("Firebrand");
        kingdomNames.add("Shadowdale");
        kingdomNames.add("Ironridge");
        kingdomNames.add("Stormholm");
        kingdomNames.add("Dragonhold");
        kingdomNames.add("Frostwynd");
        kingdomNames.add("Hawkmoor");
        kingdomNames.add("Stonewall");
        kingdomNames.add("Glimmerstone");
        kingdomNames.add("Moonshadow");
        kingdomNames.add("Stormhaven");
        kingdomNames.add("Mistholm");
        kingdomNames.add("Starhaven");
        Collections.shuffle(kingdomNames);
        kingdomName = kingdomNames.get(0);
    }

}