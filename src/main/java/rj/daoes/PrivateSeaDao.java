package rj.daoes;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;
import rj.classes.Clue;
import rj.classes.Develop;

import rj.classes.PrivateSea;

import java.sql.Date;
import java.util.List;

@DB
public interface PrivateSeaDao {
    @ReturnGeneratedId
    @SQL("insert into privateseaclues(developId, clueId) values(:1.id, :2.id)")
    public int insertPrivatesea(Develop develop, Clue clue);
    @SQL("select clueId from privateseaclues where developId = :1.id")
    public List<Integer> getCluesId(Develop develop);

    @SQL("delete from privateseaclues where developId=:1.id and clueId=:2.id")
    public int deleteClue(Develop develop, Clue clue);
}
