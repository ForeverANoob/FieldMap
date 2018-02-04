package com.example.andresbarreiros.fieldmap;

/*
 *
 * This object is a single entry of a fingerprint. The classifier creates many
 * of these during each call of getRoomID(). Each fingerprint runs through and
 * scores the reading using scoreMac(). This is mostly a convinience class.
 *
 */

public class FingerprintEntry {
    private int min_strength;
    private int max_strength;
    private String mac_address;
    private float rangemult = (float) 1; //Multiplier on upper/lower bounds on scoring

    public void setRangemult(float value){

        this.rangemult = value;

    }

    public FingerprintEntry(String mac_address, int max_strength, int min_strength){

        //Constructor

        this.setMac_address(mac_address);
        this.setMax_strength(max_strength);
        this.setMin_strength(min_strength);

    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public int getMax_strength() {
        return max_strength;
    }

    public void setMax_strength(int max_strength) {
        this.max_strength = max_strength;
    }

    public int getMin_strength() {
        return min_strength;
    }

    public void setMin_strength(int min_strength) {
        this.min_strength = min_strength;
    }

    public double scoreMac(int str){ //Scores a wifi strength against a fingerprint

        // This scoring operates on a really cheap and simple triangular distribution
        // which basically just starts at the lower end of the range, goes right
        // up to 1 at the average, and then back down to 0 on the high end.
        //
        // basically picking along this function
        //
        // score
        //  ^
        //  |
        // 1|               ^
        //  |              / \
        //  |             /   \
        //  |            /     \
        //  |           /       \
        //  |          /         \
        // _+_________/           \____________> signal strength
        // 0|        ^      ^      ^
        //          min    avg    max
        //
        //  where the distance between min and max are scaled outwards by the
        //  rangemult value, which was tweaked to account for variance in
        //  signals. Now if you'll excuse me I just drew an ASCII triangular
        //  transfer function so I think I'm going to go lie down.
        //
        //  If you @ me about unicode box drawing lines I will slay you
        //

        double avg = (float) ((this.getMin_strength()+this.getMax_strength())/2.0);
        double range = Math.abs(this.getMax_strength() - this.getMin_strength());
        range *= rangemult;

        double score = 0;

        if ((str <= avg-(range/2.0)) || (str >= avg+(range/2.0))){ //Accounting for range

            score = 0;

        }
        else if (str != avg){ //Scoring along transfer function
            score = 1-(Math.abs(str-avg)/(range/2.0));

        }
        else if (str == avg){  //perfect score
            score = 1;
        }
        return score;

    }
}

