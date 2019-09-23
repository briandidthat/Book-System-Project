package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class NoteDaoJdbcTemplateImpl implements NoteDao {

    //prepare statement

    //add Note
    private static final String INSERT_NOTE_SQL=
            "insert into note (book_id, note) values (?, ?)";
    //get Note
    private static final String SELECT_NOTE_SQL=
            "select * from note where note_id=?";
    //get all Notes
    private static final String SELECT_ALL_NOTES_SQL=
            "select * from note";
    //update Note
    private static final String UPDATE_NOTE_SQL=
            "update note set book_id = ?, note = ? where note_id = ?";
    //delete Note
    private static final String DELETE_NOTE_SQL=
            "delete from note where note_id=?";
    //get Notes By Book Id
    private static final String SELECT_NOTES_BY_BOOK_ID_SQL=
            "select * from note where book_id=?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NoteDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Mapping between the row
    private Note mapToRowToNote(ResultSet rs, int rowNum) throws SQLException{

        Note note = new Note();

        note.setNoteId(rs.getInt("note_id"));
        note.setBookId(rs.getInt("book_id"));
        note.setNote(rs.getString("note"));

        return note;
    }

    @Override
    @Transactional
    public Note addNote(Note note)
    {
        jdbcTemplate.update(INSERT_NOTE_SQL, note.getBookId(),note.getNote());

        //grabbing the last inserted id from the Database
        int id= jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        note.setNoteId(id);
        return note;
    }

    @Override
    public Note getNote(int noteId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_NOTE_SQL,this::mapToRowToNote,noteId);

        }catch (EmptyResultDataAccessException e){
            //if there is no match return null
            return null;
        }
    }

    @Override
    public List<Note> getAllNotes() {
        return jdbcTemplate.query(SELECT_ALL_NOTES_SQL,this::mapToRowToNote);
    }

    @Override
    public void updateNote(Note note) {

        jdbcTemplate.update(UPDATE_NOTE_SQL,note.getBookId(),note.getNote(),
                note.getNoteId());

    }

    @Override
    public void deleteNote(int noteId) {
        jdbcTemplate.update(DELETE_NOTE_SQL,noteId);

    }

    @Override
    public List<Note> getNotesByBookId(int bookId) {
        return jdbcTemplate.query(SELECT_NOTES_BY_BOOK_ID_SQL,this::mapToRowToNote,bookId);
    }
}
