package com.example.politicsgame;

import java.util.*;

public class GameMap {
    private static ArrayList<City> cities = new ArrayList<>();
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
        for(int i = 0; i < parties.size(); i++)
            System.out.println(parties.get(i).getName());
        City city1 = new City(0, 290, 170, parties);
        City city2 = new City(1, 200, 325, parties);
        City city3 = new City(2, 300, 470, parties);
        City city4 = new City(3, 370, 290, parties);
        City city5 = new City(4, 522, 500, parties);
        City city6 = new City(5, 554, 335, parties);
        City city7 = new City(6, 700, 300, parties);
        City city8 = new City(7, 717, 570, parties);
        City city9 = new City(8, 770, 175, parties);
        City city10 = new City(9, 925, 295, parties);
        City city11 = new City(10, 860, 500, parties);
        City city12 = new City(11, 941, 400, parties);
        City city13 = new City(12, 1037, 530, parties);
        City city14 = new City(13, 1186, 442, parties);
        City city15 = new City(14, 1200, 300, parties);

        city1.addConnection(new Connection(city2, "land"));
        city1.addConnection(new Connection(city4, "land"));
        city1.addConnection(new Connection(city3, "land"));

        city2.addConnection(new Connection(city4, "land"));
        city2.addConnection(new Connection(city1, "land"));
        city2.addConnection(new Connection(city6, "water"));
        city2.addConnection(new Connection(city9, "water"));

        city3.addConnection(new Connection(city1, "land"));
        city3.addConnection(new Connection(city4, "land"));
        city3.addConnection(new Connection(city5, "land"));

        city4.addConnection(new Connection(city2, "land"));
        city4.addConnection(new Connection(city1, "land"));
        city4.addConnection(new Connection(city3, "land"));
        city4.addConnection(new Connection(city5, "land"));
        city4.addConnection(new Connection(city6, "land"));

        city5.addConnection(new Connection(city3, "land"));
        city5.addConnection(new Connection(city4, "land"));
        city5.addConnection(new Connection(city6, "land"));
        city5.addConnection(new Connection(city8, "land"));
        city5.addConnection(new Connection(city7, "mountains"));
        city5.addConnection(new Connection(city11, "mountains"));

        city6.addConnection(new Connection(city4, "land"));
        city6.addConnection(new Connection(city5, "land"));
        city6.addConnection(new Connection(city7, "land"));
        city6.addConnection(new Connection(city9, "water"));
        city6.addConnection(new Connection(city8, "mountains"));
        city6.addConnection(new Connection(city11, "mountains"));
        city6.addConnection(new Connection(city10, "mountains"));

        city7.addConnection(new Connection(city6, "land"));
        city7.addConnection(new Connection(city10, "land"));
        city7.addConnection(new Connection(city9, "water"));
        city7.addConnection(new Connection(city5, "mountains"));
        city7.addConnection(new Connection(city8, "mountains"));
        city7.addConnection(new Connection(city11, "mountains"));
        city7.addConnection(new Connection(city12, "mountains"));

        city8.addConnection(new Connection(city5, "land"));
        city8.addConnection(new Connection(city11, "land"));
        city8.addConnection(new Connection(city6, "mountians"));
        city8.addConnection(new Connection(city7, "mountians"));
        city8.addConnection(new Connection(city10, "mountians"));
        city8.addConnection(new Connection(city12, "mountians"));

        city9.addConnection(new Connection(city2, "water"));
        city9.addConnection(new Connection(city6, "water"));
        city9.addConnection(new Connection(city7, "water"));
        city9.addConnection(new Connection(city10, "water"));
        city9.addConnection(new Connection(city15, "water"));

        city10.addConnection(new Connection(city7, "land"));
        city10.addConnection(new Connection(city12, "land"));
        city10.addConnection(new Connection(city15, "land"));
        city10.addConnection(new Connection(city9, "water"));
        city10.addConnection(new Connection(city14, "mountains"));
        city10.addConnection(new Connection(city13, "mountains"));
        city10.addConnection(new Connection(city11, "mountains"));
        city10.addConnection(new Connection(city8, "mountains"));

        city11.addConnection(new Connection(city8, "land"));
        city11.addConnection(new Connection(city12, "land"));
        city11.addConnection(new Connection(city13, "land"));
        city11.addConnection(new Connection(city5, "mountains"));
        city11.addConnection(new Connection(city6, "mountains"));
        city11.addConnection(new Connection(city7, "mountains"));
        city11.addConnection(new Connection(city10, "mountains"));

        city12.addConnection(new Connection(city10, "land"));
        city12.addConnection(new Connection(city11, "land"));
        city12.addConnection(new Connection(city13, "land"));
        city12.addConnection(new Connection(city14, "mountains"));
        city12.addConnection(new Connection(city15, "mountains"));
        city12.addConnection(new Connection(city7, "mountains"));
        city12.addConnection(new Connection(city8, "mountains"));

        city13.addConnection(new Connection(city11, "land"));
        city13.addConnection(new Connection(city12, "land"));
        city13.addConnection(new Connection(city14, "land"));
        city13.addConnection(new Connection(city10, "mountains"));
        city13.addConnection(new Connection(city15, "mountains"));

        city14.addConnection(new Connection(city13, "land"));
        city14.addConnection(new Connection(city15, "land"));
        city14.addConnection(new Connection(city12, "mountains"));
        city14.addConnection(new Connection(city10, "mountains"));

        city15.addConnection(new Connection(city10, "land"));
        city15.addConnection(new Connection(city14, "land"));
        city15.addConnection(new Connection(city9, "water"));
        city15.addConnection(new Connection(city12, "mountains"));
        city15.addConnection(new Connection(city13, "mountains"));

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
    }

    public void createParties(int number) {
        ArrayList<String> shuffledNames = new ArrayList<>(partyNames);
        Collections.shuffle(shuffledNames);

        ArrayList<Party> parties = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String name = shuffledNames.get(i);
            Party party = new Party(name);
            parties.add(party);
        }
        parties.add(Main.party);

        GameMap.parties.addAll(parties);
    }

    public static List<City> shortestPath(City start, City end) {
        Map<City, Double> distances = new HashMap<>();
        Map<City, City> previous = new HashMap<>();
        PriorityQueue<City> queue = new PriorityQueue<>();

        for (City city : GameMap.cities) {
            distances.put(city, Double.POSITIVE_INFINITY);
            previous.put(city, null);
        }

        distances.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            City current = queue.poll();

            if (current == end) {
                break;
            }

            double currentDistance = distances.getOrDefault(current, Double.POSITIVE_INFINITY);

            for (Connection connection : current.getConnections()) {
                City neighbor = connection.getCity();
                double weight = getWeight(connection.getConnectionType());

                double distance = currentDistance + weight;
                if (distance < distances.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    distances.put(neighbor, distance);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        List<City> path = new ArrayList<>();
        City current = end;
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }

        Collections.reverse(path);

        return path;
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