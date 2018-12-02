package ic.uff.br.computacaoubiqua.database.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;

@Entity
public class User {

    @PrimaryKey
    @ColumnInfo(name = "mac_address")
    @NonNull
    private String macAddress;

    @ColumnInfo(name = "device_name")
    private String deviceName;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "kinship")
    private String kinship;

    @ColumnInfo(name = "place")
    private String place;

    @ColumnInfo(name = "photo_path")
    private String photoPath;

    public User(String firstName, String lastName, @NonNull String macAddress, String deviceName, String description, String kinship, String place, String photoPath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.macAddress = macAddress;
        this.description = description;
        this.kinship = kinship;
        this.place = place;
        this.photoPath = photoPath;
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKinship() {
        return kinship;
    }

    public void setKinship(String kinship) {
        this.kinship = kinship;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

}