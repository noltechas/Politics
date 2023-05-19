package com.example.politicsgame;

import java.util.ArrayList;
import java.util.Collections;

public class MountainRange {

    private String name;
    private int x;
    private int size;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int y;
    private int rotation;
    private ArrayList<Connection> connections = new ArrayList<>();

    public MountainRange(int x, int y, int rotation, int size){
        this.name = generateName();
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.size = size;
    }

    private String generateName() {
        ArrayList<String> mountainRangeNames = new ArrayList<>();
        mountainRangeNames.add("Dragonspine Mountains");
        mountainRangeNames.add("Shadowfang Peaks");
        mountainRangeNames.add("Ironhelm Range");
        mountainRangeNames.add("Mistveil Peaks");
        mountainRangeNames.add("Stormcrest Mountains");
        mountainRangeNames.add("Frostbite Range");
        mountainRangeNames.add("Silverpeak Mountains");
        mountainRangeNames.add("Dreadfort Peaks");
        mountainRangeNames.add("Ravenwing Range");
        mountainRangeNames.add("Bloodfang Mountains");
        mountainRangeNames.add("Stonehammer Range");
        mountainRangeNames.add("Wyrmscale Peaks");
        mountainRangeNames.add("Serpent's Spine");
        mountainRangeNames.add("Blackrock Mountains");
        mountainRangeNames.add("Thunderspire Peaks");
        mountainRangeNames.add("Gloomshroud Range");
        mountainRangeNames.add("Firebrand Mountains");
        mountainRangeNames.add("Dragontooth Range");
        mountainRangeNames.add("Riftpeak Mountains");
        mountainRangeNames.add("Sorrow's Reach");
        mountainRangeNames.add("Everfrost Range");
        mountainRangeNames.add("Ashen Peaks");
        mountainRangeNames.add("Ironspike Mountains");
        mountainRangeNames.add("Cinderpeak Range");
        mountainRangeNames.add("Stonewall Mountains");
        mountainRangeNames.add("Shadowveil Peaks");
        mountainRangeNames.add("Thunderjaw Range");
        mountainRangeNames.add("Frostfire Mountains");
        mountainRangeNames.add("Duskfall Peaks");
        mountainRangeNames.add("Stormcrag Range");
        mountainRangeNames.add("Obsidian Mountains");
        mountainRangeNames.add("Wyrmcrest Peaks");
        mountainRangeNames.add("Ravenshadow Range");
        mountainRangeNames.add("Blackscale Mountains");
        mountainRangeNames.add("Valeheart Peaks");
        mountainRangeNames.add("Dreadfang Range");
        mountainRangeNames.add("Grimfrost Mountains");
        mountainRangeNames.add("Ironjaw Range");
        mountainRangeNames.add("Shadowspire Peaks");

        Collections.shuffle(mountainRangeNames);
        return mountainRangeNames.get(0);
    }

    public String getName(){
        return this.name;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
