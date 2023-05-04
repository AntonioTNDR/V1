package Port_GUI;
public class Container {
    private int id;
    private int weight;
    private String countryOfOrigin;
    private boolean inspectedByCustoms;
    private int priority;
    private String contentDescription;
    private String sendingCompany;
    private String recivingCompany;
    Container(){
    }

    Container(int id, int weight, String countryOfOrigin, boolean inspectedByCustoms, int priority, String contentDescription, String sendingCompany, String recivingCompany){
        this.id = id;
        this.weight = weight;
        this.countryOfOrigin = countryOfOrigin;
        this.inspectedByCustoms = inspectedByCustoms;
        this.priority = priority;
        this.contentDescription = contentDescription;
        this.sendingCompany = sendingCompany;
        this.recivingCompany = recivingCompany;
    }

    // getters and setters

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }

    public void setCountryOfOrigin(String countryOfOrigin){
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getCountryOforigin(){
        return countryOfOrigin;
    }

    public void setInspected(boolean inspectedByCustoms){
        this.inspectedByCustoms = inspectedByCustoms;
    }

    public boolean getInspected(){
        return inspectedByCustoms;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public int getPriority(){
        return priority;
    }

    public void setContentDescription(String contentDescription){
        this.contentDescription = contentDescription;
    }

    public String getContentDescription(){
        return contentDescription;
    }

    public void setSending(String sendingCompany){
        this.sendingCompany = sendingCompany;
    }

    public String getSending(){
        return sendingCompany;
    }

    public void setReciving(String recivingCompany){
        this.recivingCompany = recivingCompany;
    }

    public String getReciving(){
        return recivingCompany;
    }

    // toString method

    @Override
    public String toString(){
        return "Container{" + "id: " + id + ", weight: " + weight + ", Country of origin: " + countryOfOrigin + ", Inspected: " + inspectedByCustoms + ", Priority Level: " + priority + ", Content description: " + contentDescription + ", Sending company: " + sendingCompany + ", Reciving company: " + recivingCompany + "}";
    }
    public String toStringExam(){
        return " ID: " + id + "; sender: " + sendingCompany + "; weight: " + weight + "; inspected: " + inspectedByCustoms +";";
    }

}