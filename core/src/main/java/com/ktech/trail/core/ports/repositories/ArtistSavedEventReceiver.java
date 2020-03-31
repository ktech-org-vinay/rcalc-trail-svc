package com.ktech.trail.core.ports.repositories;

import com.ktech.trail.core.model.events.ArtistRegisteredEvent;

public interface ArtistSavedEventReceiver {
  void receive(ArtistRegisteredEvent event);
}
