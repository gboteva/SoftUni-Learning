package FundamentalsCourse.objectsAndClasses_6.MoreEx.TeamworkProjects_Object;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private String creator;
    private List<String> members;

    public Project(String name, String creator){
        this.name = name;
        this.creator = creator;
        this.members = new ArrayList<>();
    }

    public String getCreator(){
        return this.creator;
    }

    public String getName(){
        return this.name;
    }

    public List<String> getMembers(){
        return this.members;
    }

    public void setMembers(String member){
        this.members.add(member);
    }
}
