package models;

/**
 * Created by mariathomas on 8/11/17.
 */
public class Member {

    private String name;
    private int teamId;
    private int id;

    public Member(String name, int teamId){
        this.name = name;
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member that = (Member) o;

        if (teamId != that.teamId) return false;
        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + teamId;
        result = 31 * result + id;
        return result;
    }
}
