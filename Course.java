public class Course{
    private short ID;
    private String Courses;
    private String Title;
    private byte Credit;
    private String Days;
    private String Time;
    private String Duration;
    private String Professor;

    public Course(String Line){
        String[] all = Line.split(",");

        ID=Short.parseShort(all[0]);
        Courses=all[1];
        Title=all[2];
        Credit=Byte.parseByte(all[3]);
        Days=all[4];
        Time=all[5];
        Duration=all[6];
        Professor=all[7];
    }

    //get all variables to show student
    public short getID(){
        return ID;
    }

    public String getCourses(){
        return Courses;
    }

    public String getTitle(){
        return Title;
    }

    public byte getCredit(){
        return Credit;
    }

    public String getDays(){
        return Days;
    }

    public String getTime(){
        return Time;
    }

    public String getDuration(){
        return Duration;
    }

    public String getProfessor(){
        return Professor;
    }
}