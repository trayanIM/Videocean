package com.videocean.model;

import com.videocean.exception.ClipException;
import com.videocean.exception.PlaylistException;
import com.videocean.exception.UserException;

import java.util.List;

public interface IUser {


    void addClipIntoPlaylist(Playlist playlist, Clip clip);

    void removeClipFromPlaylist(Playlist playlist, Clip clip) throws PlaylistException;

    void makePlaylist(String name) throws PlaylistException;

    void addPlaylist(Playlist playlist);

    void removePlaylist(Playlist playlist) throws PlaylistException;

    void addFollower(User user) throws UserException;

    void addSubscrition(User user) throws UserException;

    void removeFollower(IUser user) throws UserException;

    void removeSubscription(IUser user) throws UserException;

    void removeClipFromMyClips(Clip clip) throws PlaylistException;

    int getUserID();

    public void AddClipToHistory(Clip clip);

    public List<Clip> getClipsFromHistory();

    public List<User> getUsersFromSupscription();

    public List<User> getUsersFromFollowers();

    public List<Playlist> getPlaylistFromPlaylists();

    void addClipToMyClips(Clip S) throws ClipException, PlaylistException;
}