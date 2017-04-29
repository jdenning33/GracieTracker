package com.ece435.gracietracker;

import java.util.ArrayList;

/**
 * Created by jdenn on 4/19/2017.
 */

public class Skill {

    int number;
    String name;
    String link;

    public Skill(int number, String name, String link){
        this.number = number;
        this.name = name;
        this.link = link;
    }

    public String getName(){
        return name;
    }
    public String getLink(){
        return link;
    }


    public static final ArrayList<Skill> skillArray = populateSkillArray();
    private static ArrayList<Skill> populateSkillArray(){
        ArrayList<Skill> skillArray = new ArrayList<Skill> (36);

        skillArray.add(new Skill(  1, "Trap and Roll Escape – Mount (GU 1)*"        , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=MhEfILAfdnhgTBe9W6i%2bPQ%3d%3d       "));
        skillArray.add(new Skill(  2, "Americana Armlock – Mount (GU 2)"            , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=dkG2PaKrWHkbW%2bHxxa7%2bXA%3d%3d     "));
        skillArray.add(new Skill(  3, "Positional Control – Mount (GU 3)"           , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=E34hsiuYxJBoYG1ZYMYFqg%3d%3d         "));
        skillArray.add(new Skill(  4, "Take the Back – Mount (GU 4)"                , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=Hr2JJ9XHJ4SOr78tucuRHQ%3d%3d         "));
        skillArray.add(new Skill(  5, "Rear Naked Chock – Mount (GU 5)"             , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=%2fK0yibt2OqvBNyMjUfU2MQ%3d%3d       "));
        skillArray.add(new Skill(  6, "Leg Hook Takedown (GU 6)"                    , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=0A0xm2Fjkj%2bhWYfuqYtD6A%3d%3d       "));
        skillArray.add(new Skill(  7, "Clinch (Aggressive Opponent) (GU 7)"         , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=d9COFgiEGp0ucINiM6G08w%3d%3d         "));
        skillArray.add(new Skill(  8, "Punch Block Series (1-4) – Guard (GU 8)"     , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=Ki5CHRbu984jOKpGeVjNQQ%3d%3d         "));
        skillArray.add(new Skill(  9, "Straight Armlock – Mount (GU 9)"             , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=2KgTQAJgcbccBk9xaEP7VQ%3d%3d         "));
        skillArray.add(new Skill( 10, "Triangle Choke – Guard (GU 10)"              , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=QiCe%2fLoUmhHHliAppVPDtQ%3d%3d       "));
        skillArray.add(new Skill( 11, "Elevator Sweep – Guard (GU 11)"              , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=lHYGiWA3DhLyJoYvBarA1w%3d%3d         "));
        skillArray.add(new Skill( 12, "Elbow Escape – Mount (GU 12)"                , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=y3d1ZdpD9gBGMD9tpU5Z%2fg%3d%3d       "));
        skillArray.add(new Skill( 13, "Positional Control – Side Mount (GU 13)"     , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=JSrlOmvn%2f1wMgxZGzlpSlQ%3d%3d       "));
        skillArray.add(new Skill( 14, "Body Fold Takedown (GU 14)"                  , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=KQ%2fzpRulN869kg1ln0vjcA%3d%3d       "));
        skillArray.add(new Skill( 15, "Clinch (Conservative Opponent) (GU 15)"      , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=bTfyoi6fu9KXANIKX8tzcw%3d%3d         "));
        skillArray.add(new Skill( 16, "Headlock Counters – Mount (GU 16)"           , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=SBisFKCMRngOP4KmRQGIpQ%3d%3d"));
        skillArray.add(new Skill( 17, "Double Leg Takedown (Conservative) (GU 17)"  , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=lxa4RKypSG1YuBKdqrEmvg%3d%3d         "));
        skillArray.add(new Skill( 18, "Headlock Escape 1 – Side Mount (GU 18)"      , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=zh4zbMirWKwxlzSw%2bbL%2fbw%3d%3d     "));
        skillArray.add(new Skill( 19, "Straight Armlock – Guard (GU 19)"            , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=Ad1fZriwRTYZNUliAbpvQg%3d%3d         "));
        skillArray.add(new Skill( 20, "Double Ankle Sweep – Guard (GU 20)"          , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=TylWH8yjaRYpd6xRPULVfQ%3d%3d         "));
        skillArray.add(new Skill( 21, "Pull Guard (GU 21)"                          , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=UO5ZLgtutOHKPrjfAjCtXg%3d%3d         "));
        skillArray.add(new Skill( 22, "Headlock Escape 2 – Side Mount (GU 22)"      , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=iOOIXE6NqBVabG3bgvehUQ%3d%3d         "));
        skillArray.add(new Skill( 23, "Guillotine Choke (Guard Pull) (GU 23)"       , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=wwdk8YvcbA8flaDWSHo0qA%3d%3d         "));
        skillArray.add(new Skill( 24, "Shrimp Escape – Side Mount (GU 24)"          , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=WYRi6LPGXft8yOanN2O2UQ%3d%3d         "));
        skillArray.add(new Skill( 25, "Kimura Armlock – Guard (GU 25)"              , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=jMw%2bO47%2bDd%2fu5xh2%2f69HTg%3d%3d "));
        skillArray.add(new Skill( 26, "Standing Headlock Defense (GU 26)"           , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=LiKf9bZRnl%2bNw%2b9heBUG2Q%3d%3d     "));
        skillArray.add(new Skill( 27, "Punch Block Series (5) – Guard (GU 27)"      , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=wa78CzlxPEWkdOZi2kKR1Q%3d%3d         "));
        skillArray.add(new Skill( 28, "Hook Sweep – Guard (GU 28)"                  , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=0YVoZ9G50MRK6vQxviNaVA%3d%3d         "));
        skillArray.add(new Skill( 29, "Rear Takedown (GU 29)"                       , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=N3xVPK9Jl9gANgAyTnIKag%3d%3d         "));
        skillArray.add(new Skill( 30, "Haymaker Punch Defense (GU 30)"              , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=6y6XlTHBe1lGplav2reRMg%3d%3d         "));
        skillArray.add(new Skill( 31, "Take the Back – Guard (GU 31)"               , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=hwRYyECMiti%2brJhq2mdwJg%3d%3d       "));
        skillArray.add(new Skill( 32, "Guillotine Defense (GU 32)"                  , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=AHuIdS%2bryMj2F66mF4tcxg%3d%3d       "));
        skillArray.add(new Skill( 33, "Elbow Escape – Side Mount (GU 33)"           , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=czMv7cHyDukpMxhKnkAaXA%3d%3d         "));
        skillArray.add(new Skill( 34, "Standing Armlock (GU 34)"                    , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=Q6v0kI1B1DG6DjGXG7dUIA%3d%3d         "));
        skillArray.add(new Skill( 35, "Twisting Arm Control – Mount (GU 35)"        , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=TeKM88ZBkw8NCEgNtvdASg%3d%3d         "));
        skillArray.add(new Skill( 36, " Double Underhook Pass – Guard (GU 36)"      , "http://www.gracieuniversity.com/mobile/lessons.aspx?enc=bzfsCCRQgks9F8jaP1UzUA%3d%3d         "));


        return skillArray;
    }

}
