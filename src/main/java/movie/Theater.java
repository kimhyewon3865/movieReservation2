package movie;

public class Theater {
	int id;
    String name;
    String location;

    public Theater(String name, String location) {
    	this.name = name;
    	this.location = location;
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

    public String getTheaterLocation() {
        return location;
    }
    
    

}
