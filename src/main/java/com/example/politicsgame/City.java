package com.example.politicsgame;

import java.util.*;
import java.util.stream.Collectors;

public class City {
    private boolean capitol = false;
    private CityPartySupport cityPartySupport;
    private String name;
    private static Set<String> usedNames = new HashSet<>();

    private ArrayList<City> adjacentCities = new ArrayList<>();

    private String history;

    private int population;
    private int money;
    private int happiness;

    private List<String> resources;
    private String beliefSystem;
    private String economy;
    private String localCulture;
    private String strategicImportance;
    private String geographicalFeatures;
    private String politicalSystem;
    private String notablePersonnel;
    private int i;
    private int x;
    private int y;

    private static final List<String> cityNames = Arrays.asList(
            "Elderglen", "Frosthold", "Ironpeak", "Sundale", "Shadowfen",
            "Stormwatch", "Havenwood", "Dragonspire", "Silvermoon", "Thornwall",
            "Ravenholme", "Brightwater", "Gloomwood", "Whitewind", "Starfall",
            "Winterdeep", "Sorrow's End", "Emberstone", "Windhaven", "Moonshadow",
            "Greenwarden", "Azurebay", "Duskendale", "Eaglecrest", "Goldenhall",
            "Rivermouth", "Dawnridge", "Wolfden", "Bronzegate", "King's Rest",
            "Quartzbank", "Falcon's Crest", "Harpy's Roost", "Mirthwood", "Owl's Peak",
            "Violet Shore", "Rose Gate", "Emerald Field", "Sapphire Spring", "Neverwatch",
            "Ruby Hill", "Topaz Landing", "Amber Meadows", "Diamond Vale", "Jade Garden",
            "Crimson Keep", "Onyx Hall", "Ivory Tower", "Opal Heights", "Citrine Harbor",
            "Turquoise Isle", "Obsidian Bastion", "Sardonyx Citadel", "Moonstone Bay", "Sunstone Gulch",
            "Cliffborough", "Malachite Marsh", "Rhodonite Range", "Peridot Plains", "Garnet Grove"
    );

    private static final List<List<String>> resourcesList = Arrays.asList(
            Arrays.asList("Iron", "Coal"),
            Arrays.asList("Fish", "Timber"),
            Arrays.asList("Grain", "Cattle"),
            Arrays.asList("Gold", "Silver")
    );

    private static final List<String> beliefSystems = Arrays.asList("Polytheism", "Monotheism", "Atheism", "Ancestor Worship");
    private static final List<String> economies = Arrays.asList("Agricultural", "Industrial", "Trade-based");
    private static final List<String> strategicImportances = Arrays.asList("Trade route", "Military outpost", "Cultural hub", "Political center");

    private static final List<String> geographicalFeaturesList = Arrays.asList("Coastal", "Mountainous", "Forest", "Desert");

    private static final List<String> politicalSystems = Arrays.asList("Monarchy", "Democracy", "Theocracy", "Dictatorship");

    private static final List<String> notablePersonnelList = Arrays.asList("Famous general", "Renowned artist", "Charismatic leader", "Influential merchant");

    public City(int i, int x, int y, ArrayList<Party> parties) {
        Random rand = new Random();

        this.money = rand.nextInt(100000) + 1000;
        this.happiness = 5;

        this.name = generateUniqueCityName(rand);
        this.resources = resourcesList.get(rand.nextInt(resourcesList.size()));
        this.beliefSystem = beliefSystems.get(rand.nextInt(beliefSystems.size()));
        this.population = rand.nextInt(10000) + 1000; // population between 1000 and 11000
        this.economy = economies.get(rand.nextInt(economies.size()));
        this.strategicImportance = strategicImportances.get(rand.nextInt(strategicImportances.size()));
        this.geographicalFeatures = geographicalFeaturesList.get(rand.nextInt(geographicalFeaturesList.size()));
        this.politicalSystem = politicalSystems.get(rand.nextInt(politicalSystems.size()));
        this.notablePersonnel = notablePersonnelList.get(rand.nextInt(notablePersonnelList.size()));
        this.i = i;
        this.x = x;
        this.y = y;
        // Initialize party support for this city
        this.cityPartySupport = new CityPartySupport(this, parties);
    }

    public String getName() {
        return this.name;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public int getPopulation() {
        return population;
    }

    public int getMoney(){
        return this.money;
    }

    public int getHappiness(){
        return this.happiness;
    }

    public void setCapitol(){
        this.capitol = true;
    }

    public boolean isCapitol() {
        return this.capitol;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public String getBeliefSystem() {
        return beliefSystem;
    }

    public void setBeliefSystem(String beliefSystem) {
        this.beliefSystem = beliefSystem;
    }

    public String getEconomy() {
        return economy;
    }

    public void setEconomy(String economy) {
        this.economy = economy;
    }

    public String getStrategicImportance() {
        return strategicImportance;
    }

    public void setStrategicImportance(String strategicImportance) {
        this.strategicImportance = strategicImportance;
    }

    public String getGeographicalFeatures() {
        return geographicalFeatures;
    }

    public void setGeographicalFeatures(String geographicalFeatures) {
        this.geographicalFeatures = geographicalFeatures;
    }

    public String getPoliticalSystem() {
        return politicalSystem;
    }

    public void setPoliticalSystem(String politicalSystem) {
        this.politicalSystem = politicalSystem;
    }

    public CityPartySupport getCityPartySupport() {
        return cityPartySupport;
    }

    public List<Party> getTopParties(int n) {
        // Create a map of parties to their support percentages in this city
        Map<Party, Double> partySupportMap = new HashMap<>();
        for (int i = 0; i < cityPartySupport.getParties().size(); i++) {
            partySupportMap.put(cityPartySupport.getParties().get(i), cityPartySupport.getSupportPercentages().get(i));
        }

        // Sort the parties based on their support percentages
        List<Party> sortedParties = cityPartySupport.getParties().stream()
                .sorted(Comparator.comparing(party -> -partySupportMap.get(party)))
                .collect(Collectors.toList());

        // Return the top n parties
        return new ArrayList<>(sortedParties.subList(0, Math.min(n, sortedParties.size())));
    }

    private String generateUniqueCityName(Random rand) {
        String cityName = cityNames.get(rand.nextInt(cityNames.size()));

        while (usedNames.contains(cityName)) {
            cityName = cityNames.get(rand.nextInt(cityNames.size()));
        }

        usedNames.add(cityName);
        return cityName;
    }

    public ArrayList<City> getAdjacentCities() {
        return adjacentCities;
    }

    public void setAdjacentCities(ArrayList<City> adjacentCities) {
        this.adjacentCities = adjacentCities;
    }

    public void addAdjacentCity(City city){
        this.adjacentCities.add(city);
    }

    public int getI() {
        return i;
    }
}
