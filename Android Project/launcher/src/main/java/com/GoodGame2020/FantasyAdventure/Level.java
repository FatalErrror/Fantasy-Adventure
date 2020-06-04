package com.GoodGame2020.FantasyAdventure;




public class Level {
    public Level(int ImageID, String Name, int UnitySceneID){
        this.Name = Name;
        this.ImageID = ImageID;
        this.UnitySceneID = UnitySceneID;
        this.Discription = "Some level";
    }

    public Level(int ImageID, String Name,int UnitySceneID, String Discription){
        this.Name = Name;
        this.ImageID = ImageID;
        this.UnitySceneID = UnitySceneID;
        this.Discription = Discription;
    }


    public String Name;
    public String Discription;

    public int ImageID;

    public int UnitySceneID;

    /*new
    *
    *
    * */
}
