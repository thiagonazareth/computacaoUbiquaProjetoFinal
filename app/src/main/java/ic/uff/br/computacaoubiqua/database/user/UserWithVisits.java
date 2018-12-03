package ic.uff.br.computacaoubiqua.database.user;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import ic.uff.br.computacaoubiqua.database.visit.Visit;

public class UserWithVisits {
   @Embedded
   public User user;

   @Relation(parentColumn = "mac_address", entityColumn = "id", entity = Visit.class)
   public List<Visit> visits;

}