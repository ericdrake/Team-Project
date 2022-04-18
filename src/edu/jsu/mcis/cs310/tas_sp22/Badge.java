package edu.jsu.mcis.cs310.tas_sp22;

public class Badge {

    private String id;
    private String description; 

    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#").append(id).append(" ");
        sb.append("(").append(description).append(")");
        return sb.toString();
    }

    
    
}
