package movie;

import java.sql.Date;

public class Movie {
    private  int id;
    private String name;
    private  int grade;
    private  String genre;
    private String nation;
    private String viewingTime;
    private Date releaseDate;
    private String director;

    public Movie(String name, int grade, String genre, String nation, String viewingTime, Date releaseDate, String director) {
        this.name = name;
        this.grade = grade;
        this.genre = genre;
        this.nation = nation;
        this.viewingTime = viewingTime;
        this.releaseDate = releaseDate;
        this.director = director;
    }


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getViewingTime() {
        return viewingTime;
    }

    public void setViewingTime(String viewingTime) {
        this.viewingTime = viewingTime;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
