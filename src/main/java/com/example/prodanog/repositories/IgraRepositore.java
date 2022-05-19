package com.example.prodanog.repositories;

import com.example.prodanog.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class IgraRepositore {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList<Integer> addPlayer(Player player) {
        Igra igra = jdbcTemplate.queryForObject("select * from \"Igra\" where \"status\"=? and \"maxPlayer\"=?", new IgraMapper(), false, player.getMaxPlayer());
        if (igra == null) {
            igra.setMaxPlayer(player.getMaxPlayer());
            igra.setMoneycol(igra.coloda());
            igra.setNedcol(igra.coloda());
            igra.setPlay1(player.getId());
        }
        int num = igra.addPlayr(player.getId());
        if (num == 2) {
            jdbcTemplate.update("update \"Igra\" set \"player2\" = ? where \"id\" = ?", player.getId(), Igra.getId());
            ArrayList<Integer>a=new ArrayList<>();
            a.add(createIgra(igra));
            a.add(num);
            return a;
        }
        if (num == 3) {
            jdbcTemplate.update("update \"Igra\" set \"player3\" = ? where \"id\" = ?", player.getId(), Igra.getId());
            if (player.getMaxPlayer() == 3) {
                jdbcTemplate.update("update \"Igra\" set \"status\" = ? where \"id\" = ?", true, Igra.getId());
            }
            ArrayList<Integer>a=new ArrayList<>();
            a.add(createIgra(igra));
            a.add(num);
            return a;
        }
        if (num == 4) {
            if (player.getMaxPlayer() == 4) {
                jdbcTemplate.update("update \"Igra\" set \"status\" = ? where \"id\" = ?", true, Igra.getId());
            }
            jdbcTemplate.update("update \"Igra\" set \"player4\" = ? where \"id\" = ?", player.getId(), Igra.getId());
            ArrayList<Integer>a=new ArrayList<>();
            a.add(createIgra(igra));
            a.add(num);
            return a;
        }
        if (num == 5) {
            if (player.getMaxPlayer() == 5) {
                jdbcTemplate.update("update \"Igra\" set \"status\" = ? where \"id\" = ?", true, Igra.getId());
            }
            jdbcTemplate.update("update \"Igra\" set \"player4\" = ? where \"id\" = ?", player.getId(), Igra.getId());
            ArrayList<Integer>a=new ArrayList<>();
            a.add(createIgra(igra));
            a.add(num);
            return a;
        }
        if (num == 6) {
            if (player.getMaxPlayer() == 6) {
                jdbcTemplate.update("update \"Igra\" set \"status\" = ? where \"id\" = ?", true, Igra.getId());
            }
            jdbcTemplate.update("update \"Igra\" set \"player4\" = ? where \"id\" = ?", player.getId(), Igra.getId());
            ArrayList<Integer>a=new ArrayList<>();
            a.add(createIgra(igra));
            a.add(num);
            return a;
        }
        ArrayList<Integer>a=new ArrayList<>();
        a.add(createIgra(igra));
        a.add(0);
        return a;
    }
    public Boolean getStatus(Integer id){
        Igra igra =jdbcTemplate.queryForObject("select status from \"Igra\" where \"id\"=?", new IgraMapper(), id);
        return igra.getStatus();
    }


    public Integer createIgra(Igra igra) {
        jdbcTemplate.update("insert into \"Igra\" (\"id\") values(?)", igra.getId());
        jdbcTemplate.update("insert into \"Igra\" (\"play1\") values(?)", igra.getPlay1());
        jdbcTemplate.update("insert into \"Igra\" (\"play2\") values(?)", igra.getPlay2());
        jdbcTemplate.update("insert into \"Igra\" (\"play3\") values(?)", igra.getPlay3());
        jdbcTemplate.update("insert into \"Igra\" (\"play4\") values(?)", igra.getPlay4());
        jdbcTemplate.update("insert into \"Igra\" (\"play5\") values(?)", igra.getPlay5());
        jdbcTemplate.update("insert into \"Igra\" (\"play6\") values(?)", igra.getPlay6());
        jdbcTemplate.update("insert into \"Igra\" (\"nedcol\") values(?)", ParserString.parserArray(igra.getNedcol()));
        jdbcTemplate.update("insert into \"Igra\" (\"moneycol\") values(?)", ParserString.parserArray(igra.getMoneycol()));
        jdbcTemplate.update("insert into \"Igra\" (\"status\") values(?)", igra.getStatus());
        jdbcTemplate.update("insert into \"Igra\" (\"playerShod\") values(?)", igra.getPlayerShod());
        return igra.getId();
    }

    public ArrayList<Integer> getStavka(int id) {
        Igra igra = (Igra) jdbcTemplate.query("select * from \"Igra\" where \"id\"=?", new IgraMapper(), id);
        ArrayList<Integer> rez = new ArrayList<>();
        for (int i = 0; i < igra.getMaxPlayer(); i++) {
            if (i == 0) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay1());
                rez.add(player.getStavka());
            } else if (i == 1) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay2());
                rez.add(player.getStavka());
            } else if (i == 2) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay3());
                rez.add(player.getStavka());
            } else if (i == 3) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay4());
                rez.add(player.getStavka());
            } else if (i == 4) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay5());
                rez.add(player.getStavka());
            } else if (i == 5) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay6());
                rez.add(player.getStavka());
            }

        }
        return rez;
    }

    public ArrayList<Integer> getNedcol(int id) {
        Igra igra = jdbcTemplate.queryForObject("select * from \"Igra\" where \"id\"=?", new IgraMapper(), id);
        return igra.getNedcol();
    }

    public ArrayList<Integer> getMoneycol(int id) {
        Igra igra = jdbcTemplate.queryForObject("select * from \"Igra\" where \"id\"=?", new IgraMapper(), id);
        return igra.getMoneycol();
    }

    public ArrayList<Integer> deleteIgra(Integer id) {
        Igra igra = (Igra) jdbcTemplate.query("select * from \"Igra\" where \"id\"=?", new IgraMapper(), id);
        ArrayList<Integer> rez = new ArrayList<>();
        for (int i = 0; i < igra.getMaxPlayer(); i++) {
            if (i == 0) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay1());
                rez.add(player.getBalance() + player.getMon());
            }else if (i == 1) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay2());
                rez.add(player.getBalance() + player.getMon());
            }else if (i == 2) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay3());
                rez.add(player.getBalance() + player.getMon());
            }else if (i == 3) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay4());
                rez.add(player.getBalance() + player.getMon());
            }else if (i == 4) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay5());
                rez.add(player.getBalance() + player.getMon());
            }else if (i == 5) {
                Player player = (Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new IgraMapper(), igra.getPlay6());
                rez.add(player.getBalance() + player.getMon());
            }
        }
        return rez;
    }


    public Integer getSholPlayer(int id){
        Igra igra = (Igra) jdbcTemplate.query("select * from \"Igra\" where \"id\"=?", new IgraMapper(), id);
        return igra.getPlayerShod();
    }
    public Integer setStavka(int id, Integer input) {
        Igra igra = (Igra) jdbcTemplate.query("select * from \"Igra\" where \"id\"=?", new IgraMapper(), id);
        Player player = null;
        switch (igra.getPlayerShod()){
            case 1:
                player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay1());
                break;
            case 2:
                player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay2());
                break;
            case 3:
                player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay3());
                break;
            case 4:
                player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay4());
                break;
            case 5:
                player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay5());
                break;
            case 6:
                player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay6());
                break;
        }
        player.setStavka(input);
        jdbcTemplate.update("update \"Player\" set \"stavka\" = ? where \"id\" = ?", input, id);
        igra.setPlayerShod(igra.getPlayerShod() + 1);
        if (igra.getMaxPlayer() == igra.getPlayerShod()) {
            igra.setPlayerShod(1);
        }
        jdbcTemplate.update("update \"Igra\" set \"playerShod\" = ? where \"id\" = ?", igra.getPlayerShod(), id);
        return igra.getPlayerShod();
    }
    public ArrayList<Integer> getPlayerShod(int id){
        Igra igra = (Igra) jdbcTemplate.query("select * from \"Igra\" where \"id\"=?", new IgraMapper(), id);
        Player player = null;
        ArrayList<Integer> rez=new ArrayList<>();
        for (int i = 1; i <= igra.getMaxPlayer(); i++) {
            switch (igra.getPlayerShod()){
                case 1:
                    player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay1());
                    break;
                case 2:
                    player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay2());
                    break;
                case 3:
                    player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay3());
                    break;
                case 4:
                    player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay4());
                    break;
                case 5:
                    player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay5());
                    break;
                case 6:
                    player=(Player) jdbcTemplate.query("select * from \"Player\" where \"id\"=?", new PlayerMapper(), igra.getPlay6());
                    break;
            }
            rez.add(player.getStavka());
        }
        rez.add(igra.getPlayerShod());
        return rez;
    }

    public Integer setMonColDlylud(int id, int inp, int num){
        Igra igra = (Igra) jdbcTemplate.query("select * from \"Igra\" where \"id\"=?", new IgraMapper(), id);
        ArrayList<Integer> a=igra.getMonColDlylud();
        a.add(num,inp);
        return jdbcTemplate.update("update \"Igra\" set \"monColDlylud\" = ? where \"id\" = ?", ParserString.parserArray(a), id);
    }
    public ArrayList<Integer> getMonColDlylud(int id){
        Igra igra = (Igra) jdbcTemplate.query("select * from \"Igra\" where \"id\"=?", new IgraMapper(), id);
        return igra.getMonColDlylud();
    }

    public ArrayList<Integer> rezaut(int id){
        ArrayList<Integer> rez=new ArrayList<>();
        Igra igra = (Igra) jdbcTemplate.query("select * from \"Igra\" where \"id\"=?", new IgraMapper(), id);
        Player player;
        for (int i = 1; i < igra.getMaxPlayer()+1; i++) {
            switch (i){
                case 1:
                    player= (Player) jdbcTemplate.query("select * from \"Player\" where \"id\" = ?", new PlayerMapper(), igra.getPlay1());
                    rez.add(player.getBalance()+summ(player.getMoncol()));
                    break;
                case 2:
                    player= (Player) jdbcTemplate.query("select * from \"Player\" where \"id\" = ?", new PlayerMapper(), igra.getPlay2());
                    rez.add(player.getBalance()+summ(player.getMoncol()));
                    break;
                case 3:
                    player= (Player) jdbcTemplate.query("select * from \"Player\" where \"id\" = ?", new PlayerMapper(), igra.getPlay3());
                    rez.add(player.getBalance()+summ(player.getMoncol()));
                    break;
                case 4:
                    player= (Player) jdbcTemplate.query("select * from \"Player\" where \"id\" = ?", new PlayerMapper(), igra.getPlay4());
                    rez.add(player.getBalance()+summ(player.getMoncol()));
                    break;
                case 5:
                    player= (Player) jdbcTemplate.query("select * from \"Player\" where \"id\" = ?", new PlayerMapper(), igra.getPlay5());
                    rez.add(player.getBalance()+summ(player.getMoncol()));
                    break;
                case 6:
                    player= (Player) jdbcTemplate.query("select * from \"Player\" where \"id\" = ?", new PlayerMapper(), igra.getPlay6());
                    rez.add(player.getBalance()+summ(player.getMoncol()));
                    break;
            }
        }
        return rez;
    }
    private Integer summ(ArrayList<Integer> inp){
        int rez=0;
        for (int i = 0; i < inp.size(); i++) {
            rez+=inp.get(i);
        }
        return rez;
    }
    }

