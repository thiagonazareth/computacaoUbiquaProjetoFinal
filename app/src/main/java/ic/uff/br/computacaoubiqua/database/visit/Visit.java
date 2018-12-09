package ic.uff.br.computacaoubiqua.database.visit;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

import ic.uff.br.computacaoubiqua.database.user.User;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(foreignKeys = @ForeignKey( onDelete = CASCADE, entity = User.class,
        parentColumns = "mac_address",
        childColumns = "user_id"))

public class Visit {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    @NonNull
    private Date visitDate;

    @ColumnInfo
    private Integer qtdPerguntas;

    @ColumnInfo(name = "user_id")
    private String macAddress;

    public Visit(@NonNull Date visitDate, @NonNull Integer qtdPerguntas, @NonNull String macAddress) {
        this.visitDate = visitDate;
        this.macAddress = macAddress;
        this.qtdPerguntas = qtdPerguntas;
    }

    public int getId() {
        return id;
    }

    public void setId(int uid) {
        this.id = id;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }


    public Integer getQtdPerguntas() {
        return qtdPerguntas;
    }

    public void setQtdPerguntas(Integer qtdPerguntas) {
        this.qtdPerguntas = qtdPerguntas;
    }
}