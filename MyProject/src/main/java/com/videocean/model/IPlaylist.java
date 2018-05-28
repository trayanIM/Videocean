package com.videocean.model;

import com.videocean.exception.PlaylistException;

public interface IPlaylist {

    void addClipToPlaylist(Clip clip);

    void removeClipFromPlaylist(Clip clip) throws PlaylistException;

    void changeState(TYPE state);

    void increaseViewsOfPlaylist();

    String getName();

    Object getOwner();

}