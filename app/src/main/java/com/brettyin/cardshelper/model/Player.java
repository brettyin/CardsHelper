package com.brettyin.cardshelper.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table PLAYER.
 */
public class Player {

    private Long id;
    private String name;
    private String Nickname;
    private String pic;

    public Player() {
    }

    public Player(Long id) {
        this.id = id;
    }

    public Player(Long id, String name, String Nickname, String pic) {
        this.id = id;
        this.name = name;
        this.Nickname = Nickname;
        this.pic = pic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String Nickname) {
        this.Nickname = Nickname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
