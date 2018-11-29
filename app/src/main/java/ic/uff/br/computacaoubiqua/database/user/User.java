package ic.uff.br.computacaoubiqua.database.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;

@Entity(indices = {@Index(value = {"mac_address"}, unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "first_name")
    @NonNull
    private String firstName;

    @ColumnInfo(name = "last_name")
    @NonNull
    private String lastName;

    @ColumnInfo(name = "mac_address")
    @NonNull
    private String macAddress;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "kinship")
    private String kinship;

    @ColumnInfo(name = "place")
    private String place;

    public User(@NonNull String firstName, @NonNull String lastName, @NonNull String macAddress, String description, String kinship, String place) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.macAddress = macAddress;
        this.description = description;
        this.kinship = kinship;
        this.place = place;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

}