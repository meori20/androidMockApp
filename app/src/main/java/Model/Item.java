package Model;

/**
 * Created by shahar on 05/05/2018.
 */

public class Item {
    String gender;
    String image;
    String last_name;
    String name;


    public Item(String gender,String imageUrl,String lastName,String name ) {
    this.gender=gender;
    this.last_name=lastName;
    this.image=imageUrl;
    this.name = name;
    }

    public Item(){

    }
    public void setmGender(String mGender) {
        this.gender = mGender;
    }

    public void setmImageUrl(String mImageUrl) {
        this.image = mImageUrl;
    }

    public void setmLastName(String mLastName) {
        this.last_name = mLastName;
    }

    public void setmName(String mName) {
        this.name = mName;
    }

    public String getGender() {
        return gender;
    }

    public String getImageUrl() {
        return image;
    }

    public String getLastName() {
        return last_name;
    }

    public String getName() {
        return name;
    }
}