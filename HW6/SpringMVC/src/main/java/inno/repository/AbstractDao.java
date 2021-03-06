package inno.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractDao<T> {

    @Autowired
    protected JdbcTemplate template;

    protected RowMapper<T> rowMapper;
    private String tableName;
    private String seqName;

    public AbstractDao(String tableName, String seqName, RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
        this.tableName = tableName;
        this.seqName = seqName;
    }

    public List<T> findAll(){
       return template.query("SELECT * FROM " + tableName, rowMapper);
    }

    public T find(Long id){
        return template.queryForObject("SELECT * FROM " + tableName + " WHERE id = ?", new Object[]{id}, rowMapper);
    }

    public boolean add(String fields, Object... args){
        return template.update(
                String.format("INSERT INTO (%s) VALUES (%s)",
                tableName, fields, seqName,
                IntStream.range(0, args.length).boxed().map(i -> "?").collect(Collectors.joining(","))),
                args) > 0;
    }

    public boolean delete(Long id){
        return template.update("DELETE FROM " + tableName + " WHERE id = ?", id) > 0;
    }

}
