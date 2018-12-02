package ic.uff.br.computacaoubiqua.database.visit;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

import ic.uff.br.computacaoubiqua.database.user.User;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "uid",
        childColumns = "user_id"))

public class Visit {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "date")
    @NonNull
    private Date visitDate;

    @ColumnInfo
    private Integer qtdPerguntas;

    @ColumnInfo(name = "user_id")
    private User user;


    public Visit(@NonNull Date visitDate, @NonNull User user) {
        this.visitDate = visitDate;
        this.user = user;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Integer getQtdPerguntas() {
        return qtdPerguntas;
    }

    public void setQtdPerguntas(Integer qtdPerguntas) {
        this.qtdPerguntas = qtdPerguntas;
    }
}