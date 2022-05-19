package com.example.prodanog.controllers;

import com.example.prodanog.entity.Player;
import com.example.prodanog.repositories.IgraRepositore;
import com.example.prodanog.repositories.PlayerRepozitore;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("PS")
public class Controler {
    @Autowired
    private IgraRepositore igraRepositore;
    @Autowired
    private PlayerRepozitore playerRepozitore;

    @RequestMapping(value = "/setBalance", method = RequestMethod.PUT,
            consumes = "text/plain")
    public Integer setBalance(@RequestBody String param){
        try {
            JSONObject jsonObject = new JSONObject(param);
            return playerRepozitore.setBalance(jsonObject.getInt("balance"),jsonObject.getInt("id"));

        } catch (JSONException e){
            e.getStackTrace();
            System.out.println("Не удалось распарсить тело запроса");
            return 0;
        }
    }

    @RequestMapping(value = "/getNedCol", method = RequestMethod.GET)
    public ArrayList<Integer> getNedCol(@RequestParam("id") int id){
        return igraRepositore.getNedcol(id);
    }

    @RequestMapping(value = "/getMoneyCol", method = RequestMethod.GET)
    public ArrayList<Integer> getMoneyCol(@RequestParam("id") int id){
        return igraRepositore.getMoneycol(id);
    }

    @RequestMapping(value = "/deleteIgra", method = RequestMethod.DELETE)
    public ArrayList<Integer> deleteIgra(@RequestParam("id") int id){
        return igraRepositore.deleteIgra(id);
    }

    @RequestMapping(value = "/getStatus", method = RequestMethod.GET)
    public Boolean getStatus(@RequestParam("id") int id){
        return igraRepositore.getStatus(id);
    }

    @RequestMapping(value = "/setStavka", method = RequestMethod.PUT,
            consumes = "text/plain")
    public Integer setStavka(@RequestBody String param){
        try {
            JSONObject jsonObject = new JSONObject(param);
            return igraRepositore.setStavka(jsonObject.getInt("id"), jsonObject.getInt("stavka"));

        } catch (JSONException e){
            e.getStackTrace();
            System.out.println("Не удалось распарсить тело запроса");
            return 0;
        }
    }
    @RequestMapping(value = "/getPlayerShod", method = RequestMethod.GET)
    public ArrayList<Integer> getPlayerShod(@RequestParam("id") int id){
        return igraRepositore.getStavka(id);
    }

    @RequestMapping(value = "/createPlayer", method = RequestMethod.PUT,
            consumes = "text/plain")
    public ArrayList<Integer> createPlayer(@RequestBody String param){
        System.out.println("Create Player");
        Player player = new Player();
        try {
            JSONObject jsonObject = new JSONObject(param);
            player.setMaxPlayer(jsonObject.getInt("maxPlayer"));
            player.setName(jsonObject.getString("name"));

        } catch (JSONException e){
            e.getStackTrace();
            System.out.println("Не удалось распарсить тело запроса");
            return null;
        }
        playerRepozitore.createPlayer(player);
        return igraRepositore.addPlayer(player);
    }

    @RequestMapping(value = "/getNumPlayer", method = RequestMethod.GET)
    public Integer getSholdPlayer(@RequestParam("id") int id){
        return igraRepositore.getSholPlayer(id);
    }

    @RequestMapping(value = "/setNedColPlayer", method = RequestMethod.PUT,
            consumes = "text/plain")
    public Integer setNedColPlayer(@RequestBody String param){
        try {
            JSONObject jsonObject = new JSONObject(param);
            return playerRepozitore.addNedCol(jsonObject.getInt("nedCart"),jsonObject.getInt("id"));

        } catch (JSONException e){
            e.getStackTrace();
            System.out.println("Не удалось распарсить тело запроса");
            return 0;
        }
    }

    @RequestMapping(value = "/getMonColDlylud", method = RequestMethod.GET)
    public ArrayList<Integer> getMonColDlylud(@RequestParam("id") int id){
        return igraRepositore.rezaut(id);
    }
    @RequestMapping(value = "/setMonColDlylud", method = RequestMethod.PUT,
            consumes = "text/plain")
    public Integer setMonColDlylud(@RequestBody String param){
        try {
            JSONObject jsonObject = new JSONObject(param);
            return igraRepositore.setMonColDlylud(jsonObject.getInt("id"),jsonObject.getInt("monCol"), jsonObject.getInt("num"));

        } catch (JSONException e){
            e.getStackTrace();
            System.out.println("Не удалось распарсить тело запроса");
            return 0;
        }
    }



}
