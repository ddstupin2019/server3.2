package com.example.prodanog.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IgraMapper implements RowMapper<Igra> {
    @Override
    public Igra mapRow(ResultSet rs, int rowNum) throws SQLException {
        Igra igra = new Igra();
        igra.setId(rs.getInt("id"));
        igra.setPlay1(rs.getInt("play1"));
        igra.setPlay2(rs.getInt("play2"));
        igra.setPlay3(rs.getInt("play3"));
        igra.setPlay4(rs.getInt("play4"));
        igra.setPlay5(rs.getInt("play5"));
        igra.setPlay6(rs.getInt("play6"));
        igra.setPlayerShod(rs.getInt("playerShod"));
        igra.setMoneycol(ParserString.parserString(rs.getString("moneycol")));
        igra.setNedcol(ParserString.parserString(rs.getString("nedcol")));
        igra.setNedcol(ParserString.parserString(rs.getString("monColDlylud")));
        igra.setStatus(rs.getBoolean("status"));
        igra.setMaxPlayer(rs.getInt("maxPlayer"));
        return igra;
    }
}
