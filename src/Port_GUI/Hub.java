package Port_GUI;

import java.util.Objects;

public class Hub {
    private final int NUM_ROWS = 10;
    private final int NUM_COLUMNS = 12;
    private final int PRIORITY1_COLUMN = 0;
    private final int PRIORITY2_COLUMN = 1;
    private final int PRIORITY3_START_COLUMN = 2;
    private Container[][] containers;
    public Hub(){
        containers = new Container[NUM_ROWS][NUM_COLUMNS];
    }

    public String displayHub(){
        String output = "";
        for(int i = NUM_ROWS-1; i>=0; i--){
            for(int j = 0; j<NUM_COLUMNS; j++){
                if(containers[i][j] == null){
                    output += "[  ]";
                }
                else if(containers[i][j] != null){
                    output += "[x]";
                }
            }
            output += "\n";
        }
        return output;
    }

    public void stackContainer(Container container){
        if(container.getPriority() == 1){
            for(int i = 0; i< NUM_COLUMNS; i++){
                if(containers[i][PRIORITY1_COLUMN] == null){
                    containers[i][PRIORITY1_COLUMN] = container;
                    break;
                }
            }
        }
        else if(container.getPriority() == 2){
            for(int i = 0; i< NUM_COLUMNS; i++){
                if(containers[i][PRIORITY2_COLUMN] == null){
                    containers[i][PRIORITY2_COLUMN] = container;
                    break;
                }
            }
        }
        else{
            for(int i = PRIORITY3_START_COLUMN; i<NUM_COLUMNS; i++){
                for(int j = 0; j<NUM_ROWS; j++){
                    if(containers[j][i] == null){
                        containers[j][i] = container;
                        return;
                    }
                }
            }
        }
    }

    public void removeContainer(int column){
        for(int i = NUM_ROWS-1; i>=0; i--){
            if(containers[i][column-1] != null){
                containers[i][column-1] = null;
                return;
            }
        }
    }
    public String displayContainer(int id){
        boolean found = false;
        String output = "";
        for(int i = 0; i<NUM_ROWS; i++){
            for(int j = 0; j<NUM_COLUMNS; j++){
                if(containers[i][j] != null && containers[i][j].getId()==id){
                    Container container = containers[i][j];
                    output += container.toString();
                    found = true;
                    break;
                }
            }
        }
        if(found) {
            return output;
        }
        if(!found) {
            return "Container with ID:" + id + " not found in the hub";
        }
        return "";
    }

    public int countContainersFromCountry(String countryName){
        int count = 0;
        for(int i = 0; i<NUM_ROWS; i++){
            for(int j = 0; j<NUM_COLUMNS; j++){
                if(containers[i][j] != null && containers[i][j].getCountryOforigin().equals(countryName)){
                    count++;
                }
            }
        }
        return count;
    }

    public boolean priority1Full(){
        for(int i = 0; i<NUM_ROWS; i++){
            if(containers[i][PRIORITY1_COLUMN] == null){
                return false; //if there is a space, it returns false
            }
        }
        return true; // if there isn't, it returns true
    }

    public boolean priority2Full(){
        for(int i = 0; i<NUM_ROWS; i++){
            if(containers[i][PRIORITY2_COLUMN]==null){
                return false;//if there is a space, it returns false
            }
        }
        return true;// if there isn't, it returns true
    }
    public boolean isHubFull(){
        for(int i = 0; i<NUM_ROWS; i++){
            for(int j = PRIORITY3_START_COLUMN; j<NUM_COLUMNS; j++){
                if(containers[i][j] == null){
                    return false; //if any space is empty, the hub is not full
                }
            }
        }
        return true; //all spaces are occupied
    }
    public String weightCustoms(int weight){
        String exam = "";
        for(int i = 0; i<NUM_ROWS; i++){
            for(int j = 0; j<NUM_COLUMNS; j++){
                if(containers[i][j] != null && containers[i][j].getWeight() == weight){
                    containers[i][j].setInspected(true);
                    exam += containers[i][j].toStringExam();
                    System.out.println(containers[i][j].toStringExam());
                }
            }
        }
        return exam;
    }
}