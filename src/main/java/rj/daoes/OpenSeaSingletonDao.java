package rj.daoes;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import rj.classes.Clue;

import java.util.List;


@DB
public interface OpenSeaSingletonDao {
    @SQL("insert into openseaclues (clueId) values(:1.id)")
    public void insertOpenSea(Clue clue);

    @SQL("delete from openseaclues where clueId=:1.id")
    public void deleteOpenSea(Clue clue);

    @SQL("select COUNT(*) from openseaclues where clueId = :1.id")
    public int countCluesById(Clue clue);
    @SQL("select clueId from openseaclues")
    public List<Integer> getAllCluesId();
}
