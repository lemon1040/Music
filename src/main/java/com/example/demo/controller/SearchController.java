package com.example.demo.controller;

import com.example.demo.entity.Album;
import com.example.demo.entity.Song;
import com.example.demo.entity.SongList;
import com.example.demo.entity.User;
import com.example.demo.service.AlbumService;
import com.example.demo.service.SongListService;
import com.example.demo.service.SongService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    UserService userService;
    @Autowired
    SongService songService;
    @Autowired
    SongListService songListService;
    @Autowired
    AlbumService albumService;
    @Autowired
    AutoShowUtil autoShowUtil;
    @RequestMapping(value ={"/Search","/Search/Song"},method = RequestMethod.GET)
    public String search(@RequestParam("words")String searchWords, Map<String, Object> map, HttpServletRequest request){
        //User user = (User) request.getSession().getAttribute("user");
        ArrayList<Song>songs = songService.getSongByNamePart(searchWords);
        map.put("words",searchWords);
        map.putAll(autoShowUtil.showSong(songs));
        //map.put("favoritesonglist",userService.getFavoritelist(user.getUserid()));
        return "search/search";
    }

    @RequestMapping(value ="/Search/SongList",method = RequestMethod.GET)
    public String searchSonglist(@RequestParam("words")String searchWords, Map<String, Object> map, HttpServletRequest request){
        ArrayList<SongList> songlist = songListService.getSongListByNamePart(searchWords);
        map.put("words",searchWords);
        map.putAll(autoShowUtil.showSongList(songlist));
        return "search/songlist";
    }

    @RequestMapping(value ="/Search/Album",method = RequestMethod.GET)
    public String searchAlbum(@RequestParam("words")String searchWords, Map<String, Object> map, HttpServletRequest request){
        ArrayList<Album> albums = albumService.getAlbumByNamePart(searchWords);
        map.put("words",searchWords);
        map.putAll(autoShowUtil.showAlbum(albums));
        return "search/album";
    }

}
