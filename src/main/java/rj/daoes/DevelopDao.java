package rj.daoes;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;
import rj.classes.Develop;


@DB
public interface DevelopDao {
    @ReturnGeneratedId
    @SQL("insert into develops(name) " +
            "values(:1.name)")
    public int insertDevelop(Develop develop);
}
